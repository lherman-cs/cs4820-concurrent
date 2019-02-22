import javax.print.DocFlavor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Party {
    private Lock lock;
    private Condition superCondition;
    private Condition sideCondition;
    private boolean sideReady;
    private boolean superReady;

    Party() {
        sideReady = false;
        superReady = false;

        lock = new ReentrantLock();
        superCondition = lock.newCondition();
        sideCondition = lock.newCondition();
    }

    void arrive(String type) {
    }

    void leave(String type) {
        lock.lock();
        if (type.equals("hero")) {
            try {
                superReady = true;
                while (!sideReady) {
                    try {
                        sideCondition.await();
                        superReady = true;
                    } catch (InterruptedException ignored) {
                    }
                }
                sideReady = false;
                superCondition.signalAll();
            } finally {
                lock.unlock();
            }

        }

        else if (type.equals("sidekick")) {
            try {
                sideReady = true;
                while (!superReady) {
                    try {
                        superCondition.await();
                        sideReady = true;
                    } catch (InterruptedException ignored) {
                    }
                }
                superReady = false;
                sideCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
