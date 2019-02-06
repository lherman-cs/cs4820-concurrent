public class BadProducerConsumerExample {
    public static void main(String[] args) {
        BadDrop drop = new BadDrop();
        (new Thread(new BadProducer(1, drop))).start();
        (new Thread(new BadProducer(2, drop))).start();
        (new Thread(new BadConsumer(3, drop))).start();
        (new Thread(new BadConsumer(4, drop))).start();
    }
}
