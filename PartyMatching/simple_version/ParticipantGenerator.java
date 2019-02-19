import java.util.Random;

class ParticipantGenerator implements Runnable {
  private final Party party;
  private final ParticipantType type;

  ParticipantGenerator(Party party) {
    this.party = party;

    Random random = new Random();
    switch (random.nextInt(2)) {
    case 0:
      this.type = ParticipantType.Superhero;
      break;
    default:
      this.type = ParticipantType.Sidekick;
      break;
    }
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      this.party.arrive(type);
      this.party.leave(type);
    }
  }
}