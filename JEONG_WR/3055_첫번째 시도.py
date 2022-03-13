import sys
from collections import deque

input = sys.stdin.readline

N , M = map(int,input().split())

Map = []

for i in range(N):
    Map.append(list(input().replace("\n","")))
D = 0
S = 0
water = []
for i in range(N):
    for j in range(M):
        if Map[i][j] == "S":
            D = [i,j,"S",0]
        if Map[i][j] == "*":
            water.append([i,j,"*"])
        if Map[i][j] == "D":
            S = [i,j]

queue = []

for i in range(len(water)):
    queue.append(water[i])
queue.append(D)
queue = deque(queue)

move = [[1,0],[-1,0],[0,1],[0,-1]]

answer = "KAKTUS"
while queue:
    now = queue.popleft()


    if now[2] == "S" and now[0] == S[0] and now[1] == S[1]:
        answer = now[3]
        break

    if now[2] == "*":
        for i in move:
            n_r = now[0] + i[0]
            n_c = now[1] + i[1]
            if n_r >= N or n_c >= M or n_r < 0 or n_c < 0:
                continue

            if Map[n_r][n_c] == "." :

                Map[n_r][n_c] = "*"
                queue.append([n_r,n_c,"*"])

    elif now[2] == "S" :
        for i in move:
            n_r = now[0] + i[0]
            n_c = now[1] + i[1]
            if n_r >= N or n_c >= M or n_r < 0 or n_c < 0:
                continue
            if Map[n_r][n_c] == "." or Map[n_r][n_c] == "D":
                Map[n_r][n_c] = "S"
                queue.append([n_r,n_c,"S",now[3]+1])



print(answer)