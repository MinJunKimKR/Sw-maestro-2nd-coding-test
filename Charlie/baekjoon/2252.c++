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

    // [백준] 2252번 : 줄 세우기 (위상정렬, 그래프)

    // 도움 받은 글 : https://maluchisoft.com/wp/2020/09/30/cplusplus-topological-sort-algorithm/

    int n, m;
    cin >> n >> m;

    vector< vector<int> > v(n + 1);
    vector<int> indegree(n + 1, 0);
    queue<int> q;

    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        v[a].push_back(b);
        indegree[b] += 1;
    }

    for (int i = 1; i <= n; i++) {
        if (indegree[i] == 0)
        {
            q.push(i);
        }
    }

    while (!q.empty()) {
        int x = q.front();
        q.pop();
        cout << x << ' ';
        for (int t : v[x]) {
            indegree[t] -= 1;
            if (indegree[t] == 0)
            {
                q.push(t);
            }
        }
    }

    return 0;
}