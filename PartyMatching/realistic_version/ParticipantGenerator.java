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
    int n = 10;

    for (int i = 0; i < n; i++) {
      Participant participant = type == ParticipantType.Superhero ? new Superhero() : new Sidekick();
      this.party.arrive(participant);
      this.party.leave(participant);
    }
  }
}