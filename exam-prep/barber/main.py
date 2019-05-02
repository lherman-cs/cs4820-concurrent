
'''
  Message format
    command: 
    data: 

    https://docs.python.org/3.4/library/concurrent.futures.html
    https://www.geeksforgeeks.org/context-manager-in-python/
    https://docs.python.org/3/library/contextlib.html
'''

import asyncio
import random


class Shop:
    def __init__(self, n_barbers: int):
        self.__work_queue = asyncio.Queue(n_barbers)
        self.__n_barbers = n_barbers
        self.__handlers = {
            "client": self.handle_client,
        }

    # multiplex incomming messages
    async def handle(self, msg: dict):
        cmd = msg["command"]
        data = msg["data"]
        await self.__handlers[cmd](data)

    # handle incomming clients
    async def handle_client(self, id: int):
        workload = random.randint(1, 3) * 1e-1
        await self.__work_queue.put(workload)

    # have barber work on client
    async def barber(self, id: int):
        while True:
            workload = await self.__work_queue.get()
            print(f"barber {id} started working {workload}")
            await asyncio.sleep(workload)
            print(f"barber {id} worked {workload}", flush=True)

    async def start(self):
        workers = (self.barber(id) for id in range(self.__n_barbers))
        await asyncio.gather(*workers)


# create 100 clients who enter the shop


async def client(s: Shop):
    for i in range(1000):
        await s.handle({
            "command": "client",
            "data": i,
        })


s = Shop(4)
n_clients = 16
loop = asyncio.get_event_loop()
clients = (client(s) for _ in range(n_clients))
asyncio.ensure_future(asyncio.gather(*clients))

try:
    loop.run_until_complete(s.start())
finally:
    loop.close()
