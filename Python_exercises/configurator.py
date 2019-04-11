import configparser

def write_conf():
    config = configparser.RawConfigParser()

    config.add_section('String')
    config.set('String', 'both_ends', ['spring', 'hello', 'google', 'a', ''])
    config.set('String', 'fix_start', ['babble', 'aardvark', 'google', 'donut'])
    config.set('String', 'mix_up', [('mix', 'pod'), ('dog', 'dinner'), ('gnash', 'sport'), ('pezzy', 'firm')])
    config.set('String', 'not_bad', ['This movie is not so bad', 'This dinner is not that bad!', 'This tea is not hot', 'It is bad yet not'])
    config.set('String', 'front_back', [('abcd', 'xy'), ('abcde', 'xyz'), ('Kitten', 'Donut')])
    
    config.add_section('List')
    config.set('List', 'match_ends', [['aba', 'xyz', 'aa', 'x', 'bbb'], ['', 'x', 'xy', 'xyx', 'xx'], ['aaa', 'be', 'abc', 'hello']])
    config.set('List', 'front_x', [['bbb', 'ccc', 'axx', 'xzz', 'xaa'], ['ccc', 'bbb', 'aaa', 'xcc', 'xaa'], ['mix', 'xyz', 'apple', 'xanadu', 'aardvark']])
    config.set('List', 'sort_last', [[(1, 3), (3, 2), (2, 1)], [(2, 3), (1, 2), (3, 1)], [(1, 7), (1, 3), (3, 4, 5), (2, 2)]])
    config.set('List', 'remove_adjacent', [[1, 2, 2, 3], [2, 2, 3, 3, 3], []])
    config.set('List', 'linear_merge', [(['aa', 'xx', 'zz'], ['bb', 'cc']), (['aa', 'xx'], ['bb', 'cc', 'zz']), (['aa', 'aa'], ['aa', 'bb', 'bb'])])
    
    config.add_section('Remote')
    config.set('Remote', 'width', 6)
    config.set('Remote', 'x', 0)
    config.set('Remote', 'y', 0)
    config.set('Remote', 'keys', ['hello', 'world', 'zzz'])
    
    # Writing our configuration file to 'example.ini'
    cfgfile = open('example.ini', 'w')
    config.write(cfgfile)
    cfgfile.close()
  
def get_conf(section, option):
    config = configparser.RawConfigParser()
    config.read('example.ini')
    
    return config.get(section, option)