class Main {
  public static void main(String[] args) {
    int n = 10;
    Party party = new Party();

    for (int i = 0; i < n; i++) {
      new Thread(new ParticipantGenerator(party)).start();
    }
  }
}