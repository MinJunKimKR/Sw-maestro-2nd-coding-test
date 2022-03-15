import sys


N = int(input())

Map = []
for i in range(N):
    Map.append(list(map(int,input().split())))


dp = [[0 for i in range(N) ]for j in range(N)]
move = [[1,0],[-1,0],[0,1],[0,-1]]
def find(now):
    if dp[now[0]][now[1]] != 0 :
        return(dp[now[0]][now[1]] )


    max_value = 1
    for i in move:
        n_r = now[0] + i[0]
        n_c = now[1] + i[1]
        if n_r >= N or n_c >= N or n_r < 0 or n_c < 0 :
            continue
        if Map[now[0]][now[1]] < Map[n_r][n_c]:
            max_value = max(max_value,find([n_r,n_c]) +1)

    dp[now[0]][now[1]] = max_value
    return max_value
max_value = 0
for i in range(N):
    for j in range(N):
        if dp[i][j] == 0 :
            max_value = max(max_value,find([i,j]))

print(max_value)


