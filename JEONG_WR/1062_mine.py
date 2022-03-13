import sys

input = sys.stdin.readline
N , K = map(int,input().split())

word = []
for i in range(N):
    word.append(input().replace("\n",""))


alphabet = {"a","t","i","c","n"}
trial = set(list("".join(word)))
trial = trial-alphabet

ans = 0
def dfs(alphabet,depth,trial):
    global ans
    if 0 >= K-len(alphabet):
        #세야함
        cnt = 0
        for wd in word:
            w = set(wd)
            boo = True

            for i in list(w):
                if i not in alphabet:
                    boo = False
                    break


            if boo:
                cnt += 1
        ans = max(ans,cnt)

        return

    for i in list(trial):
        alphabet.add(i)
        trial.remove(i)
        dfs(alphabet,depth+1,trial)
        trial.add(i)
        alphabet.remove(i)


dfs(alphabet,0,trial)
print(ans)
