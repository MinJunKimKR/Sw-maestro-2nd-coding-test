import sys
from collections import deque
input = sys.stdin.readline

dx, dy = [1, -1, 0, 0], [0, 0, -1, 1]
def BFS(x:int, y:int):
    q = deque()
    q.append([x, y])
    board[x][y] = 0

    while q:
        a, b = q.popleft()
        
        for i in range(4):
            nx = a + dx[i]
            ny = b + dy[i]

            if 0 <= nx < M and 0 <= ny < N and board[nx][ny] == 1:
                board[nx][ny] = 0
                q.append([nx, ny])



for T in range(int(input())):
    M, N, K = map(int, input().split())
    board = [[0]*N for _ in range(M)]
    answer = 0

    for i in range(K):
        x, y = map(int, input().split())
        board[x][y] = 1

    for i in range(M):
        for j in range(N):
            if board[i][j] == 1:
                BFS(i, j)
                board[i][j] = 0
                answer += 1

    print(answer)
