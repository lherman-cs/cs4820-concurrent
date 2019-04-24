import time
import random

# class party
#   record the number of superheroes and sidekicks and the number of pairs
#   methods:
#       superhero_checkin
#       sidekick_checkin
#       superhero_checkout
#       sidekick_checkout
# generator function participant_generator(name, role, party)
#   generate 10 party participants in total (randomly either all superheros or all sidekicks)
#   Whenever a participant is generated, it should be sent to the party
#       If it is not possible for it to leave, the generator object
#       should yield for others to proceed.
#   After all generated participants leave the party, this generator object should stop.
#    Participant behavior:
#      find partner
#      leave


class Party:
    heros = {}
    num_heros = 0
    kicks = {}
    num_kicks = 0
    pair = 0

    def __init__(self):
        pass

    def superhero_checkin(self, i, hero):
        self.heros[i] = (hero, False)
        self.num_heros += 1

    def sidekick_checkin(self, i, kick):
        self.kicks[i] = (kick, False)
        self.num_kicks += 1

    def superhero_checkout(self, i):
        while self.num_kicks == 0:
            yield

        kick = next(kick[0] for kick in self.kicks.values() if kick[1])
        kick.send(i)

    def sidekick_checkout(self, i):
        coroutine, _ = self.kicks[i]
        self.kicks[i] = (coroutine, True)
        hero_id = -1

        while self.num_heros == 0 or hero_id == -1:
            hero_id = (yield)

        self.num_kicks -= 1
        del self.kicks[i]
        print(hero_id)
        self.num_heros -= 1
        del self.heros[hero_id]
        self.pair += 1


def participant_generator(i, hero, party):
    n = 10
    checkin = party.superhero_checkin if hero else party.sidekick_checkin
    checkout = party.superhero_checkout if hero else party.sidekick_checkout

    for _ in range(n):
        coroutine = checkout(i)
        checkin(i, coroutine)
        yield from coroutine


def main():
    p = Party()
    num_heros = 0
    num_kicks = 0
    participants = []
    for i in range(10):
        if random.randint(0, 1) == 1:
            pg = participant_generator(i, True, p)
            num_heros += 10
        else:
            pg = participant_generator(i, False, p)
            num_kicks += 10
        participants.append(pg)

    t0 = time.time()
    # while len(participants) > 0 and time.time() - t0 < 5:
    while len(participants) > 0 and min(num_heros, num_kicks) != int(p.pair):
        task = random.choice(participants)
        try:
            next(task)
        except StopIteration:
            participants.remove(task)

    print('heroes: {}'.format(num_heros))
    print('sidekicks: {}'.format(num_kicks))
    print('pairs: {}'.format(int(p.pair)))
    assert min(num_heros, num_kicks) == int(p.pair)


if __name__ == '__main__':
    main()
