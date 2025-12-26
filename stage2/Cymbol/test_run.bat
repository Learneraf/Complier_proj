@echo off
chcp 65001 > nul

:: ========== 配置项 ==========
set "ANTLR_JAR_PATH=D:\Edge_download\antlr-4.13.2-complete.jar"

set "GRAMMAR_FILE=Cymbol.g4"
:: 语法文件名
set "MAIN_CLASS=CallGraph"
:: 主类名
set "TEST_FILE1=test_cymbol_1.c"
set "TEST_FILE2=test_cymbol_2.c"

:: 步骤1：生成ANTLR4解析器代码
echo.
echo 1. 正在生成解析器代码...
java -cp "%ANTLR_JAR_PATH%;." org.antlr.v4.Tool .\%GRAMMAR_FILE%
if %errorlevel% neq 0 (
    echo 错误：生成解析器代码失败！请检查jar包路径和CSV.g4是否存在。
    pause
    exit /b 1
)
echo 解析器代码生成成功！

:: 步骤2：编译所有Java文件
echo.
echo 2. 正在编译所有Java文件...
javac -encoding UTF-8 -cp "%ANTLR_JAR_PATH%;." *.java
if %errorlevel% neq 0 (
    echo 错误：编译Java文件失败！请检查LoadCSV.java是否存在。
    pause
    exit /b 1
)
echo Java文件编译成功！

:: 步骤3：运行第一个测试文件
echo.
echo 3. 正在运行测试文件：%TEST_FILE1%
java -cp "%ANTLR_JAR_PATH%;." %MAIN_CLASS% %TEST_FILE1%
if %errorlevel% neq 0 (
    echo 错误：运行%TEST_FILE1%失败！请检查测试文件是否存在。
    pause
    exit /b 1
)

:: 步骤4：运行第二个测试文件
echo.
echo 4. 正在运行测试文件：%TEST_FILE2%
java -cp "%ANTLR_JAR_PATH%;." %MAIN_CLASS% %TEST_FILE2%
if %errorlevel% neq 0 (
    echo 错误：运行%TEST_FILE2%失败！请检查测试文件是否存在。
    pause
    exit /b 1
)

echo.
echo ========== 所有任务执行完成！ ==========