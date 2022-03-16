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
vector<int> cnt(100004, 0);

int main()
{

    F_I;

    cin >> n >> k;
    dist[n] = 0;
    cnt[n] = 1;
    queue<int> q;
    q.push(n);
    while (!q.empty()) {
        int now = q.front();
        q.pop();

        for (int next : {now - 1, now + 1, 2 * now}) {
            if (next < 0 || next>100000) continue;
            if (dist[next] == -1) { // 처음 방문했을 때
                dist[next] = dist[now] + 1;
                cnt[next] = cnt[now];
                q.push(next);
            }
            else {
                // a -> b 로 올 때의 최단 거리가 3이고,  c -> b 로 올 때의 최단 거리가 3이라면, 이 두 가지의 경로로 오는 방법의 수를 더해줘야 한다.
                if (dist[next] == dist[now] + 1) { // 처음 방문은 아닌데, 최단 거리가 같으면, 이번 경우도 방법에 포함해야 한다!
                    cnt[next] += cnt[now];
                    // q.push(next); // 주의!
                }
            }
        }
    }
    cout << dist[k] << '\n' << cnt[k] << '\n';
    
    return 0;
}