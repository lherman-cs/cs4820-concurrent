import sys
import random
from collections import defaultdict

# opens the dictionary and stores array


def mimic_dict(filename):
    with open(filename) as i:
        text = i.read()

    words = text.split()
    mimic = {word: words[i+1:] for i, word in enumerate(words)}
    mimic[""] = words
    return mimic


# prints out the string based on the array
def print_mimic(mimic_dict, word, n):
    generated = []
    current = word
    if current not in mimic_dict:
        current = ""
    current = random.choice(mimic_dict[current])

    # for loop iterates from 0 to n
    for _ in range(n):
        generated.append(current)
        try:
            current = random.choice(mimic_dict[current])
        except IndexError:
            current = random.choice(mimic_dict[""])

    print(" ".join(generated))


def main():
    if len(sys.argv) != 4:
        print("usage: ./mimic.py file-to-read start-word num-of-random-words")
        sys.exit(1)

    dic = mimic_dict(sys.argv[1])
    print_mimic(dic, sys.argv[2], int(sys.argv[3]))
    print()


if __name__ == '__main__':
    main()
