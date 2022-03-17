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

int arr[1004][4];
int d[1004][4];

int main()
{
	F_I;
	int n;
	cin >> n;
	for (int i = 1; i <= n; i++)
	{
		for (int j = 0; j < 3; j++)
			cin >> arr[i][j];
	}
	d[1][0] = arr[1][0];
	d[1][1] = arr[1][1];
	d[1][2] = arr[1][2];

	for (int i = 2; i <= n; i++)
	{
		d[i][0] = min(d[i - 1][2], d[i - 1][1]) + arr[i][0];
		d[i][1] = min(d[i - 1][2], d[i - 1][0]) + arr[i][1];
		d[i][2] = min(d[i - 1][0], d[i - 1][1]) + arr[i][2];
	}
	int min = 1e9;
	for (int i = 0; i < 3; i++)
	{
		if (min >= d[n][i])
			min = d[n][i];
	}
	cout << min << '\n';
}