from collections import deque


def solution(prices):
    queue = deque(prices)
    answer = []
    while queue:
        sec = 0
        now = queue.popleft()
        for i in queue:
            sec += 1
            if i < now:
                break
        answer.append(sec)

    return answer