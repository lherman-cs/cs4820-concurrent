public class Main {
    public static void main(String args[]) {
        Party party = new Party();
        (new Thread(new ParticipantGenerator(party,0))).start();
        (new Thread(new ParticipantGenerator(party,1))).start();
        (new Thread(new ParticipantGenerator(party,0))).start();
        (new Thread(new ParticipantGenerator(party,1))).start();
        (new Thread(new ParticipantGenerator(party,0))).start();
        (new Thread(new ParticipantGenerator(party,1))).start();
        (new Thread(new ParticipantGenerator(party,0))).start();
        (new Thread(new ParticipantGenerator(party,1))).start();
        (new Thread(new ParticipantGenerator(party,0))).start();
        (new Thread(new ParticipantGenerator(party,1))).start();
    }
}
