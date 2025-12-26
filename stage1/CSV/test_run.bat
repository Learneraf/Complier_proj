@echo off
:: 关闭命令回显（避免终端输出冗余的命令本身，只显示执行结果）
chcp 65001 > nul
:: 设置编码为UTF-8，避免中文乱码

:: ============== 配置项==============
set "ANTLR_JAR_PATH=D:\Edge_download\antlr-4.13.2-complete.jar"
:: ANTLR4完整jar包路径
set "GRAMMAR_FILE=CSV.g4"
:: .g4语法文件名
set "TEST_FILE=test_csv_2.txt"
:: 测试文件名称
set "GRAMMAR_NAME=CSV"
:: 语法名称（和.g4文件中的grammar名称一致，如CSV、Expr）
set "START_RULE=file"
:: 起始规则名称（和.g4语法中的起始规则一致，如file、expr）

echo ============== 开始执行ANTLR4相关命令 ==============
echo 1. 正在生成解析器代码...
:: 步骤1：生成ANTLR4解析器代码
java -cp "%ANTLR_JAR_PATH%;." org.antlr.v4.Tool .\%GRAMMAR_FILE%
:: 判断步骤1是否执行成功
if %errorlevel% neq 0 (
    echo 错误：生成解析器代码失败！请检查jar包路径和语法文件是否存在。
    pause
    exit /b 1
)
echo 生成解析器代码成功！

echo.
echo 2. 正在编译Java文件...
:: 步骤2：编译所有CSV开头的Java文件
javac -cp "%ANTLR_JAR_PATH%;." %GRAMMAR_NAME%*.java
:: 判断步骤2是否执行成功
if %errorlevel% neq 0 (
    echo 错误：编译Java文件失败！请检查生成的Java文件是否存在。
    pause
    exit /b 1
)
echo 编译Java文件成功！

echo.
echo 3. 正在执行语法测试（可视化AST）...
:: 步骤3：执行TestRig测试
java -cp "%ANTLR_JAR_PATH%;." org.antlr.v4.gui.TestRig %GRAMMAR_NAME% %START_RULE% -gui .\%TEST_FILE%
if %errorlevel% neq 0 (
    echo 错误：测试语法失败！请检查测试文件和起始规则是否正确。
    pause
    exit /b 1
)
echo 测试语法成功！AST可视化窗口已弹出。

echo.
echo ============== 所有命令执行完成！ ==============