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

vector<string> arr(104);
vector< vector<bool> > visited(104, vector<bool>(104, false));
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int white, blue;
int n, m;

int BFS(int x, int y, char target) {
    queue<pi> q;
    int cnt = 1;
    visited[x][y] = true;
    q.push({ x,y });
    while (!q.empty()) {
        
        int x, y;
        tie(x, y) = q.front();
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (visited[nx][ny]) continue;
                if (target == arr[nx][ny]) {
                    visited[nx][ny] = true;
                    cnt += 1;
                    q.push({ nx,ny });
                }
            }
        }
    }

    return cnt*cnt;
}
int main()
{

    F_I;

    cin >> m >> n;
    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;
        arr[i] = str;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (!visited[i][j]) {
                if(arr[i][j] == 'W') white+= BFS(i, j, arr[i][j]);
                else blue += BFS(i, j, arr[i][j]);
            }
        }
    }

    cout << white << ' ' << blue << '\n';

    return 0;
}