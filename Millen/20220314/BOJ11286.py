import heapq
import sys
input = sys.stdin.readline

heap = []; answer=[]
for i in range(int(input())):
    x = int(input())
    if x:
        heapq.heappush(heap, (abs(x), x))
    else:
        if len(heap):
            answer.append(heapq.heappop(heap)[1])
        else:
            answer.append(0)

print(*answer, sep='\n')