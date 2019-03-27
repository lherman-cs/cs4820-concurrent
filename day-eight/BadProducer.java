import java.util.Random;

public class BadProducer implements Runnable {
    private BadDrop drop;
    private int who;

    public BadProducer(int who, BadDrop drop) {
        this.who = who;
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = { "Mares eat oats", "Does eat oats", "Little lambs eat ivy",
                "A kid will eat ivy too" };
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(who, importantInfo[i]);
            // try {
            // Thread.sleep(random.nextInt(5));
            // } catch (InterruptedException e) {}
        }
        drop.put(who, "DONE");
    }
}
