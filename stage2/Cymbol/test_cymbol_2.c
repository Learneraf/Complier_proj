int fib(int n) {
    if (n == 0) then return 0;
    if (n == 1) then return 1;
    return fib(n-1) + fib(n-2);
}

void main() {
    int val = fib(6);
}