# 4:30 -> 5:10
# FAIL - 5:07
# 5:10 -> FAIL
# ReReTry 6:40 -> 7:10 [6:45 - fail]
# 7:00 -> 시간초과 pypy [success]

from collections import deque
import sys
sys_input = sys.stdin.readline

K = int(input())
W, H = map(int, input().split())
field = []
for _ in range(H):
    field.append(
        list(map(int, sys_input().strip().split())))
q = deque([])
m_vector = [(1, 0), (-1, 0), (0, 1), (0, -1)]
h_vector = [(-1, 2), (-1, -2), (1, 2), (1, -2),
            (-2, 1), (-2, -1), (2, 1), (2, -1)]
visited = [[[0]*W for _ in range(H)]for _ in range(K+1)]
q.append((0, 0, K))


def is_able(x, y):
    if 0 <= x < H and 0 <= y < W and field[x][y] == 0:
        return True
    else:
        return False


INF = int(1e9)
ans = INF

while q:
    x, y, k = q.popleft()
    if x == H-1 and y == W-1:
        ans = min(ans, visited[k][x][y])
        continue
    for vec in m_vector:
        nx = x + vec[0]
        ny = y + vec[1]
        if is_able(nx, ny):
            if visited[k][nx][ny] > visited[k][x][y]+1 or visited[k][nx][ny] == 0:
                visited[k][nx][ny] = visited[k][x][y]+1
                q.append((nx, ny, k))
    if k > 0:
        for vec in h_vector:
            nx = x + vec[0]
            ny = y + vec[1]
            if is_able(nx, ny):
                if visited[k-1][nx][ny] > visited[k][x][y]+1 or visited[k-1][nx][ny] == 0:
                    visited[k-1][nx][ny] = visited[k][x][y]+1
                    q.append((nx, ny, k-1))

if ans == INF:
    print(-1)
else:
    print(ans)

# # min_cnt = INF
# # visited = [[INF]*W for _ in range(H)]
# # q.append([0, 0, K, 0])


# # while q:
# #     x, y, k, cnt = q.popleft()
# #     if visited[x][y] < cnt or cnt >= min_cnt:
# #         continue
# #     if x == H-1 and y == W-1:
# #         min_cnt = min(min_cnt, cnt)
# #         continue
# #     visited[x][y] = cnt
# #     cnt += 1
# #     for vec in m_vector:
# #         nx = x + vec[0]
# #         ny = y + vec[1]
# #         if is_able(nx, ny):
# #             q.append((nx, ny, k, cnt))
# #     if k > 0:
# #         k -= 1
# #         for vec in h_vector:
# #             nx = x + vec[0]
# #             ny = y + vec[1]
# #             if is_able(nx, ny):
# #                 q.append((nx, ny, k, cnt))
# # if min_cnt == INF:
# #     print(-1)
# # else:
# #     print(min_cnt)


# # 1
# # 4 4
# # 0 1 0 0
# # 1 1 0 0
# # 0 0 1 0
# # 0 1 0 0

# # 3
# # 4 5
# # 0 1 1 1
# # 1 1 0 1
# # 1 1 1 1
# # 1 1 1 0
# # 1 1 1 0


# # 1
# # 4 5
# # 0 1 1 1
# # 1 1 0 1
# # 1 1 1 1
# # 1 1 1 0
# # 1 1 1 0

# 1
# 5 5
# 0 0 0 0 0
# 0 0 0 0 0
# 0 0 0 0 0
# 0 0 0 1 1
# 0 0 0 1 0
