 #include <bits/stdc++.h>
// C
//#ifndef _GLIBCXX_NO_ASSERT
//#include <cassert>
//#endif
//#include <cctype>
//#include <cerrno>
//#include <cfloat>
//#include <ciso646>
//#include <climits>
//#include <clocale>
//#include <cmath>
//#include <csetjmp>
//#include <csignal>
//#include <cstdarg>
//#include <cstddef>
//#include <cstdio>
//#include <cstdlib>
//#include <cstring>
//#include <ctime>
//
//#if __cplusplus >= 201103L
//#include <ccomplex>
//#include <cfenv>
//#include <cinttypes>
//#include <cstdalign>
//#include <cstdbool>
//#include <cstdint>
//#include <ctgmath>
//#include <cwchar>
//#include <cwctype>
//#endif
//
//// C++
//#include <algorithm>
//#include <bitset>
//#include <complex>
//#include <deque>
//#include <exception>
//#include <fstream>
//#include <functional>
//#include <iomanip>
//#include <ios>
//#include <iosfwd>
//#include <iostream>
//#include <istream>
//#include <iterator>
//#include <limits>
//#include <list>
//#include <locale>
//#include <map>
//#include <memory>
//#include <new>
//#include <numeric>
//#include <ostream>
//#include <queue>
//#include <set>
//#include <sstream>
//#include <stack>
//#include <stdexcept>
//#include <streambuf>
//#include <string>
//#include <typeinfo>
//#include <utility>
//#include <valarray>
//#include <vector>
//
//#include <array>
//#include <atomic>
//#include <chrono>
//#include <condition_variable>
//#include <forward_list>
//#include <future>
//#include <initializer_list>
//#include <mutex>
//#include <random>
//#include <ratio>
//#include <regex>
//#include <scoped_allocator>
//#include <system_error>
//#include <thread>
//#include <tuple>
//#include <typeindex>
//#include <type_traits>
//#include <unordered_map>
//#include <unordered_set>

#define F_I ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0); // F_I 사용 시 주의) scanf와 printf 사용 금지, 오로지 cin , cout 만 사용!
#define INF 987654321

using namespace std;

typedef long long ll;
typedef pair<ll, ll> pl;
typedef pair<int, int> pi;

ll Min(ll a, ll b) { return (a < b) ? a : b; }
ll Max(ll a, ll b) { return (a < b) ? b : a; }
ll gcd(ll m, ll n) { if (n == 0) return m; return gcd(n, m % n); } //최대공약수
ll lcm(ll m, ll n) { return m * n / gcd(m, n); } //최소공배수
int n;
int mx = -1e9, mn = 1e9;
int v[15]; // 숫자들 저장
int cal[15]; // 연산자들의 조합을 저장
int op[5]; // 연산자별 사용 가능한 횟수

// 해설 : 사용 가능한 연산자들을 가지고 만들 수 있는 조합을 구하고, 하나의 조합(경우의 수)을 구할 때마다 계산을 해주면 된다.

void Calc() { // 하나의 조합(경우의 수)을 구할 때마다 계산을 해주면 된다.
    int sum = v[0];
    for (int i = 0; i < n - 1; i++) {
        switch (cal[i]) {
            case 0:
                sum += v[i + 1];
                break;
            case 1:
                sum -= v[i + 1];
                break;
            case 2:
                sum *= v[i + 1];
                break;
            case 3:
                sum /= v[i + 1];
                break;
        }
    }
    
    mx = max(mx, sum);
    mn = min(mn, sum);

    return;
}

void DFS(int idx) { // 사용 가능한 연산자들을 가지고 만들 수 있는 조합을 구하고
    
    if (idx >= n-1) {
        Calc();
        return;
    }
    for (int i = 0; i < 4; i++) {
        if (op[i] == 0) continue;

        op[i] -= 1;
        cal[idx] = i;

        DFS(idx + 1);

        op[i] += 1;
        cal[idx] = 0;

    }
}
int main()
{

    // F_I : 코테 때 필수! ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0); 암기해놓기!
    F_I;


    cin >> n;
    for (int i = 0; i < n; i++) cin >> v[i];
    for (int i = 0; i < 4; i++) cin >> op[i];
    DFS(0);

    cout << mx << '\n' << mn << '\n';

    return 0;
}