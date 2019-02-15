import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Party {
  private AtomicBoolean superheroAtExit = new AtomicBoolean();
  private AtomicBoolean sidekickAtExit = new AtomicBoolean();

  final private ReentrantLock lock = new ReentrantLock();
  final private Condition superheroLeaving = lock.newCondition();
  final private Condition superheroLeft = lock.newCondition();
  final private Condition sidekickLeaving = lock.newCondition();
  final private Condition sidekickLeft = lock.newCondition();

  public void arrive(ParticipantType type) {
    // Nothing is needed here. This is intentionally blank.
    // Superheroes and sidekicks can enter the party concurrently at any
    // given of time.
  }

  public void leave(ParticipantType type) {
    lock.lock();
    try {

      // block superhero/sidekick if there's another same type
      System.out.println(type + " is leaving");
      switch (type) {
      case Superhero:
        while (superheroAtExit.get()) {
          superheroLeft.await();
        }

        superheroAtExit.set(true);
        superheroLeaving.signalAll();
        if (!sidekickAtExit.get()) {
          sidekickLeaving.await();
        }

        // Only 1 person who needs to inform the others
        superheroAtExit.set(false);
        sidekickAtExit.set(false);
        superheroLeft.signalAll();
        sidekickLeft.signalAll();
        break;

      case Sidekick:
        while (sidekickAtExit.get()) {
          sidekickLeft.await();
        }

        sidekickAtExit.set(true);
        sidekickLeaving.signalAll();
        if (!superheroAtExit.get()) {
          superheroLeaving.await();
        }
        break;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

    System.out.println(type + " has left");
  }
}