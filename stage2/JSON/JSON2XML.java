import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class JSON2XML {
    
    public static class XMLEmitter extends JSONBaseListener {
        // 【核心工具】用来在节点之间传递翻译结果
        // 就像给树上的每个节点挂一个 String 类型的牌子
        ParseTreeProperty<String> xml = new ParseTreeProperty<String>();

        // 辅助方法：获取某个节点的翻译结果
        String getXML(ParseTree ctx) { return xml.get(ctx); }
        // 辅助方法：设置某个节点的翻译结果
        void setXML(ParseTree ctx, String s) { xml.put(ctx, s); }

        // --- 下面是具体的翻译逻辑 ---

        // 1. 处理原子值 (数字, true, false, null) -> 原样输出
        public void exitAtom(JSONParser.AtomContext ctx) {
            setXML(ctx, ctx.getText());
        }

        // 2. 处理字符串 -> 去掉 JSON 的双引号
        public void exitString(JSONParser.StringContext ctx) {
            setXML(ctx, stripQuotes(ctx.getText()));
        }

        // 3. 处理对象中的一个键值对 "key": value -> <key>value_xml</key>
        public void exitPair(JSONParser.PairContext ctx) {
            String tag = stripQuotes(ctx.STRING().getText()); // 获取标签名 (去掉引号)
            String content = getXML(ctx.value());             // 获取值的翻译结果
            String result = String.format("<%s>%s</%s>\n", tag, content, tag);
            setXML(ctx, result);
        }

        // 4. 处理非空对象 { pair1, pair2 } -> 拼接所有 pair 的结果
        public void exitAnObject(JSONParser.AnObjectContext ctx) {
            StringBuilder buf = new StringBuilder();
            for (JSONParser.PairContext pctx : ctx.pair()) {
                buf.append(getXML(pctx));
            }
            setXML(ctx, buf.toString());
        }

        // 5. 处理空对象 {} -> 空字符串
        public void exitEmptyObject(JSONParser.EmptyObjectContext ctx) {
            setXML(ctx, "");
        }

        // 6. 处理数组 [v1, v2] -> <element>v1</element> <element>v2</element>
        public void exitArrayOfValues(JSONParser.ArrayOfValuesContext ctx) {
            StringBuilder buf = new StringBuilder();
            for (JSONParser.ValueContext vctx : ctx.value()) {
                buf.append("<element>");
                buf.append(getXML(vctx));
                buf.append("</element>\n");
            }
            setXML(ctx, buf.toString());
        }

        // 7. 处理空数组 [] -> 空字符串
        public void exitEmptyArray(JSONParser.EmptyArrayContext ctx) {
            setXML(ctx, "");
        }

        // 8. 处理 json 根节点 -> 最终输出
        public void exitJson(JSONParser.JsonContext ctx) {
            setXML(ctx, getXML(ctx.getChild(0)));
        }

        // 中转站：ObjectValue 只是包装了 obj，直接透传
        public void exitObjectValue(JSONParser.ObjectValueContext ctx) {
            setXML(ctx, getXML(ctx.obj()));
        }
        // 中转站：ArrayValue 只是包装了 array，直接透传
        public void exitArrayValue(JSONParser.ArrayValueContext ctx) {
            setXML(ctx, getXML(ctx.array()));
        }

        // 工具方法：去掉字符串首尾的双引号 "abc" -> abc
        public static String stripQuotes(String s) {
            if (s == null || s.length() < 2) return s;
            return s.substring(1, s.length() - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0) inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null) is = new FileInputStream(inputFile);

        JSONLexer lexer = new JSONLexer(new ANTLRInputStream(is));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokens);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.json(); // 解析

        ParseTreeWalker walker = new ParseTreeWalker();
        XMLEmitter converter = new XMLEmitter();
        walker.walk(converter, tree); // 遍历并翻译

        // 从根节点拿到最终结果并打印
        System.out.println(converter.getXML(tree));
    }
}