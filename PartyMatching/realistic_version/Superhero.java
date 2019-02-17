import java.util.concurrent.atomic.AtomicLong;

public class Superhero extends Participant {
    private final static AtomicLong count = new AtomicLong();
    private boolean accepted = false;
    private boolean waiting = false;

    public Superhero() {
        super(count.getAndIncrement());
        System.out.println("Superhero " + id + " created");
    }

    public synchronized boolean invite(Sidekick sidekick) {
        waiting = true;
        System.out.println("Superhero " + id + " invites Sidekick " + sidekick.id);
        sidekick.receive(new SuperheroInvitation() {
            @Override
            public void accept(boolean yes) {
                onAccept(yes);
            }
        });

        try {
            while (waiting) {
                wait();
            }
        } catch (InterruptedException e) {
            this.waiting = false;
            this.accepted = false;
            return false;
        }

        return accepted;
    }

    private synchronized void onAccept(boolean yes) {
        if (!yes) {
            System.out.println("Superhero " + id + " got rejected");
        }
        waiting = false;
        accepted = yes;
        this.notifyAll();
    }
}