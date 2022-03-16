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

int n, k;
vector<int> dist(100004, -1);

int main()
{

    F_I;

    cin >> n >> k;
    dist[n] = 0;
    queue<int> q;
    q.push(n);
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        for (int t : {x - 1, x + 1, 2 * x}) {
            if (t < 0 || t>100000) continue;
            if (dist[t] != -1) continue;
            if (dist[t] == -1 || dist[t] > dist[x] + 1) {
                dist[t] = dist[x] + 1;
                q.push(t);
            }
        }
    }
    cout << dist[k];
    
    return 0;
}