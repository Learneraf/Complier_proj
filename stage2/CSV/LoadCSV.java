import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class LoadCSV {
    // 自定义监听器，继承自 ANTLR 自动生成的 CSVBaseListener
    public static class Loader extends CSVBaseListener {
        
        // 存储最终解析结果：一个列表，其中每个元素是一个代表一行数据的 Map
        private final List<Map<String, String>> rows = new ArrayList<>();

        // 存储从 CSV 文件第一行解析出的表头
        private List<String> headers;

        // 临时存储当前正在解析的行的所有字段值
        private List<String> currentRowValues;

        // 标志位，用于区分表头行和数据行
        private boolean isHeaderProcessed = false;
        
        // 标志位，用于判断当前字段是否已被 TEXT 或 STRING 规则填充
        private boolean fieldHasValue = false;

        /**
         * 每当解析器进入一个 'row' 规则时被调用。
         * 这是初始化当前行数据存储的最佳时机。
         */
        public void enterRow(CSVParser.RowContext ctx) {
            currentRowValues = new ArrayList<>();
            // 重置字段值标志位
            fieldHasValue = false;
        }

        /**
         * 每当解析器退出一个 'row' 规则时被调用。
         * 这是处理和存储整行数据的最佳时机。
         */
        public void exitRow(CSVParser.RowContext ctx) {
            // 如果表头尚未处理，则当前行为表头
            if (!isHeaderProcessed) {
                headers = new ArrayList<>(currentRowValues);
                isHeaderProcessed = true;
            } else {
                // 如果是数据行，则将其转换为 Map 并存入结果列表
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    // 防止数据列数少于表头列数导致的索引越界
                    String value = i < currentRowValues.size() ? currentRowValues.get(i) : "";
                    rowMap.put(headers.get(i), value);
                }
                rows.add(rowMap);
            }
        }

        /**
         * 每当解析器退出一个 'TEXT' 规则时被调用。
         * 这对应于一个非引号、非空的文本字段。
         */
        public void exitText(CSVParser.TextContext ctx) {
            currentRowValues.add(ctx.TEXT().getText());
            fieldHasValue = true;
        }

        /**
         * 每当解析器退出一个 'STRING' 规则时被调用。
         * 这对应于一个带引号的字符串字段。
         */
        public void exitString(CSVParser.StringContext ctx) {
            String quotedString = ctx.STRING().getText();
            // 移除首尾的双引号，并将内部的两个双引号 ("") 替换为一个双引号 (")
            String unquotedString = quotedString.substring(1, quotedString.length() - 1).replace("\"\"", "\"");
            currentRowValues.add(unquotedString);
            fieldHasValue = true;
        }

        /**
         * 每当解析器退出一个 'field' 规则时被调用。
         * 这个方法特别重要，因为它会在 TEXT 或 STRING 之后被调用，
         * 并且也会在一个空字段（即规则 `field : | ;`）被匹配时被调用。
         */
        public void exitField(CSVParser.FieldContext ctx) {
            // 如果字段不是由 TEXT 或 STRING 构成的（即它是一个空字段），
            // 则向当前行的值列表中添加一个空字符串。
            if (!fieldHasValue) {
                currentRowValues.add("");
            }
            // 为下一个字段重置标志位
            fieldHasValue = false;
        }

        /**
         * 获取解析完成后的数据。
         * @return 包含所有数据行的 List<Map<String, String>>
         */
        public List<Map<String, String>> getRows() {
            return rows;
        }
    }

    public static void main(String[] args) throws Exception {
        // 1. 设置输入流
        // 如果命令行提供了文件名，则从文件读取；否则从标准输入读取
        String inputFile = args.length > 0 ? args[0] : null;
        InputStream is = System.in;
        if (inputFile != null) {
            is = new FileInputStream(inputFile);
        }

        // 2. 创建 CharStream
        ANTLRInputStream input = new ANTLRInputStream(is);

        // 3. 创建 Lexer
        CSVLexer lexer = new CSVLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. 创建 Parser
        CSVParser parser = new CSVParser(tokens);
        parser.setBuildParseTree(true); // 确保构建解析树

        // 5. 开始解析，从 'file' 规则开始
        ParseTree tree = parser.file();

        // 6. 创建监听器实例
        Loader listener = new Loader();

        // 7. 创建树遍历器，并使用监听器遍历解析树
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        // 8. 获取并打印结果
        List<Map<String, String>> data = listener.getRows();
        System.out.println("成功解析 " + data.size() + " 行数据:");
        for (Map<String, String> row : data) {
            System.out.println(row);
        }
    }
}