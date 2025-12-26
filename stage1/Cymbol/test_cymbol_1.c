int gcd(int a, int b) {
    if (b == 0) then return a;
    return gcd(b, a + -1 * (a/b) * b); 
}

int exprTest() {
    int a = 1 + 2 * 3;      // 应该是7，不是9
    int b = (1 + 2) * 3;    // 应该是9
    int c = -a + b;
    int d = !(a == b);
    return a;
}

void main() {
    int result = gcd(10, 5) / 3 + 10 * 5;
}