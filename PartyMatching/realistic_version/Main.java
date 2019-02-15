class Main {
  public static void main(String[] args) {
    int n = 2;
    Party party = new Party();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();

    // for (int i = 0; i < n; i++) {
    // new Thread(new ParticipantGenerator(party,
    // ParticipantType.Superhero)).start();
    // }
  }
}