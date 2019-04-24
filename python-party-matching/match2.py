import time
import random

# class party
#   record the number of superheroes and sidekicks and the number of pairs
#   methods:
#       superhero_checkin
#       sidekick_checkin
#       superhero_checkout
#       sidekick_checkout
# participant generator
#   function participant_generator(name, role, party)
#   Whenever a participant is generated, it should be sent to the party
#       If it is not possible for it to leave, the generator object
#       should yield for others to proceed.
#   After all generated participants leave the party, this generator object should stop.
#   Participant behavior:
#      find partner
#      leave


class Party:

    def __init__(self):
        # coroutine wrappers
        self.superhero_checkout = self.__superhero_checkout()
        self.sidekick_checkout = self.__sidekick_checkout()
        self.gate = self.__gate()

        # start up the coroutines
        next(self.superhero_checkout)
        next(self.sidekick_checkout)
        next(self.gate)

        # data about party state
        self.kicks = 0
        self.heros = 0
        self.pair = 0
        self.kick_coroutines = []

    def superhero_checkin(self):
        pass

    def sidekick_checkin(self):
        pass

    # when a superhero tries to leave,
    def __superhero_checkout(self):
        while True:
            # wait here for participant generator to tell to leave
            yield

            if len(self.kick_coroutines) == 0:
                continue

            accepted = False

            def accept():
                nonlocal accepted
                accepted = True

            # send invitations to sidekicks until one accepts
            while not accepted:
                # choose a sidekick from the list of sidekicks
                kick = random.choice(self.kick_coroutines)
                # send them an invitiation, give them a callback, to respond
                #   to the invitiation if they wish (if they respond they accept)
                kick.send(accept)

            kick.close()
            self.kick_coroutines.remove(kick)
            # notify the gate that the hero is trying to leave
            self.gate.send("hero")

    # when a sidekick tries to leave,
    def __sidekick_checkout(self):
        def sidekick_coroutine():
            while True:
                # wait for an invitation from a stuperhero
                #   note accept is a callback function
                accept = (yield)

                # randomly choose to accept the superhero's invite
                if random.randint(0, 1) == 1:

                    # we do not accept the invite, so keep waiting
                    #   for a more appealing invitation
                    continue

                # choose to accept the recieved invitation
                accept()

                # tell the gate that we are ready to leave
                self.gate.send("kick")

        while True:
            # wait until called by a sidekick generator
            yield

            # define a sidekick coroutine
            coro = sidekick_coroutine()

            # start up the coroutine
            next(coro)

            # log the started coroutine to the class
            #  allows the sidekick (coroutine) to be discoverable by superheros
            self.kick_coroutines.append(coro)

    # gate method, controls exiting process
    def __gate(self):
        while True:
            # message from the participant's call to checkout
            role = (yield)

            # determines who is trying to leave
            if role == "hero":

                # if we cannot leave, record the entering
                if self.kicks == 0:
                    self.heros += 1
                    continue

                # if we can leave, leave with partner
                self.kicks -= 1
            else:
                if self.heros == 0:
                    self.kicks += 1
                    continue
                self.heros -= 1

            # record pair leaving
            self.pair += 1


def participant_generator(i, hero, party):
    # number of participants to generate
    n = 10

    # store the functions (for cleaner calling)
    checkin = party.superhero_checkin if hero else party.sidekick_checkin
    checkout = party.superhero_checkout if hero else party.sidekick_checkout

    # for each participant
    for _ in range(n):
        # enter party
        checkin()
        # exit party
        next(checkout)
        # wait to be scheduled again (wait to be told to generate again)
        yield


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
    while len(participants) > 0 and time.time() - t0 < 5:
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
