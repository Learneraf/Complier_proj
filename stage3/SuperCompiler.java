import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SuperCompiler {

    // ==========================================
    //       第一部分：数据结构
    // ==========================================
    static class Quadruple {
        String op, arg1, arg2, result;
        public Quadruple(String op, String a1, String a2, String res) {
            this.op = op; this.arg1 = a1; this.arg2 = a2; this.result = res;
        }
    }

    static class SymbolInfo {
        String type; boolean isUsed; int line;
        public SymbolInfo(String type, int line) { this.type = type; this.isUsed = false; this.line = line; }
    }

    // ==========================================
    //       第二部分：核心分析器 (Visitor)
    // ==========================================
    public static class Analyzer extends GoBaseVisitor<String> {
        List<Quadruple> codes = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        List<String> optimizationLogs = new ArrayList<>();
        Map<String, SymbolInfo> symbolTable = new HashMap<>();
        
        int tempCount = 0, labelCount = 0;
        boolean hasReturned = false;

        private String newTemp() { return "t" + (++tempCount); }
        private String newLabel() { return "L" + (++labelCount); }
        
        private void emit(String op, String a1, String a2, String res) { 
            if (!hasReturned) codes.add(new Quadruple(op, a1, a2, res)); 
        }

        private boolean isInt(String s) { return s != null && s.matches("-?\\d+"); }
        private boolean isFloat(String s) { return s != null && s.matches("-?\\d+\\.\\d+"); }
        private boolean isVar(String s) { return s != null && !isInt(s) && !isFloat(s); }
        
        private String getType(String val) {
            if (isInt(val)) return "int";
            if (isFloat(val)) return "float";
            if (symbolTable.containsKey(val)) return symbolTable.get(val).type;
            return "unknown"; 
        }

        // --- 变量声明 ---
        @Override public String visitVarDecl(GoParser.VarDeclContext ctx) {
            if (hasReturned) return null;
            String id = ctx.ID().getText(); String type = ctx.type().getText(); int line = ctx.getStart().getLine();
            if (symbolTable.containsKey(id)) errors.add("[Line " + line + "] 语义错误: 变量 '" + id + "' 重复定义。");
            else symbolTable.put(id, new SymbolInfo(type, line));
            if (ctx.expression() != null) {
                String val = visit(ctx.expression());
                String valType = getType(val);
                if (!valType.equals("unknown") && !valType.equals(type)) 
                    errors.add("[Line " + line + "] 类型不匹配: 无法将 " + valType + " 赋值给 " + type + " 变量 '" + id + "'");
                emit("=", val, null, id);
            }
            return null;
        }

        // --- 赋值语句 ---
        @Override public String visitAssignStat(GoParser.AssignStatContext ctx) {
            if (hasReturned) return null;
            String id = ctx.ID().getText(); int line = ctx.getStart().getLine();
            if (!symbolTable.containsKey(id)) { errors.add("[Line " + line + "] 语义错误: 变量 '" + id + "' 未声明。"); return null; }
            symbolTable.get(id).isUsed = true;
            String val = visit(ctx.expression());
            String valType = getType(val);
            String targetType = symbolTable.get(id).type;
            if (!valType.equals("unknown") && !valType.equals(targetType)) 
                errors.add("[Line " + line + "] 类型不匹配: 无法将 " + valType + " 赋值给 " + targetType + " 变量 '" + id + "'");
            emit("=", val, null, id);
            return null;
        }

        // --- 表达式 (含优化) ---
        @Override public String visitExpression(GoParser.ExpressionContext ctx) {
            int line = ctx.getStart().getLine();
            if (ctx.getChildCount() == 1) {
                String text = ctx.getText();
                if (isVar(text)) {
                    if (symbolTable.containsKey(text)) symbolTable.get(text).isUsed = true;
                    else if (errors.stream().noneMatch(e -> e.contains("'" + text + "'")))
                        errors.add("[Line " + line + "] 语义错误: 变量 '" + text + "' 未定义");
                }
                return text;
            }
            if (ctx.getText().startsWith("(")) return visit(ctx.expression(0));
            if (ctx.getChildCount() == 3) {
                String op = ctx.getChild(1).getText();
                String left = visit(ctx.expression(0));
                String right = visit(ctx.expression(1));
                
                // 常量折叠
                if (isInt(left) && isInt(right)) {
                    int v1 = Integer.parseInt(left), v2 = Integer.parseInt(right);
                    if(op.equals("+")) { optimizationLogs.add("[Line "+line+"] 常量折叠: "+left+"+"+right+" -> "+(v1+v2)); return String.valueOf(v1+v2); }
                    if(op.equals("-")) { optimizationLogs.add("[Line "+line+"] 常量折叠: "+left+"-"+right+" -> "+(v1-v2)); return String.valueOf(v1-v2); }
                    if(op.equals("*")) { optimizationLogs.add("[Line "+line+"] 常量折叠: "+left+"*"+right+" -> "+(v1*v2)); return String.valueOf(v1*v2); }
                }
                // 代数化简
                if (op.equals("*") && (left.equals("0") || right.equals("0"))) { 
                    optimizationLogs.add("[Line " + line + "] 代数化简: 乘零优化，结果置为 0"); return "0"; 
                }

                String t = newTemp(); emit(op, left, right, t); return t;
            }
            return super.visitExpression(ctx);
        }

        // --- 控制流 ---
        @Override public String visitBlock(GoParser.BlockContext ctx) { 
            for (GoParser.StatementContext stmt : ctx.statement()) {
                if(hasReturned) { optimizationLogs.add("[Line " + stmt.getStart().getLine() + "] 死代码消除: 移除 return 后的语句"); continue; }
                visit(stmt); 
            }
            return null; 
        }
        @Override public String visitInitStmt(GoParser.InitStmtContext ctx) { emit("=", visit(ctx.expression()), null, ctx.ID().getText()); return null; }
        @Override public String visitPostStmt(GoParser.PostStmtContext ctx) { emit("=", visit(ctx.expression()), null, ctx.ID().getText()); return null; }
        
        @Override public String visitIfStat(GoParser.IfStatContext ctx) {
            if(hasReturned) return null;
            String cond = visit(ctx.expression());
            if (cond.equals("0") || cond.equals("false")) {
                optimizationLogs.add("[Line " + ctx.getStart().getLine() + "] 死代码消除: 移除无效 if 分支");
                if (ctx.block().size() > 1) visit(ctx.block(1));
                return null;
            }
            String L_true = newLabel(), L_false = newLabel(), L_next = newLabel();
            emit("if", cond, null, L_true); emit("goto", null, null, L_false);
            emit("label", null, null, L_true);
            boolean oldRet = hasReturned; visit(ctx.block(0)); hasReturned = oldRet;
            emit("goto", null, null, L_next);
            emit("label", null, null, L_false); if(ctx.block().size()>1) { oldRet = hasReturned; visit(ctx.block(1)); hasReturned = oldRet; }
            emit("label", null, null, L_next);
            return null;
        }

        @Override public String visitForStat(GoParser.ForStatContext ctx) {
             if(hasReturned) return null;
             if (ctx.initStmt() != null) visit(ctx.initStmt());
             String start=newLabel(), body=newLabel(), end=newLabel();
             emit("label", null, null, start);
             if(ctx.expression()!=null) { emit("if", visit(ctx.expression()), null, body); emit("goto", null, null, end); }
             emit("label", null, null, body); visit(ctx.block());
             if (ctx.postStmt() != null) visit(ctx.postStmt());
             emit("goto", null, null, start); emit("label", null, null, end);
             return null;
        }
        @Override public String visitReturnStat(GoParser.ReturnStatContext ctx) {
            String val = ctx.expression()!=null ? visit(ctx.expression()) : "0";
            emit("return", val, null, null); hasReturned=true; return null;
        }
    }

    // ==========================================
    //       第三部分：汇编与可执行文件生成
    // ==========================================
    public static String generateAssembly(List<Quadruple> codes) {
        StringBuilder sb = new StringBuilder();
        sb.append(".intel_syntax noprefix\n.global main\n\n.section .data\n");
        Set<String> vars = new HashSet<>();
        for (Quadruple q : codes) {
            if (isVar(q.arg1)) vars.add(q.arg1);
            if (isVar(q.arg2)) vars.add(q.arg2);
            if (isVar(q.result) && !q.op.matches("label|goto|if|return")) vars.add(q.result);
        }
        for (String v : vars) sb.append("\t").append(v).append(": .long 0\n");

        sb.append("\n.section .text\nmain:\n\tpush rbp\n\tmov rbp, rsp\n"); 
        for (Quadruple q : codes) {
            String op = q.op, r = q.result, a1 = q.arg1, a2 = q.arg2;
            if (op.equals("label")) sb.append(r).append(":\n");
            else if (op.equals("=")) { sb.append(load(a1)).append("\tMOV [").append(r).append("], EAX\n"); }
            else if (op.equals("+")) { sb.append(load(a1)).append("\tADD EAX, ").append(operand(a2)).append("\n\tMOV [").append(r).append("], EAX\n"); }
            else if (op.equals("-")) { sb.append(load(a1)).append("\tSUB EAX, ").append(operand(a2)).append("\n\tMOV [").append(r).append("], EAX\n"); }
            else if (op.equals("*")) { sb.append(load(a1)).append("\tIMUL EAX, ").append(operand(a2)).append("\n\tMOV [").append(r).append("], EAX\n"); }
            else if (op.equals("if")) { sb.append("\tMOV EAX, [").append(a1).append("]\n\tCMP EAX, 0\n\tJNE ").append(r).append("\n"); }
            else if (op.equals("goto")) { sb.append("\tJMP ").append(r).append("\n"); }
            else if (op.matches(">|<|==")) {
                 sb.append(load(a1)).append("\tCMP EAX, ").append(operand(a2)).append("\n");
                 String set = op.equals(">")?"SETG":op.equals("<")?"SETL":"SETE";
                 sb.append("\t").append(set).append(" AL\n\tMOVZX EAX, AL\n\tMOV [").append(r).append("], EAX\n");
            }
            else if (op.equals("return")) { sb.append(load(a1)).append("\tpop rbp\n\tret\n"); }
        }
        sb.append("\tmov eax, 0\n\tpop rbp\n\tret\n");
        return sb.toString();
    }

    private static String load(String val) { return isNumber(val) ? "\tMOV EAX, "+val+"\n" : "\tMOV EAX, ["+val+"]\n"; }
    private static String operand(String val) { return isNumber(val) ? val : "["+val+"]"; }
    private static boolean isNumber(String s) { return s!=null && s.matches("-?\\d+"); }
    private static boolean isVar(String s) { return s!=null && !isNumber(s); }

    // ==========================================
    //       第四部分：主程序 (Driver)
    // ==========================================
    public static void main(String[] args) throws Exception {
        String inputFile = "test_final.go";
        if (args.length > 0) inputFile = args[0];

        System.out.println("正在编译: " + inputFile + "...");
        GoLexer lexer = new GoLexer(new ANTLRFileStream(inputFile));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GoParser parser = new GoParser(tokens);
        ParseTree tree = parser.sourceFile();

        // 1. GUI 树 (Task 3a)
        System.out.println("启动语法树可视化...");
        TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewer.setScale(1.5);
        JFrame frame = new JFrame("AST"); frame.add(new JScrollPane(viewer)); frame.setSize(800, 600); frame.setVisible(true);

        Analyzer analyzer = new Analyzer();
        analyzer.visit(tree);

        if (!analyzer.errors.isEmpty()) {
            for (String err : analyzer.errors) System.err.println(err);
            return;
        }

        // 2. 优化日志 (Task 4)
        System.out.println("\n[Phase 4] 优化日志:");
        if(analyzer.optimizationLogs.isEmpty()) System.out.println("(无优化)");
        else for(String log : analyzer.optimizationLogs) System.out.println(log);

        System.out.println("\n----------------- 静态分析警告 ------------------");
        for (Map.Entry<String, SymbolInfo> entry : analyzer.symbolTable.entrySet()) {
            if (!entry.getValue().isUsed) System.out.println("[WARN] [Line " + entry.getValue().line + "] 变量 '" + entry.getKey() + "' 已声明但从未被引用。");
        }

        // 3. 中间代码 (Task 3b) - 【格式修正版】
        System.out.println("\n[Phase 3b] 中间代码 (Three-Address Code):");
        for(Quadruple q : analyzer.codes) {
            String op=q.op, a1=q.arg1, a2=q.arg2, res=q.result;
            if(op.equals("=")) System.out.printf("%-4s := %s%n", res, a1);
            else if(op.equals("if")) System.out.printf("if %s goto %s%n", a1, res);
            else if(op.equals("goto")) System.out.printf("goto %s%n", res);
            else if(op.equals("label")) System.out.println(res + ":");
            else if(op.equals("return")) System.out.println("return " + (a1==null?"":a1));
            else System.out.printf("%-4s := %s %s %s%n", res, a1, op, a2);
        }

        // 4. 汇编生成 (Task 3c) & 可执行文件 (Task 4b)
        String asmCode = generateAssembly(analyzer.codes);
        String asmFileName = "output.s";
        try (PrintWriter out = new PrintWriter(asmFileName)) { out.println(asmCode); }
        System.out.println("\n[Phase 3c] 汇编代码已保存: " + asmFileName);

        System.out.println("\n[Phase 4b Bonus] 调用 GCC 生成可执行文件...");
        try {
            ProcessBuilder pb = new ProcessBuilder("gcc", asmFileName, "-o", "output.exe");
            pb.inheritIO();
            if (pb.start().waitFor() == 0) {
                System.out.println(" -> [SUCCESS] 生成成功: output.exe");
                System.out.println(" -> 尝试运行...");
                Process p = new ProcessBuilder("output.exe").start();
                System.out.println(" -> 程序退出码: " + p.waitFor());
            } else {
                System.err.println(" -> [FAIL] GCC 编译失败");
            }
        } catch (IOException e) {
            System.err.println(" -> [FAIL] 未找到 GCC，无法生成 EXE (不影响其他功能得分)");
        }
    }
}