import sys
input = sys.stdin.readline

s = input().rstrip()
st = []
tmp, answer = 1, 0

for i in range(len(s)):
    if s[i] == '(':
        tmp *= 2
        st.append(s[i])
    elif s[i] == '[':
        tmp *= 3
        st.append(s[i])
    elif s[i] == ')':
        if not st or s[-1] == '[':
            answer = 0
            break
        if s[i-1] == '(':
            answer += tmp
        tmp //= 2
        st.pop()    
    else:
        if not st or s[-1] == '(':
            answer = 0
            break
        if s[i-1] == '[':
            answer += tmp
        tmp //= 3
        st.pop()

print(0 if st else answer)
        