import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class LoadCSV {
    // 自定义监听器，继承自 ANTLR 自动生成的 CSVBaseListener
    public static class Loader extends CSVBaseListener {
        public static final String EMPTY = "";
        
        // 【核心数据结构】存储最终结果：[ {Name="Jiang", Score="95"}, {...} ]
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        
        // 临时存储表头：[ "StudentId", "Name", "Course", "Score" ]
        List<String> header;
        
        // 临时存储当前正在处理的这一行的所有字段值
        List<String> currentRowFieldValues;

        // --- 下面是事件触发方法 ---

        // 1. 每当解析到一个普通文本字段 (对应 # Text)
        public void exitText(CSVParser.TextContext ctx) {
            currentRowFieldValues.add(ctx.TEXT().getText());
        }

        // 2. 每当解析到一个带引号的字符串字段 (对应 # String)
        public void exitString(CSVParser.StringContext ctx) {
            currentRowFieldValues.add(ctx.STRING().getText());
        }

        // 3. 每当解析到一个空字段 (对应 # Empty)
        public void exitEmpty(CSVParser.EmptyContext ctx) {
            currentRowFieldValues.add(EMPTY);
        }

        // 4. 开始处理一行时，初始化临时列表
        public void enterRow(CSVParser.RowContext ctx) {
            currentRowFieldValues = new ArrayList<String>();
        }

        // 5. 处理完一行时，将数据打包存入 rows
        public void exitRow(CSVParser.RowContext ctx) {
            // 如果这一行是表头（hdr 规则内部也调用了 row），需要单独处理
            // 通过父节点的规则索引来判断
            if (ctx.getParent().getRuleIndex() == CSVParser.RULE_hdr) return;

            // 将数据值与表头列名一一对应，存入 Map
            Map<String, String> m = new LinkedHashMap<String, String>();
            int i = 0;
            for (String v : currentRowFieldValues) {
                // 防止数据列比表头列多的情况导致越界
                if (i < header.size()) {
                    m.put(header.get(i), v);
                }
                i++;
            }
            rows.add(m);
        }

        // 6. 处理完表头时，保存表头信息
        public void exitHdr(CSVParser.HdrContext ctx) {
            header = new ArrayList<String>();
            header.addAll(currentRowFieldValues);
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) is = new FileInputStream(inputFile);
        
        // 标准流程：Lexer -> Token -> Parser -> Tree
        CSVLexer lexer = new CSVLexer(new ANTLRInputStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.file(); // 开始解析

        // 创建遍历器和监听器
        ParseTreeWalker walker = new ParseTreeWalker();
        Loader loader = new Loader();
        
        // 开始“爬树”！
        walker.walk(loader, tree);
        
        // 打印结果
        System.out.println("成功加载 " + loader.rows.size() + " 行数据:");
        for (Map<String, String> row : loader.rows) {
            System.out.println(row);
        }
    }
}