// test_error.c - 包含语义错误的代码

int x;
int x; // 错误：重复定义

void f(int a, int b) {
    int y;
    y = a + x;   // 正确：a, x 都有定义
    z = b + 1;   // 错误：z 没定义
    y = c;       // 错误：c 没定义
}