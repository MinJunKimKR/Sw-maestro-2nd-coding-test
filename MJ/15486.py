# 5:45 -> 6:10
# 6:20 [FAIL]
# 6:43
import sys
sys_input = sys.stdin.readline

N = int(input())
booking = []
dp = [0] * (N+1)

for _ in range(N):
    time, pay = map(int, sys_input().strip().split())
    booking.append((time, pay))
max_pay = 0
for now in range(0, N):
    max_pay = max(max_pay, dp[now])
    time = booking[now][0]
    pay = booking[now][1]
    if now+time > N:
        continue
    dp[now] = max_pay
    dp[now+time] = max(dp[now]+pay, dp[now+time])
# print(dp[N])
print(max(dp))


# import sys
# input = sys.stdin.readline

# n = int(input())
# t, p = [], []
# dp = [0] * (n+1)
# for i in range(n):
#     x, y = map(int, input().split())
#     t.append(x)
#     p.append(y)
# M = 0
# for i in range(n):
#     M = max(M, dp[i])
#     dp[i] = M
#     if i+t[i] > n:
#         continue
#     dp[i+t[i]] = max(M+p[i], dp[i+t[i]])
# print(max(dp))
# [0, 0, 0, 0, 0, 50, 60, 60, 80, 0, 90]


# input
# 10
# 5 50
# 4 40
# 3 30
# 2 20
# 1 10
# 1 10
# 2 20
# 3 30
# 4 40
# 5 50
