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

int n, k, idx, cnt;
string words[54];
bool visited[26];
int ans;
int temp;

// 도움 받은 글 : https://jaimemin.tistory.com/927

void getMax(int start, int cnt) {
    if (cnt == k) { // k개를 배웠을 때
        temp = 0;
        for (int i = 0; i < n; i++) {

            string cmp = words[i];
            bool success = true;

            for (int len = 0; len < cmp.length(); len ++) {
                if (!visited[cmp[len] - 'a']) {
                    success = false;
                    break;
                }
            }

            if (success) {
                temp += 1;
            }
        }
        ans = max(ans, temp);
        return;
    }

    for (int idx = start; idx < 26; idx++) {
        if (visited[idx]) continue;
        visited[idx] = true;
        getMax(idx + 1, cnt + 1);
        visited[idx] = false;
    }
    return;
}
int main()
{
    F_I;

    // [백준] 1062번 : 가르침 (브루트 포스 , 백트래킹)

    cin >> n >> k;
    
    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;
        str = str.substr(4);
        reverse(str.begin(), str.end());
        str=str.substr(4);
        reverse(str.begin(), str.end());
        words[i] = str;
    }

    if (k < 5) {
        cout << 0 << '\n';
        return 0;
    }

    visited['a' - 'a'] = true;
    visited['c' - 'a'] = true;
    visited['i' - 'a'] = true;
    visited['n' - 'a'] = true;
    visited['t' - 'a'] = true;

    k -= 5;

    getMax(idx, cnt);

    cout << ans << '\n';

    return 0;
}