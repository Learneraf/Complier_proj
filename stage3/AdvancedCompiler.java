import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.PrintWriter;
import java.util.*;

public class AdvancedCompiler {

    // --- 四元式 ---
    static class Quadruple {
        String op, arg1, arg2, result;
        public Quadruple(String op, String a1, String a2, String res) {
            this.op = op; this.arg1 = a1; this.arg2 = a2; this.result = res;
        }
    }

    // --- 带有优化和错误检查的生成器 ---
    public static class Optimizer extends GoBaseVisitor<String> {
        List<Quadruple> codes = new ArrayList<>();
        Set<String> declaredVars = new HashSet<>(); // 【符号表】记录已声明的变量
        List<String> errors = new ArrayList<>();    // 【错误列表】
        
        int tempCount = 0;
        int labelCount = 0;

        private String newTemp() { return "t" + (++tempCount); }
        private String newLabel() { return "L" + (++labelCount); }
        private void emit(String op, String a1, String a2, String res) { codes.add(new Quadruple(op, a1, a2, res)); }

        // 判断是否为数字
        private boolean isNumber(String s) { return s != null && s.matches("-?\\d+"); }

        // --- 1. 变量声明 (记录到符号表) ---
        @Override
        public String visitVarDecl(GoParser.VarDeclContext ctx) {
            String id = ctx.ID().getText();
            if (declaredVars.contains(id)) {
                errors.add("Error: Variable '" + id + "' already declared.");
            } else {
                declaredVars.add(id);
            }

            if (ctx.expression() != null) {
                String val = visit(ctx.expression());
                emit("=", val, null, id);
            }
            return null;
        }

        // --- 2. 赋值 (语义检查：变量是否已声明) ---
        @Override
        public String visitAssignStat(GoParser.AssignStatContext ctx) {
            String id = ctx.ID().getText();
            if (!declaredVars.contains(id)) {
                errors.add("Error: Variable '" + id + "' used before declaration.");
            }
            String val = visit(ctx.expression());
            emit("=", val, null, id);
            return null;
        }

        // --- 3. 表达式 (【核心优化】：常量折叠) ---
        @Override
        public String visitExpression(GoParser.ExpressionContext ctx) {
            if (ctx.getChildCount() == 1) {
                String text = ctx.getText();
                // 如果是变量，检查是否已声明
                if (isVar(text) && !declaredVars.contains(text)) {
                    errors.add("Error: Undefined variable '" + text + "' in expression.");
                }
                return text;
            }
            if (ctx.getText().startsWith("(")) return visit(ctx.expression(0));
            
            if (ctx.getChildCount() == 3) {
                String op = ctx.getChild(1).getText();
                String left = visit(ctx.expression(0));
                String right = visit(ctx.expression(1));

                // === 附加功能 4c: 常量折叠优化 ===
                // 如果左右两边都是数字，直接算出结果！
                if (isNumber(left) && isNumber(right)) {
                    int v1 = Integer.parseInt(left);
                    int v2 = Integer.parseInt(right);
                    int res = 0;
                    boolean optimized = true;
                    switch (op) {
                        case "+": res = v1 + v2; break;
                        case "-": res = v1 - v2; break;
                        case "*": res = v1 * v2; break;
                        case "/": res = v1 / v2; break;
                        default: optimized = false; // 比较运算暂不折叠
                    }
                    if (optimized) {
                        // 直接返回计算结果，不生成中间代码！
                        System.out.println("[Optimization] Constant folded: " + left + op + right + " -> " + res);
                        return String.valueOf(res);
                    }
                }
                // ===================================

                String t = newTemp();
                emit(op, left, right, t);
                return t;
            }
            return super.visitExpression(ctx);
        }

        // ... If, For, Init, Post 保持不变，省略以节省篇幅，直接复用之前的逻辑即可 ...
        // 为了代码完整性，请把之前的 visitIfStat, visitForStat, visitInitStmt, visitPostStmt 复制过来
        // (如果不方便复制，我可以把完整代码再发一次)
        
        @Override public String visitIfStat(GoParser.IfStatContext ctx) {
            String cond = visit(ctx.expression());
            String L_true = newLabel(), L_false = newLabel(), L_next = newLabel();
            emit("if", cond, null, L_true); emit("goto", null, null, L_false);
            emit("label", null, null, L_true); visit(ctx.block(0)); emit("goto", null, null, L_next);
            emit("label", null, null, L_false);
            if (ctx.block().size() > 1) visit(ctx.block(1));
            emit("label", null, null, L_next); return null;
        }
        @Override public String visitForStat(GoParser.ForStatContext ctx) {
            if (ctx.initStmt() != null) visit(ctx.initStmt());
            String L_start = newLabel(), L_body = newLabel(), L_end = newLabel();
            emit("label", null, null, L_start);
            if (ctx.expression() != null) {
                String cond = visit(ctx.expression());
                emit("if", cond, null, L_body); emit("goto", null, null, L_end);
            }
            emit("label", null, null, L_body); visit(ctx.block());
            if (ctx.postStmt() != null) visit(ctx.postStmt());
            emit("goto", null, null, L_start); emit("label", null, null, L_end); return null;
        }
        @Override public String visitInitStmt(GoParser.InitStmtContext ctx) {
            emit("=", visit(ctx.expression()), null, ctx.ID().getText()); return null;
        }
        @Override public String visitPostStmt(GoParser.PostStmtContext ctx) {
            emit("=", visit(ctx.expression()), null, ctx.ID().getText()); return null;
        }
    }

    // --- 汇编生成 (保持不变) ---
    public static void translateToX86(List<Quadruple> codes) {
        // ... 直接复用 AsmGenerator 中的 translateToX86 方法 ...
        // 这里简单打印一下，实际请复制 AsmGenerator 的完整逻辑
        System.out.println("\n; === Optimized x86 Assembly ===");
        System.out.println(".section .data");
        Set<String> vars = new HashSet<>();
        for (Quadruple q : codes) {
            if (isVar(q.arg1)) vars.add(q.arg1);
            if (isVar(q.arg2)) vars.add(q.arg2);
            if (isVar(q.result) && !q.op.startsWith("L") && !q.op.equals("goto") && !q.op.equals("if")) vars.add(q.result);
        }
        for (String v : vars) System.out.println("\t" + v + ": .long 0");
        System.out.println(".section .text\n.global main\nmain:");
        
        for (Quadruple q : codes) {
             // 简单的指令打印，你可以替换为 AsmGenerator 的详细逻辑
             if(q.op.equals("label")) System.out.println(q.result + ":");
             else if(q.op.equals("=")) System.out.println("\tMOV EAX, " + q.arg1 + "\n\tMOV [" + q.result + "], EAX");
             else if(q.op.equals("+")) System.out.println("\tMOV EAX, " + q.arg1 + "\n\tADD EAX, " + q.arg2 + "\n\tMOV [" + q.result + "], EAX");
             else if(q.op.equals("*")) System.out.println("\tMOV EAX, " + q.arg1 + "\n\tIMUL EAX, " + q.arg2 + "\n\tMOV [" + q.result + "], EAX");
             // ... 其他指令省略，用 AsmGenerator 的即可
             else System.out.println("\t; " + q.result + " = " + q.arg1 + " " + q.op + " " + q.arg2);
        }
    }

    private static boolean isVar(String s) { return s != null && !s.matches("-?\\d+"); }

    public static void main(String[] args) throws Exception {
        String inputFile = "test_opt.go"; // 使用新的测试文件
        if (args.length > 0) inputFile = args[0];

        GoLexer lexer = new GoLexer(new ANTLRFileStream(inputFile));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GoParser parser = new GoParser(tokens);
        ParseTree tree = parser.sourceFile();

        Optimizer visitor = new Optimizer();
        visitor.visit(tree);

        // 1. 检查错误 (4a)
        if (!visitor.errors.isEmpty()) {
            System.err.println("=== Compilation Failed with " + visitor.errors.size() + " Errors ===");
            for (String err : visitor.errors) {
                System.err.println(err);
            }
            return; // 有错就不生成代码了
        }

        System.out.println("=== Compilation Success! ===");
        // 2. 生成优化后的代码 (4c)
        translateToX86(visitor.codes);
    }
}