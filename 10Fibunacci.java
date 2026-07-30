#include <iostream>
#include <unordered_map>
using namespace std;

unordered_map<int, long long> memo;

long long fibonacci(int n) {

    if (n <= 1)
        return n;

    if (memo.count(n))
        return memo[n];

    memo[n] = fibonacci(n - 1) + fibonacci(n - 2);

    return memo[n];
}

int main() {

    for (int i = 0; i <= 20; i++)
        cout << "Fib(" << i << ") = "
             << fibonacci(i) << endl;

    return 0;
}
