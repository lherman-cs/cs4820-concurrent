public class Main {
    public static void main(String args[]) {
        Party party = new Party();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
        (new Thread(new Generator(party))).start();
    }
}
