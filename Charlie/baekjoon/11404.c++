#include <bits/stdc++.h>
#define F_I ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0); // F_I 사용 시 주의) scanf와 printf 사용 금지, 오로지 cin , cout 만 사용!
#define INF 987654321

using namespace std;

typedef long long ll;
typedef pair<ll, ll> pl;
typedef pair<int, int> pi;

ll Min(ll a, ll b) { return (a < b) ? a : b; }
ll Max(ll a, ll b) { return (a < b) ? b : a; }
ll gcd(ll m, ll n) { if (n == 0) return m; return gcd(n, m % n); } // 최대공약수
ll lcm(ll m, ll n) { return m * n / gcd(m, n); } // 최소공배수

int d[104][104];
int n, m;

int main()
{

    F_I;

    cin >> n >> m;
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n; j++) {
            if (i == j) d[i][j] = 0;
            else d[i][j] = INF; // d[i][j]에 -1과 같은 작은 숫자말고 INF 넣기
        }
    }
    
    for (int i = 0; i < m; i++) {
        int a, b, cost;
        cin >> a >> b >> cost;
        if (d[a][b] > cost) d[a][b] = cost;
    }

    for (int k = 1; k<= n; k++) {
        for (int i= 1; i <= n; i++) {
            for (int j = 1; j<= n; j++) {
                if (d[i][j] > d[i][k] + d[k][j]) d[i][j] = d[i][k] + d[k][j];
            }
        }
    }
   
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (d[i][j] == INF) cout << 0 << ' ';
            else cout << d[i][j] << ' ';
        }
        cout << '\n';
    }
    return 0;
}