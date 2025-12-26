import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class CallGraph {
    
    public static class GraphBuilder extends CymbolBaseListener {
        // 存储当前正在处理的函数名（也就是“调用者”）
        String currentFunctionName = null;
        
        // 使用 Set 来存储图的边，自动去重（防止递归调用打印无数条边）
        // 存储格式："main -> gcd"
        Set<String> edges = new LinkedHashSet<String>();

        // 1. 当进入函数定义时：记录当前函数名
        // 对应 grammar: type ID '(' ...
        @Override
        public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx) {
            currentFunctionName = ctx.ID().getText();
        }

        // 2. 当遇到函数调用表达式时：记录一条边
        // 对应 grammar: ID '(' exprList? ')' # Call
        @Override
        public void exitCall(CymbolParser.CallContext ctx) {
            String funcName = ctx.ID().getText();
            // 只有当在某个函数内部时，才记录调用关系
            if (currentFunctionName != null) {
                String edge = "  " + currentFunctionName + " -> " + funcName + ";";
                edges.add(edge);
            }
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
        ParseTree tree = parser.file(); // 解析

        ParseTreeWalker walker = new ParseTreeWalker();
        GraphBuilder builder = new GraphBuilder();
        walker.walk(builder, tree); // 遍历

        // 输出 DOT 格式的图
        System.out.println("digraph G {");
        System.out.println("  rankdir=LR;");      // 让图从左向右画
        System.out.println("  main [shape=box];"); // 给 main 函数加个方框突出显示
        
        for (String edge : builder.edges) {
            System.out.println(edge);
        }
        System.out.println("}");
    }
}