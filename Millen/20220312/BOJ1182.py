import sys
input = sys.stdin.readline

n, target = map(int, input().split())
arr = list(map(int, input().split()))
answer = 0

def subset_sum(idx, sub_sum):
    global answer

    if idx >= n:
        return

    sub_sum += arr[idx]

    if sub_sum == target:
        answer += 1
    
    subset_sum(idx+1, sub_sum)

    subset_sum(idx+1, sub_sum - arr[idx])

subset_sum(0, 0)
print(answer)