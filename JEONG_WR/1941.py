import sys

input = sys.stdin.readline

Map = []

for i in range(5):
    Map.append(list(input().replace("\n","")))

answer= []

def dfs(check,num,cnt):
    move = [[1,0],[-1,0],[0,1],[0,-1]]
    if cnt - num > 3:
        return


    if len(check) == 7:
        if num >=4 and sorted(check) not in answer:
            answer.append(sorted(check))
        return
    #print(check)
    for now in check:

        for i in move:
            n_r = now[0] + i[0]
            n_c = now[1] + i[1]
            if n_c < 0 or n_r < 0 or n_r >= 5 or n_c >= 5:
                continue
            if [n_r,n_c] not in check:
                check.append([n_r,n_c])
                if Map[n_r][n_c] == "S":

                    dfs(check,num+1,cnt+1)


                else:

                    dfs(check,num,cnt+1)

                check.pop()

for i in range(5):
    for j in range(5):
        if Map[i][j] == "S":
            check = [[i,j]]
            dfs(check,1,1)

print(len(answer))
