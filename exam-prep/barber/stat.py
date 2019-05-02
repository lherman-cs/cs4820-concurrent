import sys

n_lines = 0
for line in sys.stdin:
    n_lines += 1
    print(f"got {n_lines} lines", end='\r')
