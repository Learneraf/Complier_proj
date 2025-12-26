import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InterCodeGenerator {

    // --- 数据结构：四元式 ---
    static class Quadruple {
        String op;      // 操作符 (e.g., +, -, if, goto)
        String arg1;    // 第一个操作数
        String arg2;    // 第二个操作数 (可为空)
        String result;  // 结果变量 或 跳转标号

        public Quadruple(String op, String arg1, String arg2, String result) {
            this.op = op;
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.result = result;
        }

        @Override
        public String toString() {
            // 格式化输出，类似：t1 = a + b
            if (op.equals("=")) {
                return result + " = " + arg1;
            } else if (isCalcOp(op)) {
                return result + " = " + arg1 + " " + op + " " + arg2;
            } else if (op.equals("if")) {
                return "if " + arg1 + " goto " + result;
            } else if (op.equals("goto")) {
                return "goto " + result;
            } else if (op.equals("label")) {
                return result + ":";
            } else if (op.equals("param")) {
                return "param " + arg1;
            } else if (op.equals("call")) {
                return result + " = call " + arg1 + ", " + arg2;
            } else if (op.equals("return")) {
                return "return " + (arg1 == null ? "" : arg1);
            }
            return "(" + op + ", " + arg1 + ", " + arg2 + ", " + result + ")";
        }

        private boolean isCalcOp(String op) {
            return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") ||
                   op.equals(">") || op.equals("<") || op.equals("==") || op.equals(">=") || op.equals("<=");
        }
    }

    // --- 核心生成器 (Visitor) ---
    public static class Generator extends GoBaseVisitor<String> {
        List<Quadruple> codes = new ArrayList<>();
        int tempCount = 0;
        int labelCount = 0;

        // 生成临时变量 t1, t2...
        private String newTemp() {
            tempCount++;
            return "t" + tempCount;
        }

        // 生成标号 L1, L2...
        private String newLabel() {
            labelCount++;
            return "L" + labelCount;
        }

        private void emit(String op, String arg1, String arg2, String result) {
            codes.add(new Quadruple(op, arg1, arg2, result));
        }

        // 1. 变量声明: var a int = 10;
        @Override
        public String visitVarDecl(GoParser.VarDeclContext ctx) {
            String varName = ctx.ID().getText();
            if (ctx.expression() != null) {
                String initVal = visit(ctx.expression()); // 获取右边表达式的值/临时变量
                emit("=", initVal, null, varName);
            }
            return null;
        }

        // 2. 赋值语句: a = b + 1;
        @Override
        public String visitAssignStat(GoParser.AssignStatContext ctx) {
            String id = ctx.ID().getText();
            String value = visit(ctx.expression());
            emit("=", value, null, id);
            return null;
        }

        // 3. 算术运算与关系运算: a + b, a > b
        // 这里的逻辑覆盖了 MulDiv, AddSub, Relational
        @Override
        public String visitExpression(GoParser.ExpressionContext ctx) {
            // 如果是叶子节点 (ID, INT, FLOAT)
            if (ctx.getChildCount() == 1) {
                return ctx.getText();
            }
            // 如果是括号 ( expr )
            if (ctx.getText().startsWith("(")) {
                return visit(ctx.expression(0));
            }
            // 如果是二元运算: expr op expr
            if (ctx.getChildCount() == 3) {
                String left = visit(ctx.expression(0));  // 递归计算左边
                String op = ctx.getChild(1).getText();   // 获取操作符
                String right = visit(ctx.expression(1)); // 递归计算右边
                String temp = newTemp();                 // 申请新临时变量
                emit(op, left, right, temp);             // 生成指令
                return temp;                             // 向上返回临时变量名
            }
            return super.visitExpression(ctx);
        }

        // 4. If 语句 (关键难点)
        @Override
        public String visitIfStat(GoParser.IfStatContext ctx) {
            String cond = visit(ctx.expression()); // 计算条件, 结果在 cond 中 (如 t1)
            
            String labelTrue = newLabel();
            String labelFalse = newLabel();
            String labelNext = newLabel();

            // 生成跳转指令
            emit("if", cond, null, labelTrue); // if t1 goto L1
            emit("goto", null, null, labelFalse); // goto L2 (否则跳到 False)

            // True 分支
            emit("label", null, null, labelTrue); // L1:
            visit(ctx.block(0)); // 执行 True 代码块
            emit("goto", null, null, labelNext);  // 执行完跳出

            // False 分支 (如果有 else)
            emit("label", null, null, labelFalse); // L2:
            if (ctx.block().size() > 1 || ctx.ifStat() != null) {
                if (ctx.block().size() > 1) visit(ctx.block(1)); // else { block }
                if (ctx.ifStat() != null) visit(ctx.ifStat());   // else if ...
            }
            
            // 结束标号
            emit("label", null, null, labelNext); // L3:
            return null;
        }

        // 5. For 循环
        @Override
        public String visitForStat(GoParser.ForStatContext ctx) {
            // for init; cond; post { ... }
            if (ctx.initStmt() != null) visit(ctx.initStmt());

            String labelStart = newLabel(); // 循环开始
            String labelBody = newLabel();  // 循环体
            String labelEnd = newLabel();   // 循环结束

            emit("label", null, null, labelStart); // L_start:

            // 条件判断
            if (ctx.expression() != null) {
                String cond = visit(ctx.expression());
                emit("if", cond, null, labelBody); // if cond goto L_body
                emit("goto", null, null, labelEnd); // goto L_end
            }

            // 循环体
            emit("label", null, null, labelBody); // L_body:
            visit(ctx.block());

            // Post 语句 (i=i+1)
            if (ctx.postStmt() != null) visit(ctx.postStmt());
            
            // 跳回开始
            emit("goto", null, null, labelStart);

            // 结束
            emit("label", null, null, labelEnd); // L_end:
            return null;
        }

        @Override
        public String visitInitStmt(GoParser.InitStmtContext ctx) {
            String id = ctx.ID().getText();
            String val = visit(ctx.expression());
            emit("=", val, null, id);
            return null;
        }

        @Override
        public String visitPostStmt(GoParser.PostStmtContext ctx) {
            String id = ctx.ID().getText();
            String val = visit(ctx.expression());
            emit("=", val, null, id);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = "test.go"; // 你的测试文件
        if (args.length > 0) inputFile = args[0];

        GoLexer lexer = new GoLexer(new ANTLRFileStream(inputFile));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GoParser parser = new GoParser(tokens);
        ParseTree tree = parser.sourceFile();

        Generator visitor = new Generator();
        visitor.visit(tree);

        System.out.println("=== 三地址指令 (Three-Address Code) ===");
        for (Quadruple q : visitor.codes) {
            System.out.println(q);
        }
    }
}