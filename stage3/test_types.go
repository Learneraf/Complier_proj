func main() {
    var a int = 10;
    var b float = 3.14;
    var unusedVar int; // 这个变量没用，应该报 Warning
    
    // 错误：试图把 float 赋值给 int
    // 编译器应该报错：Type Mismatch
    a = b; 
}