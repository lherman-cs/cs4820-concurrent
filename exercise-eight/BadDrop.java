/**
 * Authors: Lukas Herman and Gabrielle Stewart
 */

public class BadDrop {
    // Message sent from producer to consumer.
    private String message;

    // empty is True if consumer should wait for producer to send message,
    // false if producer should wait for consumer to retrieve message.
    private boolean empty = true;
    private Object consumer = new Object();
    private Object producer = new Object();

    public String take() {
        String messageCopy;
        synchronized (consumer) {
            // Wait until message is available.
            while (empty) {
                try {
                    consumer.wait();
                } catch (InterruptedException e) {
                }
            }

            // Toggle status.
            empty = true;

            // Notify producer that
            // status has changed.
            messageCopy = message;
        }

        synchronized (producer) {
            producer.notifyAll();
        }

        return messageCopy;
    }

    public void put(int who, String message) {
        synchronized (producer) {
            // Wait until message has
            // been retrieved.
            while (!empty) {
                try {
                    producer.wait();
                } catch (InterruptedException e) {
                }
            }

            System.out.format("%d:putting message : %s %n", who, message);
            // Toggle status.
            empty = false;

            // Store message.
            this.message = message;
        }

        synchronized (consumer) {
            // Notify consumer that status
            // has changed.
            consumer.notifyAll();
        }
    }
}
