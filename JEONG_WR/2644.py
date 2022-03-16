import sys
from collections import deque
input = sys.stdin.readline


N = int(input())
fr , to = map(int,input().split())
fr -=1
to -=1
M = int(input())
Map = [[]for i in range(N)]
for i in range(M):
    x, y = map(int, input().split())
    x -=1
    y -=1

    Map[x].append(y)
    Map[y].append(x)

visited = [0 for i in range(N)]
queue = deque([[fr,0]])
ans = -1
while queue:
    now = queue.popleft()
    if now[0] == to:
        ans = now[1]
        break

    for new in Map[now[0]]:
        if visited[new] == 0:
            visited[new] = 1
            queue.append([new,now[1]+1])

print(ans)
