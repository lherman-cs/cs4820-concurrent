import subprocess
import re
from collections import Counter
from tqdm import trange

tests = 10000

messages = ["Mares eat oats", "Does eat oats", "Little lambs eat ivy",
            "A kid will eat ivy too"]

args = ["java", "BadProducerConsumerExample"]

for _ in trange(tests):
    puts = Counter()
    takes = Counter()
    result = subprocess.run(args, stdout=subprocess.PIPE)

    for line in result.stdout.decode().split("\n"):
        if line.strip() == "":
            continue

        op, msg = line.split(":")
        if op == "put":
            puts[msg] += 1
        else:
            takes[msg] += 1

    # verify counters
    for msg in messages:
        if not(puts[msg] == takes[msg] == 2):
            print(f"ERROR WITH {msg}", puts[msg], takes[msg])
