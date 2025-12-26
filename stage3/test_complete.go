func main() {
    var a int = 10;
    var b int;
    var unused int; // [WARN] 未使用变量
    
    // [OPT] 常量折叠: 应该变成 b = 300
    b = 100 + 200;

    // [OPT] 代数化简: 应该变成 a = 0
    a = a * 0;

    // 基础逻辑 (体现 3b/3c)
    if b > 50 {
        a = a + 1;
    }

    return 0;

    // [OPT] 死代码: 这句不应该生成汇编
    a = 999;
}