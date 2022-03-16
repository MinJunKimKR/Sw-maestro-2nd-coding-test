# 4:40 -> 5:10
# 4:59 [Success]
import sys
from collections import deque
sys_input = sys.stdin.readline
H, W = map(int, input().split())
field = []
vector = [(1, 0), (-1, 0), (0, 1), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1)]
for _ in range(H):
    field.append(list(map(int, sys_input().strip().split())))

q = deque([])
visited = [[False] * W for _ in range(H)]
for i in range(H):
    for j in range(W):
        if field[i][j] == 1:
            q.append((i, j))

ans = 0
max_ans = 0
while q:
    ans += 1
    for _ in range(len(q)):
        x, y = q.popleft()
        if visited[x][y]:
            continue
        visited[x][y] = True
        if field[x][y] == 0:
            field[x][y] = ans
        else:
            field[x][y] = min(ans, field[x][y])
        max_ans = max(max_ans, field[x][y])
        for vec in vector:
            nx = x+vec[0]
            ny = y + vec[1]
            if 0 <= nx < H and 0 <= ny < W and not visited[nx][ny]:
                q.append((nx, ny))
print(max_ans-1)
