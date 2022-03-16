# 12:45 -> 1:25
# 1:12 -> 떨어지는거 구현???? [FAIL]
# 1:38 [success]
# TODO: retry -> 떨어지는거 참고했었음
from collections import deque
import sys
sys_input = sys.stdin.readline
graph = []
vector = [(1, 0), (-1, 0), (0, 1), (0, -1)]
puyo = ["R", 'G', 'B', 'P', 'Y']
for _ in range(12):
    graph.append(list(sys_input().strip()))


def bfs(x, y, puyo):
    global this_visited
    global is_pop
    q = deque([])
    q.append((x, y))
    visited = []
    while q:
        x, y = q.popleft()
        visited.append((x, y))
        this_visited[x][y] = True
        for vec in vector:
            nx = x + vec[0]
            ny = y + vec[1]
            if 0 <= nx < 12 and 0 <= ny < 6 and graph[nx][ny] == puyo and not this_visited[nx][ny]:
                q.append((nx, ny))
    if len(visited) >= 4:
        is_pop = True
        for p in visited:
            graph[p[0]][p[1]] = '.'


def down():
    for w in range(6):
        for h in range(10, -1, -1):
            for dot in range(11, h, -1):
                if graph[h][w] != '.' and graph[dot][w] == '.':
                    graph[dot][w] = graph[h][w]
                    graph[h][w] = '.'
                    break


ans = 0


while True:
    is_pop = False
    this_visited = [[False] * 6 for _ in range(12)]
    for i in range(12):
        for j in range(6):
            if graph[i][j] != '.':
                bfs(i, j, graph[i][j])
                this_visited[i][j] = True
    if not is_pop:
        print(ans)
        break
    ans += 1
    down()
    # 줄내리기
