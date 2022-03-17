import sys
from collections import deque
import heapq

input = sys.stdin.readline

N, M = map(int,input().split())
Map = [[] for i in range(N)]

for i in range(M):
    a , b , c = map(int,input().split())
    a -=1
    b -=1

    Map[a].append([b,c])
    Map[b].append([a, c])
fr , to = map(int,input().split())
fr-=1
to-=1

queue = []
for i in Map[fr]:
    heapq.heappush(queue,[-i[1],i[0]])


max_value = 0
visited = [0 for i in range(N)]
visited[fr] = 1
while queue:
    now = heapq.heappop(queue)
    now[0] = now[0]*(-1)

    #print("NOW {} and Weight {}".format(now[0],now[1]))

    if now[1] == to:
        max_value = max(max_value,now[0])
        break
    visited[now[1]] = 1

    for i in Map[now[1]]:
        if visited[i[0]] == 0:

            a = min(now[0],i[1])
            heapq.heappush(queue,[-a,i[0]])



print(max_value)