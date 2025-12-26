func main() {
    var a int = 100;
    var b int;
    
    // 代数化简：编译器应该直接生成 b = 0，而不是做乘法
    b = a * 0;
    
    // 代数化简：编译器应该直接生成 b = a，而不是做加法
    b = a + 0;

    return 0;
    
    // 死代码：下面这行永远不会执行
    // 编译器应该提示 Dead code eliminated，并且生成的中间代码里没有这一行
    a = 555;
}