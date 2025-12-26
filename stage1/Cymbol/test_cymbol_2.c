int fib(int n) {
    if (n == 0) then return 0;
    if (n == 1) then return 1;
    return fib(n-1) + fib(n-2);
}

void arrayTest() {
    int arr;
    arr[0] = 1;
    arr[1] = arr[0] + 2;
}

void main() {
    int c = fib(6);
    int a = 5, b = 6;
    int test = a == b * c;
}