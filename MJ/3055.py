# 11:25 -> 11:50 -> 12:10
# 12:08 -> GG -> 12:22 GG
# 1:26
# TODO: retry
from collections import deque
import sys
sys_input = sys.stdin.readline

H, W = map(int, input().split())
field = []
sonic = deque([])
water = deque([])
visited = [[False] * W for _ in range(H)]
vector = [(1, 0), (-1, 0), (0, 1), (0, -1)]
g_x = 0
g_j = 0
for i in range(H):
    row = list(sys_input().strip())
    for j in range(len(row)):
        if row[j] == 'D':
            g_x, g_y = i, j
            continue
        if row[j] == 'S':
            sonic.append((i, j))
            visited[i][j] = True
            continue
        if row[j] == '*':
            water.append((i, j))
            visited[i][j] = True
            continue
        if row[j] == 'X':
            visited[i][j] = True
            continue
    field.append(row)

ans = 0
while sonic:

    for i in range(len(water)):
        x, y = water.popleft()
        for vec in vector:
            nx = x + vec[0]
            ny = y + vec[1]
            if 0 <= nx < H and 0 <= ny < W and field[nx][ny] == '.':
                water.append((nx, ny))
                field[nx][ny] = '*'
                visited[x][y] = True
    ans += 1

    for i in range(len(sonic)):
        x, y = sonic.popleft()
        for vec in vector:
            nx = x + vec[0]
            ny = y + vec[1]
            if g_x == nx and g_y == ny:
                print(ans)
                exit(0)
            if 0 <= nx < H and 0 <= ny < W:
                if visited[nx][ny] == False and field[nx][ny] == '.':
                    sonic.append((nx, ny))
                    visited[nx][ny] = True
print('KAKTUS')
