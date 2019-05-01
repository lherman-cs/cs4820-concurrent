
'''
  Message format
    command: 
    data: 
'''


def shop():
    def tasks_gen():
        pass

    tasks = tasks_gen()

    def handle_add_worker(barber):
        next(barber)
        barber.send(tasks)

    def handle_serve_client(data):
        pass

    handlers = {
        "add_worker": handle_add_worker,
        "serve_client": handle_serve_client,
    }

    while True:
        msg = (yield)
        cmd = msg["command"]
        data = msg["data"]
        handlers[cmd](data)


def barber():
    tasks = (yield)
    for task in tasks:
        pass


def client():
    pass
