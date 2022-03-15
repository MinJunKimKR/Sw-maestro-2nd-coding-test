# 5:02 -> 5:40
# 5:50 -> 6:20 5:55 [success]
from collections import deque
import sys
sys_input = sys.stdin.readline

N, M = map(int, input().split())
INF = int(1e9)
graph = [[] for _ in range(N+1)]
min_dist = INF
ans = ''
for _ in range(M):
    a, b = map(int, sys_input().strip().split())
    graph[a].append(b)
    graph[b].append(a)


for i in range(1, N+1):
    q = deque([])
    q.append((i, 0))
    dist = [INF] * (N+1)
    dist[i] = 0
    dist[0] = 0
    while q:
        node, n_dist = q.popleft()
        if dist[node] < n_dist:
            continue
        for next in graph[node]:
            if dist[next] > dist[node]+1:
                dist[next] = dist[node]+1
                q.append((next, dist[node]+1))
    tot_dist = sum(dist)
    if min_dist > tot_dist:
        ans = i
        min_dist = tot_dist
print(ans)
