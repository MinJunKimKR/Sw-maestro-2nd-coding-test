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

int h, w;
int lf, rh;
int ans;

int main()
{

    F_I;

    // [백준] 14719번 : 빗물 (시뮬레이션, 구현)

    // [min(나를 기준으로 왼쪽에서 제일 큰 탑 & 나를 기준으로 오른쪽에서 제일 큰 탑) - 나의 높이]의 합을 구하면 된다.
    // https://hwan-shell.tistory.com/276 참고하기
    // 현재 위치에서 고일 수 있는 물의 양을 합산하기

    cin >> h >> w;
    vector<int> v(w);
    for (int i = 0; i < w; i++) cin >> v[i];
    for (int i = 1; i < w - 1; i++) {
        lf = rh = 0;
        for (int l = 0; l <= i - 1; l++) lf = max(lf, v[l]);
        for (int l = i + 1; l < w; l++) rh = max(rh, v[l]);

        int diff = min(lf, rh) - v[i];
        if (diff > 0) ans += diff;
    }
    cout << ans << '\n';

    return 0;
}