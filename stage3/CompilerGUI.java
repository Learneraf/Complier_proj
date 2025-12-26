import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets; // 引入标准字符集
import java.util.Arrays;
import java.util.Map;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.gui.TreeViewer;

public class CompilerGUI extends JFrame {

    // UI 组件
    private JTextArea sourceEditor;
    private JTextArea irArea;       
    private JTextArea asmArea;      
    private JTextArea logArea;      
    private JTextArea consoleArea;  
    private JPanel treePanel;       
    private JTabbedPane rightTabs;

    // 字体配置：使用“微软雅黑”确保中文不乱码，同时兼顾代码显示
    // 如果想要更完美的代码显示，推荐安装 "JetBrains Mono" 或 "Fira Code" 并替换此处
    private final Font UI_FONT = new Font("Microsoft YaHei", Font.PLAIN, 13); 
    private final Font CODE_FONT = new Font("Microsoft YaHei", Font.PLAIN, 14); // 强制使用雅黑以防乱码
    
    // 颜色配置 (暗黑极客风 - 修正版)
    private final Color BG_DARK = new Color(43, 43, 43);
    private final Color PANEL_BG = new Color(60, 63, 65);
    private final Color EDITOR_BG = new Color(30, 30, 30); // 更深的背景
    private final Color TEXT_COLOR = new Color(200, 200, 200);
    private final Color ACCENT_COLOR = new Color(75, 110, 175); // 按钮蓝
    private final Color TAB_BG = new Color(50, 50, 50);

    public CompilerGUI() {
        setTitle("Mini-Go Compiler IDE (Ultimate Edition)");
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // 设置全局 UI 字体，防止部分组件乱码
        setUIFont(new javax.swing.plaf.FontUIResource("Microsoft YaHei", Font.PLAIN, 13));

        // ==================== 1. 顶部工具栏 ====================
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        toolbar.setBackground(PANEL_BG);
        toolbar.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JButton btnLoad = createModernButton("📂 加载代码 (Load)");
        JButton btnRun = createModernButton("▶ 编译运行 (Run)");
        
        toolbar.add(btnLoad);
        toolbar.add(btnRun);
        add(toolbar, BorderLayout.NORTH);

        // ==================== 2. 主体分割 ====================
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplit.setDividerLocation(600);
        mainSplit.setBorder(null);
        mainSplit.setContinuousLayout(true);
        mainSplit.setDividerSize(3);
        mainSplit.setBackground(BG_DARK);

        // --- 左侧：代码编辑区 ---
        sourceEditor = createEditorArea();
        // 这里的中文如果编译时没加 -encoding UTF-8 就会乱码
        sourceEditor.setText("func main() {\n    var a int = 10;\n    var b int = 20;\n    b = 100 + 200; // 优化测试\n    if b > 50 {\n        a = a + 1;\n    }\n    return a;\n}");
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        JLabel srcLabel = new JLabel("  Go Source Code");
        srcLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        srcLabel.setForeground(Color.WHITE);
        srcLabel.setBorder(new EmptyBorder(8,0,8,0));
        leftPanel.setBackground(BG_DARK);
        leftPanel.add(srcLabel, BorderLayout.NORTH);
        
        JScrollPane srcScroll = new JScrollPane(sourceEditor);
        srcScroll.setBorder(null);
        srcScroll.getVerticalScrollBar().setBackground(BG_DARK);
        leftPanel.add(srcScroll, BorderLayout.CENTER);
        
        mainSplit.setLeftComponent(leftPanel);

        // --- 右侧：多功能标签页 ---
        rightTabs = new JTabbedPane();
        rightTabs.setFont(UI_FONT);
        rightTabs.setBackground(TAB_BG);
        rightTabs.setForeground(Color.WHITE);
        
        // Tab 1: 语法树
        treePanel = new JPanel(new BorderLayout());
        treePanel.setBackground(Color.WHITE); 
        rightTabs.addTab("Phase 3a: 语法树", new JScrollPane(treePanel));

        // Tab 2: 优化日志
        logArea = createEditorArea();
        logArea.setForeground(new Color(255, 204, 102)); // 金色
        rightTabs.addTab("Phase 4: 优化日志", new JScrollPane(logArea));

        // Tab 3: 中间代码
        irArea = createEditorArea();
        irArea.setForeground(new Color(152, 118, 170)); // 紫色
        rightTabs.addTab("Phase 3b: 中间代码", new JScrollPane(irArea));

        // Tab 4: 汇编代码
        asmArea = createEditorArea();
        asmArea.setForeground(new Color(106, 135, 89)); // 绿色
        rightTabs.addTab("Phase 3c: 汇编代码", new JScrollPane(asmArea));

        mainSplit.setRightComponent(rightTabs);

        // ==================== 3. 垂直分割 (底部控制台) ====================
        JSplitPane verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        verticalSplit.setTopComponent(mainSplit);
        verticalSplit.setResizeWeight(0.75); 
        verticalSplit.setDividerSize(3);
        verticalSplit.setBackground(BG_DARK);

        // --- 底部：控制台 ---
        consoleArea = new JTextArea();
        consoleArea.setBackground(new Color(20, 20, 20));
        consoleArea.setForeground(new Color(200, 200, 200));
        consoleArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        consoleArea.setEditable(false);
        consoleArea.setMargin(new Insets(5,10,5,10));
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel consoleLabel = new JLabel("  Console / GCC Output");
        consoleLabel.setFont(UI_FONT);
        consoleLabel.setForeground(Color.GRAY);
        consoleLabel.setBorder(new EmptyBorder(5,0,5,0));
        bottomPanel.setBackground(BG_DARK);
        bottomPanel.add(consoleLabel, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(consoleArea), BorderLayout.CENTER);
        
        verticalSplit.setBottomComponent(bottomPanel);
        add(verticalSplit, BorderLayout.CENTER);

        // --- 事件绑定 ---
        btnRun.addActionListener(e -> compileAndRun());
        btnLoad.addActionListener(e -> loadFile());
    }

    // ==================== 核心逻辑 ====================
    private void compileAndRun() {
        consoleArea.setText(">>> 开始编译流程...\n");
        logArea.setText("");
        irArea.setText("");
        asmArea.setText("");
        treePanel.removeAll();
        treePanel.repaint();

        try {
            // 1. 保存临时文件 (强制使用 UTF-8 编码写入)
            String sourceCode = sourceEditor.getText();
            File tempFile = new File("temp_gui.go");
            // 关键修改：使用 StandardCharsets.UTF_8
            try (PrintWriter out = new PrintWriter(tempFile, StandardCharsets.UTF_8.name())) { 
                out.print(sourceCode); 
            }

            // 2. ANTLR 解析 (强制使用 UTF-8 读取)
            // 关键修改：CharStreams.fromFileName 指定 UTF-8
            CharStream input = CharStreams.fromFileName("temp_gui.go", StandardCharsets.UTF_8);
            GoLexer lexer = new GoLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GoParser parser = new GoParser(tokens);
            ParseTree tree = parser.sourceFile();

            // 3. 展示语法树
            TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
            viewer.setScale(1.5);
            treePanel.add(viewer, BorderLayout.CENTER);
            treePanel.revalidate();
            consoleArea.append("[Success] 语法树构建完成。\n");

            // 4. 运行分析器
            SuperCompiler.Analyzer analyzer = new SuperCompiler.Analyzer();
            analyzer.visit(tree);

            // 5. 展示错误
            if (!analyzer.errors.isEmpty()) {
                consoleArea.append("\n[Error] 编译失败，发现语义错误：\n");
                for (String err : analyzer.errors) consoleArea.append(err + "\n");
                JOptionPane.showMessageDialog(this, "编译发现错误，请查看控制台。", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 6. 展示优化日志
            StringBuilder sbLog = new StringBuilder();
            if (analyzer.optimizationLogs.isEmpty()) sbLog.append("(无优化触发)\n");
            else for(String log : analyzer.optimizationLogs) sbLog.append("[OPT] ").append(log).append("\n");
            
            sbLog.append("\n--- 静态分析警告 ---\n");
            for (Map.Entry<String, SuperCompiler.SymbolInfo> entry : analyzer.symbolTable.entrySet()) {
                if (!entry.getValue().isUsed) sbLog.append("[WARN] [Line ").append(entry.getValue().line).append("] 变量 '").append(entry.getKey()).append("' 未引用。\n");
            }
            logArea.setText(sbLog.toString());

            // 7. 展示中间代码
            StringBuilder sbIR = new StringBuilder();
            for (SuperCompiler.Quadruple q : analyzer.codes) {
                if (q.op.equals("=")) sbIR.append(String.format("%-4s := %s\n", q.result, q.arg1));
                else if (q.op.equals("if")) sbIR.append(String.format("if %s goto %s\n", q.arg1, q.result));
                else if (q.op.equals("goto")) sbIR.append(String.format("goto %s\n", q.result));
                else if (q.op.equals("label")) sbIR.append(q.result).append(":\n");
                else if (q.op.equals("return")) sbIR.append("return ").append(q.arg1==null?"":q.arg1).append("\n");
                else sbIR.append(String.format("%-4s := %s %s %s\n", q.result, q.arg1, q.op, q.arg2));
            }
            irArea.setText(sbIR.toString());

            // 8. 展示汇编代码
            String asmCode = SuperCompiler.generateAssembly(analyzer.codes);
            asmArea.setText(asmCode);

            // 9. 调用 GCC 生成 EXE
            consoleArea.append("[Info] 正在生成 output.s ...\n");
            // 关键修改：保存汇编文件也用 UTF-8
            try (PrintWriter out = new PrintWriter("output.s", StandardCharsets.UTF_8.name())) { out.print(asmCode); }
            
            consoleArea.append("[Info] 调用 GCC 编译...\n");
            ProcessBuilder pb = new ProcessBuilder("gcc", "output.s", "-o", "output.exe");
            pb.redirectErrorStream(true);
            Process p = pb.start();
            
            // 读取 GCC 输出 (Windows GCC 默认 GBK)
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
            String line;
            while ((line = reader.readLine()) != null) consoleArea.append("GCC: " + line + "\n");
            
            if (p.waitFor() == 0) {
                consoleArea.append("[Success] output.exe 生成成功！\n");
                consoleArea.append(">>> 运行 output.exe ...\n");
                
                Process runP = new ProcessBuilder("output.exe").start();
                int exitCode = runP.waitFor();
                consoleArea.append(">>> 程序运行结束。\n");
                consoleArea.append(">>> 返回值 (Exit Code): " + exitCode + "\n");
                
                JOptionPane.showMessageDialog(this, "编译运行成功！\n返回值: " + exitCode, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                consoleArea.append("[Fail] GCC 编译失败，请检查环境。\n");
            }

        } catch (Exception ex) {
            consoleArea.append("[Exception] " + ex.getMessage() + "\n");
            ex.printStackTrace();
        }
    }

    private void loadFile() {
        sourceEditor.setText("func main() {\n    var a int = 10;\n    var b int;\n    b = 100 + 200; // 优化测试\n    a = a * 0;     // 代数化简\n    return b;\n}");
    }

    // ==================== 美化组件工厂 ====================
    private JTextArea createEditorArea() {
        JTextArea ta = new JTextArea();
        ta.setBackground(EDITOR_BG);
        ta.setForeground(TEXT_COLOR);
        ta.setCaretColor(Color.WHITE);
        ta.setFont(CODE_FONT);
        ta.setMargin(new Insets(10,10,10,10));
        return ta;
    }

    private JButton createModernButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(8, 15, 8, 15)); // 增加按钮内边距，显得更充实
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // 辅助方法：全局设置字体
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static void main(String[] args) {
        // 尝试启用 Nimbus 主题
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
        
        SwingUtilities.invokeLater(() -> {
            new CompilerGUI().setVisible(true);
        });
    }
}