import sys
from collections import deque
input = sys.stdin.readline

dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
M,N = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
q = deque([])
res = 0 

for i in range(N):
    for j in range(M):
        if board[i][j] == 1:
            q.append([i, j])

def BFS():
    while q:
        a, b = q.popleft()

        for i in range(4):
            x = a + dx[i]
            y = b + dy[i]

            if 0 <= x < N and 0 <= y < M and board[x][y] == 0:
                board[x][y] = board[a][b] + 1
                q.append([x, y])

BFS()
for i in board:
    for j in i:
        if j == 0:
            print(-1)
            exit(0)
    res = max(res, max(i))
print(res - 1)
