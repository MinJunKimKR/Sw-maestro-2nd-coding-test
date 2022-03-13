import sys
import itertools
input = sys.stdin.readline

def check(string):
    count = 0
    for alpha in string:
        if alpha == 'a' or alpha == 'e' or alpha == 'i' or alpha == 'o' or alpha == 'u':
            count += 1
    return count

L, C = map(int, input().split())
s = sorted(list(map(str, input().rstrip().split())))

for i in list(itertools.combinations(s, L)):
    tmp = check(i)
    if tmp >= 1 and L - tmp >= 2:
        print(''.join(i))