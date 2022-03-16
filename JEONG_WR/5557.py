import sys
from collections import deque
input = sys.stdin.readline


N = int(input())

nums = list(map(int,input().split()))
compare = nums[-1]
nums = nums[:-1]
dp = [[0 for i in range(21)] for i in range(N-1)]
dp[0][nums[0]] = 1
c = 0
for i in range(N-1):
    for j in range(21):
        if dp[i-1][j] != 0 :
            if 0<=j + nums[i]<=20:
                dp[i][j+nums[i]] += dp[i-1][j]
            if 0<=j - nums[i]<=20:
                dp[i][j-nums[i]] += dp[i-1][j]

print(dp[-1][compare])