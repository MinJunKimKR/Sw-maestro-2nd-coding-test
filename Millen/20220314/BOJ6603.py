import sys
import itertools 
input = sys.stdin.readline

while 1:
    nums = list(map(int, input().rstrip().split()))

    if nums[0] == 0:
        break

    res = sorted(nums[1:])

    for i in itertools.combinations(res, 6):
        print(*i)

    print("")
