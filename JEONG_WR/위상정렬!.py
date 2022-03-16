

N , M  = map(int,input().split())

indegree = [ 0 for i in range(N)]
Map = [[] for i in range(N)]

for i in range(M):
    fr , to = map(int,input().split())
    fr , to = fr-1 , to -1

    Map[fr].append(to)
    indegree[to] +=1



queue = []
result = []
for i in range(N):
    if indegree[i] == 0 :
        queue.append(i)

while queue:
    now = queue.pop(0)
    result.append(now)
    for i in Map[now]:
        indegree[i] -=1
        if indegree[i] == 0 :
            queue.append(i)

for i in range(len(result)):
    result[i] +=1

print(" ".join(list(map(str,result))))