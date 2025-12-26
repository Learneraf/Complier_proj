import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.util.*;

public class AsmGenerator {

    // --- 1. 定义四元式结构 ---
    static class Quadruple {
        String op, arg1, arg2, result;
        public Quadruple(String op, String a1, String a2, String res) {
            this.op = op; this.arg1 = a1; this.arg2 = a2; this.result = res;
        }
    }

    // --- 2. 同样的 Visitor 用于生成中间代码 ---
    public static class Generator extends GoBaseVisitor<String> {
        List<Quadruple> codes = new ArrayList<>();
        int tempCount = 0;
        int labelCount = 0;

        private String newTemp() { return "t" + (++tempCount); }
        private String newLabel() { return "L" + (++labelCount); }
        private void emit(String op, String a1, String a2, String res) { codes.add(new Quadruple(op, a1, a2, res)); }

        @Override public String visitVarDecl(GoParser.VarDeclContext ctx) {
            if (ctx.expression() != null) emit("=", visit(ctx.expression()), null, ctx.ID().getText());
            return null;
        }
        @Override public String visitAssignStat(GoParser.AssignStatContext ctx) {
            emit("=", visit(ctx.expression()), null, ctx.ID().getText());
            return null;
        }
        @Override public String visitExpression(GoParser.ExpressionContext ctx) {
            if (ctx.getChildCount() == 1) return ctx.getText();
            if (ctx.getText().startsWith("(")) return visit(ctx.expression(0));
            if (ctx.getChildCount() == 3) {
                String op = ctx.getChild(1).getText();
                String left = visit(ctx.expression(0));
                String right = visit(ctx.expression(1));
                String t = newTemp();
                emit(op, left, right, t);
                return t;
            }
            return super.visitExpression(ctx);
        }
        @Override public String visitIfStat(GoParser.IfStatContext ctx) {
            String cond = visit(ctx.expression());
            String L_true = newLabel(), L_false = newLabel(), L_next = newLabel();
            emit("if", cond, null, L_true);
            emit("goto", null, null, L_false);
            emit("label", null, null, L_true);
            visit(ctx.block(0));
            emit("goto", null, null, L_next);
            emit("label", null, null, L_false);
            if (ctx.block().size() > 1) visit(ctx.block(1));
            emit("label", null, null, L_next);
            return null;
        }
        @Override public String visitForStat(GoParser.ForStatContext ctx) {
            if (ctx.initStmt() != null) visit(ctx.initStmt());
            String L_start = newLabel(), L_body = newLabel(), L_end = newLabel();
            emit("label", null, null, L_start);
            if (ctx.expression() != null) {
                String cond = visit(ctx.expression());
                emit("if", cond, null, L_body);
                emit("goto", null, null, L_end);
            }
            emit("label", null, null, L_body);
            visit(ctx.block());
            if (ctx.postStmt() != null) visit(ctx.postStmt());
            emit("goto", null, null, L_start);
            emit("label", null, null, L_end);
            return null;
        }
        @Override public String visitInitStmt(GoParser.InitStmtContext ctx) {
            emit("=", visit(ctx.expression()), null, ctx.ID().getText()); return null;
        }
        @Override public String visitPostStmt(GoParser.PostStmtContext ctx) {
            emit("=", visit(ctx.expression()), null, ctx.ID().getText()); return null;
        }
    }

    // --- 3. 核心：汇编翻译器 ---
    public static void translateToX86(List<Quadruple> codes) {
        System.out.println("; === x86 Assembly Code ===");
        System.out.println(".section .data");
        
        // 1. 扫描所有变量并声明 (包括临时变量 t1, t2...)
        Set<String> vars = new HashSet<>();
        for (Quadruple q : codes) {
            if (isVar(q.arg1)) vars.add(q.arg1);
            if (isVar(q.arg2)) vars.add(q.arg2);
            if (isVar(q.result) && !q.op.equals("label") && !q.op.equals("goto") && !q.op.equals("if")) vars.add(q.result);
        }
        for (String v : vars) {
            System.out.println("\t" + v + ": .long 0"); // 默认初始化为 0
        }

        System.out.println("\n.section .text");
        System.out.println(".global main");
        System.out.println("main:");

        // 2. 逐条翻译
        for (Quadruple q : codes) {
            String op = q.op;
            String r = q.result;
            String a1 = q.arg1;
            String a2 = q.arg2;

            if (op.equals("label")) {
                System.out.println(r + ":");
            } 
            else if (op.equals("=")) {
                // r = a1 -> MOV EAX, a1; MOV r, EAX
                loadToEAX(a1);
                System.out.println("\tMOV [" + r + "], EAX");
            } 
            else if (op.equals("+")) {
                loadToEAX(a1);
                if (isNumber(a2)) System.out.println("\tADD EAX, " + a2);
                else System.out.println("\tADD EAX, [" + a2 + "]");
                System.out.println("\tMOV [" + r + "], EAX");
            } 
            else if (op.equals("-")) {
                loadToEAX(a1);
                if (isNumber(a2)) System.out.println("\tSUB EAX, " + a2);
                else System.out.println("\tSUB EAX, [" + a2 + "]");
                System.out.println("\tMOV [" + r + "], EAX");
            } 
            else if (op.equals("*")) {
                loadToEAX(a1);
                if (isNumber(a2)) System.out.println("\tIMUL EAX, " + a2);
                else System.out.println("\tIMUL EAX, [" + a2 + "]");
                System.out.println("\tMOV [" + r + "], EAX");
            }
            // 比较运算: t1 = a > b
            else if (op.equals(">") || op.equals("<") || op.equals("==")) {
                loadToEAX(a1);
                System.out.println("\tCMP EAX, " + (isNumber(a2) ? a2 : "[" + a2 + "]"));
                // 根据操作符设置条件位
                if (op.equals(">")) System.out.println("\tSETG AL");
                else if (op.equals("<")) System.out.println("\tSETL AL");
                else if (op.equals("==")) System.out.println("\tSETE AL");
                
                System.out.println("\tMOVZX EAX, AL"); // 扩展到 32 位
                System.out.println("\tMOV [" + r + "], EAX");
            }
            else if (op.equals("if")) {
                // if t1 goto L1 -> CMP t1, 0; JNE L1
                System.out.println("\tMOV EAX, [" + a1 + "]");
                System.out.println("\tCMP EAX, 0");
                System.out.println("\tJNE " + r); // 如果非 0 (True), 跳转
            }
            else if (op.equals("goto")) {
                System.out.println("\tJMP " + r);
            }
        }
        // 程序退出
        System.out.println("\tMOV EAX, 1");
        System.out.println("\tINT 0x80");
    }

    // 辅助：把变量或数字放入 EAX
    private static void loadToEAX(String val) {
        if (isNumber(val)) {
            System.out.println("\tMOV EAX, " + val);
        } else {
            System.out.println("\tMOV EAX, [" + val + "]");
        }
    }

    private static boolean isNumber(String s) { return s.matches("\\d+"); }
    private static boolean isVar(String s) { return s != null && !isNumber(s); }

    public static void main(String[] args) throws Exception {
        String inputFile = "test.go";
        if (args.length > 0) inputFile = args[0];
        
        GoLexer lexer = new GoLexer(new ANTLRFileStream(inputFile));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GoParser parser = new GoParser(tokens);
        ParseTree tree = parser.sourceFile();

        Generator visitor = new Generator();
        visitor.visit(tree);

        // 调用翻译器
        translateToX86(visitor.codes);
    }
}