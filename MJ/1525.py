from collections import deque
puzzle = ''

for _ in range(3):
    puzzle += ''.join(input().split())
visited = {}


vector = [(1, 0), (-1, 0), (0, 1), (0, -1)]

q = deque([])
q.append((puzzle, 0))


def swap(idx, nx, ny, puzzle_n):
    n_idx = nx*3 + ny
    puzzle_n = list(puzzle_n)
    puzzle_n[idx], puzzle_n[n_idx] = puzzle_n[n_idx], puzzle_n[idx]
    return ''.join(puzzle_n)


while q:
    puzzle_c, cnt = q.popleft()
    if puzzle_c == '123456780':
        print(cnt)
        exit(0)
    if visited.get(puzzle_c):
        continue
    visited[puzzle_c] = True
    idx = puzzle_c.find('0')
    x = idx//3
    y = idx % 3
    for vec in vector:
        nx = x+vec[0]
        ny = y+vec[1]
        if 0 <= nx < 3 and 0 <= ny < 3:
            puzzle_n = swap(idx, nx, ny, puzzle_c)
            if visited.get(puzzle_n):
                continue
            q.append((puzzle_n, cnt+1))
print(-1)
