import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Validate {
    
    public static class Validator extends CymbolBaseListener {
        // --- 简单的符号表实现 ---
        
        // 作用域栈：每一个元素是一个 Set，存储该作用域下定义的变量名
        // Stack 底部是全局作用域，顶部是当前局部作用域
        Stack<Set<String>> scopes = new Stack<Set<String>>();

        // 构造函数：初始化时先推入一个全局作用域
        public Validator() {
            scopes.push(new HashSet<String>());
        }

        // 辅助方法：在当前作用域定义变量
        void defineVar(String name, Token token) {
            Set<String> currentScope = scopes.peek();
            if (currentScope.contains(name)) {
                System.err.println("错误 (Line " + token.getLine() + "): 变量 '" + name + "' 已经被定义过了！");
            } else {
                currentScope.add(name);
                // System.out.println("定义变量: " + name); // 调试用
            }
        }

        // 辅助方法：解析变量（从内向外查找）
        void resolveVar(String name, Token token) {
            // 从栈顶（当前作用域）往下找（全局作用域）
            for (int i = scopes.size() - 1; i >= 0; i--) {
                if (scopes.get(i).contains(name)) {
                    return; // 找到了，没问题
                }
            }
            // 找遍了所有作用域都没找到
            System.err.println("错误 (Line " + token.getLine() + "): 使用了未定义的变量 '" + name + "'");
        }

        // --- 监听器事件 ---

        // 1. 进入函数时：创建新的局部作用域
        @Override
        public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
            // System.out.println("--> 进入函数 " + ctx.ID().getText());
            scopes.push(new HashSet<String>());
            
            // 把函数的参数也加到这个新作用域里
            if (ctx.formalParameters() != null) {
                for (CymbolParser.FormalParameterContext p : ctx.formalParameters().formalParameter()) {
                    defineVar(p.ID().getText(), p.ID().getSymbol());
                }
            }
        }

        // 2. 离开函数时：销毁局部作用域
        @Override
        public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
            // System.out.println("<-- 离开函数 " + ctx.ID().getText());
            scopes.pop();
        }

        // 3. 遇到变量声明：int a = ...;
        @Override
        public void exitVarDecl(CymbolParser.VarDeclContext ctx) {
            String name = ctx.ID().getText();
            defineVar(name, ctx.ID().getSymbol());
        }

        // 4. 遇到变量引用：a = b + 1; 中的 a, b
        // 对应语法: ID # Var
        @Override
        public void exitVar(CymbolParser.VarContext ctx) {
            String name = ctx.ID().getText();
            resolveVar(name, ctx.ID().getSymbol());
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) is = new FileInputStream(inputFile);

        CymbolLexer lexer = new CymbolLexer(new ANTLRInputStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CymbolParser parser = new CymbolParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.file();

        ParseTreeWalker walker = new ParseTreeWalker();
        Validator validator = new Validator();
        
        System.out.println("开始语义验证...");
        walker.walk(validator, tree);
        System.out.println("验证结束。");
    }
}