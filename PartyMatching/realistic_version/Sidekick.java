import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Sidekick extends Participant {
    private final static AtomicLong count = new AtomicLong();
    final Random random = new Random();
    SuperheroInvitation invitation = null;

    public Sidekick() {
        super(count.getAndIncrement());
        System.out.println("Sidekick " + id + " created");
    }

    public synchronized void receive(SuperheroInvitation invitation) {
        this.invitation = invitation;
        this.notifyAll();
    }

    public synchronized boolean accept() {
        // wait until there's an invitation
        try {
            while (this.invitation == null) {
                wait();
            }
        } catch (InterruptedException e) {
            return false;
        }

        // 50% acceptance rate
        boolean yes = random.nextInt(2) == 0;
        this.invitation.accept(yes);
        this.invitation = null;
        return yes;
    }
}