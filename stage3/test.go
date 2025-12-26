func main() {
    var a int = 10;
    var b int = 20;
    var c int;
    
    // 测试运算优先级
    c = a + b * 2;
    
    // 测试 if-else
    if c > 40 {
        c = c - 1;
    } else {
        c = 0;
    }

    // 测试循环
    for i = 0; i < 5; i = i + 1 {
        c = c + 1;
    }
}