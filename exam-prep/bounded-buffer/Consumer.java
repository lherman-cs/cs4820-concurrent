public class Consumer implements Runnable {
    private Buffer<String> drop;

    public Consumer(Buffer<String> drop) {
        this.drop = drop;
    }

    public void run() {
        // Random random = new Random();
        for (String message = drop.get(); !message.equals("DONE"); message = drop.get()) {
            System.out.format("get:%s\n", message);
            /*
             * try { Thread.sleep(random.nextInt(0)); } catch (InterruptedException e) {}
             */
        }
    }
}
