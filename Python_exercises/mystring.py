import math


def both_ends(s):
    # if the length of the string < 2
    # return empty string
    if len(s) < 2:
        return ""

    # return first two and last two characters
    return s[:2] + s[-2:]


def fix_start(s):
    # get first character of the string

    # return first character and the rest of the string with * replace
    # s[0] is first char, s[1:] is second char and beyond
    first = s[0]
    return s.replace(first, '*').replace('*', first, 1)


def mix_up(a, b):
    # return string b first two characters + second characters of a and beyond
    # return string a first two characters + second characters of b and beyond
    return b[:2] + a[2:] + ' ' + a[:2] + b[2:]


def not_bad(s):
    # find the position of where "not" and "bad" starts
    _not = s.find("not")
    _bad = s.find("bad")

    # if bad follows not
    # "not" is in the string
    # "bad" is in the string
    if _not != -1 and _bad != -1 and _not < _bad:
        # return string all characters up to position n and "good"
        # and all characters after not, because "not" is 3 characters why s[b+3:]
        return s[:_not] + "good" + s[_bad + 3:]

    # return un-edited string
    return s


def front_back(a, b):
    # Math.ceil()
    # ordinary number 5/2= 2.5
    # but with ceiling(5/2) = 3
    # return string string a character 0 to ceiling(length / 2)
    # return string string b character 0 to ceiling(length / 2)
    # return string a character ceiling(length / 2) to beyond
    # return string b character ceiling(length / 2) to beyond
    _a = math.ceil(len(a) / 2)
    _b = math.ceil(len(b) / 2)
    return a[:_a] + b[:_b] + a[_a:] + b[_b:]
