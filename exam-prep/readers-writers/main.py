from asyncio import coroutine

'''
    msg = {
        "command": "<name>",
        "data": <anything>,
    }
'''


def state():
    subscribers = []
    _state = {}

    def handleSubscribe(data):
        subscribers.append(data)

    def handleWrite(data):
        _state = data
        # broadcast to subscribers
        for subscriber in subscribers:
            subscriber.send(_state)

    handlers = {
        "subscribe": handleSubscribe,
        "write": handleWrite,
    }

    while True:
        msg = (yield)
        cmd = msg["command"]
        data = msg["data"]
        handlers[cmd](data)


def reader():
    while True:
        msg = (yield)
        print(msg)


def writer(arr):
    i = 0
    l = len(arr)-1
    while True:
        yield arr[i]
        i = 0 if i == l else i + 1


s = state()
next(s)
n_readers = 100
n_msgs = 50
for _ in range(n_readers):
    r = reader()
    next(r)
    s.send({
        "command": "subscribe",
        "data": r,
    })

names = ["Robin", "Aqua Lad", "Krypton the super dog", "donkey", ]
w = writer(names)
for _ in range(n_msgs):
    s.send({
        "command": "write",
        "data": next(w),
    })
    print('=' * 100)
