import sys
from collections import deque
input = sys.stdin.readline

def BFS():
    while q:
        a, b, c = q.popleft()

        for i in range(6):
            z = a + dz[i]
            x = b + dx[i] 
            y = c + dy[i]

            if 0 <= z < K and 0 <= x < N and 0 <= y < M and board[z][x][y] == 0:
                board[z][x][y] = board[a][b][c] + 1
                q.append([z, x, y])

dz = [0, 1, 0, -1, 0, 0]
dx = [0, 0, -1, 0, 1, 0]
dy = [-1, 0, 0, 0, 0, 1]

M, N, K = map(int, input().split())
board = [[list(map(int, input().split())) for i in range(N)] for j in range(K)]
q = deque()
answer = 0

for k in range(K):
    for i in range(N):
        for j in range(M):
            if board[k][i][j] == 1:
                q.append([k, i, j])

BFS()
for k in board:
    for i in k:
        for j in i:
            if j == 0:
                print(-1)
                exit(0)
        answer = max(answer, max(i))
print(answer - 1)