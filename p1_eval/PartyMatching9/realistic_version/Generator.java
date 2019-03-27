import java.util.Random;

public class Generator implements Runnable {
    private int type;
    private Party party;

    Generator(Party p) {
        type = new Random().nextInt(2);
        party = p;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (type == 0) {
                Superhero hero = new Superhero();
                party.arrive(hero);
                while (!party.leave(hero) && party.partyCount() > 0) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                }
            } else if (type == 1) {
                party.arrive(new Sidekick());
            }
        }
    }
}