public class BadProducerConsumerExample {
    public static void main(String[] args) {
        int nProducers = 100;
        int nConsumers = 100;
        BadDrop drop = new BadDrop();
        for (int i = 0; i < nProducers; i++)
            (new Thread(new BadProducer(i, drop))).start();

        for (int i = 0; i < nConsumers; i++)
            (new Thread(new BadConsumer(i, drop))).start();
    }
}
