def match_ends(words):
    # counter to count matches

    # for loop to iterate through the array of words

        # if the length of the word is 2 or greater and
        # word at character 0 is the same as word last character
        # add one to the count variable

    # return number of matches
    return len([w for w in words if len(w) >= 2 and w[0] == w[-1]])


def front_x(words):
    words.sort()
    left = []
    right = []
    for word in words:
        if word[0] == 'x':
            left.append(word)
        else:
            right.append(word)
    left += right
    return left


def sort_last(tuples):
    return sorted(tuples, key=lambda v: v[1])


def remove_adjacent(nums):
    l, r = 0, 0
    n = len(nums)
    while r < n:
        nums[l] = nums[r]

        while r < n and nums[r] == nums[l]:
            r += 1
        l += 1
    return nums[:l]


def linear_merge(list1, list2):
    l, r = 0, 0
    i = 0
    n_list1 = len(list1)
    n_list2 = len(list2)
    merged = [0] * (n_list1 + n_list2)

    while l < n_list1 and r < n_list2:
        l_val = list1[l]
        r_val = list2[r]
        if l_val <= r_val:
            merged[i] = l_val
            l += 1
        else:
            merged[i] = r_val
            r += 1
        i += 1

    if l < n_list1:
        merged[i:] = list1[l:]
    else:
        merged[i:] = list2[r:]

    return merged
