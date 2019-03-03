import java.util.Random;

public class BadConsumer implements Runnable {
    private BadDrop drop;
    private int who;

    public BadConsumer(int who, BadDrop drop) {
        this.who = who;
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take();
             ! message.equals("DONE");
             message = drop.take()) {
            System.out.format("%d:MESSAGE RECEIVED: %s%n", who, message);
	    /*
            try {
                Thread.sleep(random.nextInt(0));
            } catch (InterruptedException e) {}
	    */
        }
    }
}
