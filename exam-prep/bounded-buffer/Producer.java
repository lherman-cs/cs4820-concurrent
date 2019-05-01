public class Producer implements Runnable {
    private Buffer<String> drop;

    public Producer(Buffer<String> drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = { "Mares eat oats", "Does eat oats", "Little lambs eat ivy",
                "A kid will eat ivy too" };
        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            // try {
            // Thread.sleep(random.nextInt(5));
            // } catch (InterruptedException e) {}
        }
        drop.put("DONE");
    }
}