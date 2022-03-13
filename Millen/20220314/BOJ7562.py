import sys
from collections import deque
input = sys.stdin.readline
dx = [-1, -2, -2, -1, 1, 2, 2, 1]
dy = [2, 1, -1, -2, -2, -1, 1, 2]


def BFS(x1: int, y1: int, x2: int, y2: int) -> int:
    q = deque()
    q.append([x1, y1])
    board[x1][y1] = 1

    while q:
        a, b = q.popleft()
        if a == x2 and b == y2:
            print(board[x2][y2] - 1)
            return

        for i, j in zip(dx, dy):
            x = a + i
            y = b + j

            if 0 <= x < I and 0 <= y < I and board[x][y] == 0:
                q.append([x, y])
                board[x][y] = board[a][b] + 1


for T in range(int(input())):
    I = int(input())
    x1, y1 = map(int, input().split())
    x2, y2 = map(int, input().split())
    board = [[0] * I for _ in range(I)]
    BFS(x1, y1, x2, y2)
