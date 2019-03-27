import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParticipantGenerator implements Runnable {

    private String type;
    private Random rand;
    private int num;
    private Party party;

    ParticipantGenerator(Party part) {
        party = part;
        rand = new Random();
        num = rand.nextInt(2);

        if (num == 0) {
            type = "sidekick";
        } else {
            type = "hero";
        }
    }

    ParticipantGenerator(Party part, int num) {
        party = part;

        if (num == 0) {
            type = "sidekick";
        } else {
            type = "hero";
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            party.arrive(type);
            party.leave(type);
        }

    }

}
