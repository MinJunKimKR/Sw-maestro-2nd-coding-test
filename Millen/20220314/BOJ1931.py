import sys
input = sys.stdin.readline

N = int(input())
schedule = [list(map(int, input().split())) for _ in range(N)]

schedule.sort(key=lambda a: a[0])
schedule.sort(key=lambda a: a[1])

last = 0 
conut = 0 

for i, j in schedule:
    if i >= last: 
        conut += 1
        last = j

print(conut)