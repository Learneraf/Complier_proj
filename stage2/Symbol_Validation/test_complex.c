// test_complex.c - 复杂语义测试

// 1. 定义全局变量
int g_score;
int g_id;

void funcA(int arg1) {
    // [测试点 A] 遮蔽 (Shadowing) - 合法
    // 这里定义了一个局部变量 g_score，它应该覆盖全局的 g_score
    // 验证器不应该报错，因为它是当前作用域的新变量
    int g_score; 
    
    g_score = 100; // 这里使用的是局部的 g_score
    g_id = 888;    // 这里使用的是全局的 g_id (因为没有局部变量叫 g_id)

    // [测试点 B] 参数冲突 - 错误
    // arg1 已经在参数列表里定义过了，这里再次定义应该报错
    int arg1; 

    // [测试点 C] 先用后定义 - 错误
    // 计算机读到 x = unknown + 1 时，unknown 还没定义
    // 虽然下一行定义了 unknown，但这里应该报错
    int x = unknown + 1;
    int unknown;
}

void funcB() {
    // [测试点 D] 跨函数访问 - 错误
    // x 是 funcA 的私有变量，funcB 看不到
    x = 200; 

    // [测试点 E] 正常访问全局
    g_score = 500; 
}