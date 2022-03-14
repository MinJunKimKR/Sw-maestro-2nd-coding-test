# 12:13-> 1:03
# 12:48 -> (시간초과 예상) 시간초과
# TODO : retry
# refer : https://devjin-blog.com/boj-1987-alphabet/

# 방식은 맞았으니 접근을 잘못함 -> 문자열로 중복체크하는것에 익숙해져야함
# set으로 bfs를 하는건 처음보네

H, W = map(int, input().split())
field = []
vector = [(1, 0), (-1, 0), (0, 1), (0, -1)]
for _ in range(H):
    field.append(list(input()))

q = set([])
q.add((0, 0, ''))
ans = 1

while q:
    x, y, alpa = q.pop()
    if field[x][y] in alpa:
        continue
    alpa += field[x][y]
    ans = max(ans, len(alpa))
    for vec in vector:
        nx = x + vec[0]
        ny = y + vec[1]
        if 0 <= nx < H and 0 <= ny < W and field[nx][ny] not in alpa:
            q.add((nx, ny, alpa))
print(ans)
# DFS로 푼 풀이
# DFS로 해당 문제를 Python3에 제출했을 때 시간초과가 발생했다, 하지만 Pypy3으로 제출했을 때는 통과를 했다.


# # 좌, 하, 우, 상
# dx = [-1, 0, 1, 0]
# dy = [0, -1, 0, 1]


# def DFS(x, y, ans):
#     global answer

#     answer = max(ans, answer)

#     # 좌우상화 다 확인한다
#     for i in range(4):
#         nx = x + dx[i]
#         ny = y + dy[i]

#         # index 벗어나지 않는지 체크하고, 새로운 칸이 중복되는 알파벳인지 체크한다
#         if ((0 <= nx < R) and (0 <= ny < C)) and (board[nx][ny] not in passed):
#             passed.append(board[nx][ny])
#             DFS(nx, ny, ans+1)
#             passed.remove(board[nx][ny])


# R, C = map(int, sys.stdin.readline().split())
# board = [list(sys.stdin.readline().strip()) for _ in range(R)]

# answer = 1
# DFS(0, 0, answer)
# print(answer)
