// Rewrite the Drop application so Producers & Consumers wait in different wait sets.

public class BadDrop {
    // Message sent from producer to consumer.
    private String message;

    // empty is True if consumer should wait for producer to send message,
    // false if producer should wait for consumer to retrieve message.
    private boolean empty = true;
    // locks for producer and consumer wait queue
    private Object producerLock = new Object();
    private Object consumerLock = new Object();

    public String take() {
        String messageCopy;
        synchronized (consumerLock) {
            // Wait until message is available.
            while (empty) {
                try {
                    consumerLock.wait();
                } catch (InterruptedException e) {
                }
            }

            // Toggle status.
            empty = true;
            messageCopy = message;
        }

        // Notify producer that
        // status has changed.
        synchronized (producerLock) {
            producerLock.notifyAll();
        }

        return messageCopy;
    }

    public void put(int who, String message) {
        synchronized (producerLock) {
            // Wait until message has
            // been retrieved.
            while (!empty) {
                try {
                    producerLock.wait();
                } catch (InterruptedException e) {
                }
            }

            System.out.format("put:%s\n", message);
            // Toggle status.
            empty = false;

            // Store message.
            this.message = message;
        }

        // Notify consumer that status
        // has changed.
        synchronized (consumerLock) {
            consumerLock.notifyAll();
        }
    }
}
