import java.util.Deque;
import java.util.ArrayDeque;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Buffer<T> {
  private Deque<T> deque;
  private final ReentrantLock l = new ReentrantLock();
  private final Condition notFull = l.newCondition();
  private final Condition notEmpty = l.newCondition();
  private final int cap;

  Buffer(int cap) {
    this.cap = cap;
    deque = new ArrayDeque<T>(cap);
  }

  public T get() {
    T e = null;
    l.lock();

    try {
      while (deque.isEmpty()) {
        notEmpty.wait();
      }
      e = deque.removeFirst();
      notFull.signalAll();

    } catch (InterruptedException err) {
    } finally {
      l.unlock();
    }
    return e;
  }

  public void put(T e) {
    l.lock();

    try {
      while (deque.size() == cap) {
        notFull.wait();
      }

      deque.addLast(e);
      notEmpty.signalAll();
    } catch (InterruptedException err) {
    } finally {
      l.unlock();
    }
  }
}