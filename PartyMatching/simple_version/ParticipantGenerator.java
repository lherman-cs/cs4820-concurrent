import java.util.Random;

class ParticipantGenerator implements Runnable {
  private final Party party;
  private final ParticipantType type;

  ParticipantGenerator(Party party, ParticipantType type) {
    this.party = party;
    this.type = type;
  }

  @Override
  public void run() {
    // Random random = new Random();
    // ParticipantType type;

    // switch (random.nextInt(2)) {
    // case 0:
    // type = ParticipantType.Superhero;
    // break;
    // default:
    // type = ParticipantType.Sidekick;
    // break;
    // }

    for (int i = 0; i < 1000; i++) {
      this.party.arrive(type);
      this.party.leave(type);
    }
  }
}