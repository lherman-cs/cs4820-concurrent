public class Main {
    public static void main(String[] args) {
        Buffer<String> drop = new Buffer<>(30);
        (new Thread(new Producer(drop))).start();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}