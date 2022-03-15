#include <bits/stdc++.h>
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

int main()
{

    F_I;

    // [백준] 1806번 : 부분합 (최소길이 찾기) (투 포인터)

    int n, s;
    cin >> n >> s;
    vector<int> v(n);
    for (int i = 0; i < n; i++) cin >> v[i];

    int a = 0, b = 0;
    int sum = v[0];
    int res = 1e6;
    while (a <= b) {
        if (a >= n || b >= n) break;
        if (sum < s) {
            b += 1;
            if (b < n) sum += v[b];
        }
        else {
            res = min(res, b - a + 1);
            sum -= v[a];
            a += 1;
        }    
    }
    if (res == 1e6) cout << 0 << '\n';
    else cout << res << '\n';
    
    return 0;
}