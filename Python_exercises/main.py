import mystring
import mylist
import configurator
import ast


def myPrint():
    rows = 5
    stars = 1
    for row in range(rows):
        print(' ' * (rows - row) + '*' * stars)
        stars += 2

# a predefined main function
# that takes care of input test cases from configuration file
# no need to modify it


def main():
    print("myPrint")
    myPrint()
    print()

    configurator.write_conf()

    print("String: both_ends")
    cases = ast.literal_eval(configurator.get_conf('String', 'both_ends'))
    for case in cases:
        print(case + ' -> ' + mystring.both_ends(case))
    print()

    print("String: fix_start")
    cases = ast.literal_eval(configurator.get_conf('String', 'fix_start'))
    for case in cases:
        print(case + ' -> ' + mystring.fix_start(case))
    print()

    print("String: mix_up")
    cases = ast.literal_eval(configurator.get_conf('String', 'mix_up'))
    for case in cases:
        print(case, '-> ' + mystring.mix_up(case[0], case[1]))
    print()

    print("String: not_bad")
    cases = ast.literal_eval(configurator.get_conf('String', 'not_bad'))
    for case in cases:
        print(case + ' -> ' + mystring.not_bad(case))
    print()

    print("String: font_back")
    cases = ast.literal_eval(configurator.get_conf('String', 'front_back'))
    for case in cases:
        print(case, '-> ' + mystring.front_back(case[0], case[1]))
    print()

    print("List: match_ends")
    cases = ast.literal_eval(configurator.get_conf('List', 'match_ends'))
    for case in cases:
        print(case, '->', mylist.match_ends(case))
    print()

    print("List: front_x")
    cases = ast.literal_eval(configurator.get_conf('List', 'front_x'))
    for case in cases:
        print(case, ' -> ', mylist.front_x(case))
    print()

    print("List: sort_last")
    cases = ast.literal_eval(configurator.get_conf('List', 'sort_last'))
    for case in cases:
        print(case, ' -> ', mylist.sort_last(case))
    print()

    print("List: remove_adjacent")
    cases = ast.literal_eval(configurator.get_conf('List', 'remove_adjacent'))
    for case in cases:
        print(case, ' -> ', mylist.remove_adjacent(case))
    print()

    print("List: linear_merge")
    cases = ast.literal_eval(configurator.get_conf('List', 'linear_merge'))
    for case in cases:
        print(case, ' -> ', mylist.linear_merge(case[0], case[1]))
    print()

    print("Remote:")
    width = ast.literal_eval(configurator.get_conf('Remote', 'width'))
    x = ast.literal_eval(configurator.get_conf('Remote', 'x'))
    y = ast.literal_eval(configurator.get_conf('Remote', 'y'))
    remote = remotekeypad.remotekeypad(width, x, y)
    cases = ast.literal_eval(configurator.get_conf('Remote', 'keys'))
    for case in cases:
        remote.get_moves(case)
        print()


# Standard boilerplate to call the main() function.
if __name__ == '__main__':
    main()
