public class ProducerConsumerExample {
  public static void main(String[] args) {
    BadDrop drop = new BadDrop();
    (new Thread(new Producer(drop))).start();
    (new Thread(new Producer(drop))).start();
    (new Thread(new Consumer(drop))).start();
    (new Thread(new Consumer(drop))).start();
  }
}