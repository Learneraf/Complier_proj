int gcd(int a, int b) {
    if (b == 0) then return a;
    return gcd(b, a + -1 * (a/b) * b); 
}

void main() {
    int result = gcd(10, 5);
}