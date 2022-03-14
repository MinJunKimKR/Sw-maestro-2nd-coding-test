import sys

input = sys.stdin.readline

def find(a):
    if friends[a] == a:
        return a

    friends[a] = find(friends[a])
    return friends[a]

def union(a,b):
    a = find(a)
    b = find(b)
    if a != b :
        friends[b] = a
        nums[a] += nums[b]

N = int(input())


for i in range(N):
    F = int(input())
    friends = dict()
    nums = dict()

    for j in range(F):

        friend1 , friend2 = input().split()
        if friend1 not in friends.keys():
            friends[friend1] = friend1
            nums[friend1] = 1
        if friend2 not in friends.keys():
            friends[friend2] = friend2
            nums[friend2] = 1
        union(friend1,friend2)
        check = find(friend1)
        print(nums[check])





