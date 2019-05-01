import java.util.Deque;
import java.util.ArrayDeque;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Buffer<T> {
  private Deque<T> deque;
  private final ReentrantLock l = new ReentrantLock();
  private final Condition notFull = l.newCondition();
  private final Condition notEmpty = l.newCondition();

  Buffer(int cap) {
    deque = new ArrayDeque<T>(cap);
  }

  public T get() {
    T e = null;
    l.lock();

      while (deque.isEmpty()) {
        				try {
                  notEmpty.wait();

				} catch (InterruptedException e) {}
			}
        


      e = deque.removeFirst();
      notFull.notifyAll();
    }finally

  {
    l.unlock();
  }return e;
  }

  public void put(T e) {

  }
}