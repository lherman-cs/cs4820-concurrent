class Main {
  public static void main(String[] args) {
    Party party = new Party();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Superhero)).start();
    new Thread(new ParticipantGenerator(party, ParticipantType.Sidekick)).start();
  }
}