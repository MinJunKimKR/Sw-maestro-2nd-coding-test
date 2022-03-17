# 11:40 -> 12:20
# 12:29 [success]
N = int(input())

if N < 10:
    print(N)
    exit(0)
if N > 1022:
    print(-1)
    exit(0)
cnt = 9
num = ['1', '0']
while cnt < N:
    len_num = len(num)
    is_minus = True
    for i in range(len_num-1):
        if int(num[i]) <= int(num[i+1]):
            is_minus = False
            if int(num[i])+1 > 9:
                num[i] = '1'
                num = num+['0']
            else:
                num[i] = str(int(num[i])+1)
            mi = 1
            for j in range(i+1, len_num):
                num[j] = '0'
            break
    if is_minus:
        cnt += 1
        if cnt == N:
            break
        num = list(str(int(''.join(num))+1))

print(''.join(num))
