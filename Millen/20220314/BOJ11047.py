import sys
input = sys.stdin.readline

N, K = map(int, input().split())
coin = [int(input()) for i in range(N)]
answer = 0

for i in coin[::-1]:
    answer += K // i
    K = K % i

print(answer)