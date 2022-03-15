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

int plug[104];
int schedule[104];
int n, k;
int result;

int main()
{

    F_I;

    // [백준] 1700번 : 멀티탭 스케줄링 (그리디 알고리즘)

    // 도움 받은 글 : https://jaimemin.tistory.com/759

    cin >> n >> k;
    for (int i = 0; i < k; i++) cin >> schedule[i];

    for (int i = 0; i < k; i++) {

        int nowSchedule = schedule[i]; // 이번 스케쥴!
        bool plugged = false;

        // 1) 이미 해당 기기가 플러그에 꽂혀있는 경우
        for (int j = 0; j < n; j++) {
            if (plug[j] == nowSchedule) {
                plugged = true;
                break;
            }
        }
        if (plugged) continue;

        // 2) 1번의 경우는 아니지만, 아직 빈 플러그가 남아있는 경우 (거기다가 꽂으면 됨)
        for (int j = 0; j < n; j++) {
            if (!plug[j]) {
                plug[j] = nowSchedule;
                plugged = true;
                break;
            }
        }
        if (plugged) continue;

        // 3) 1,2번의 경우에 해당되지 않아서, 하나의 플러그를 빼고 나서 꽂아야 한다. 
        // 어떤 전자기기가 연결되어 있는 플러그를 뺄까? => 앞으로 쓰지 않을 전자기기 or 가장 마지막에 사용될 전자기기가 꽂혀있는 플러그 빼기 (그리디로 결정)
        int idx, deviceIdx = -1;

        for (int j = 0; j < n; j++)
        {
            int lastIdx = 0;
            for (int l = i + 1; l < k; l++) // i+1 번째 ~ k-1 번째 schedule 배열만 탐색 => 편하게 이것을 schedule 배열이라고 밑에서 이야기 할 것임.
            {
                // j번째 plug에 꽂혀있는 전자기기는, lastIdx번째 schedule 배열에서 쓰일 것이라는 의미!
                // lastIdx가 클 수록, j번째 plug에 꽂혀있는 전자기기는 우선순위에서 밀려난다 (가장 마지막에 사용될 전자기기라는 뜻)
                if (plug[j] == schedule[l]) break;
                lastIdx++;
            }
            if (lastIdx > deviceIdx)
            {
                // 최종적으로 나오게 된 idx번째의 플러그에 꽂혀있는 전자기기를 뽑아야 함
                idx = j; 
                deviceIdx = lastIdx; 
            }
        }
        result++;

        // 찾은 위치에다가 전자기기 꼽는 행위 (idx번째 플러그에 nowSchedule(= schedule[i]) 꼽기)
        plug[idx] = nowSchedule;
    }

    cout << result << '\n';

    return 0;
}