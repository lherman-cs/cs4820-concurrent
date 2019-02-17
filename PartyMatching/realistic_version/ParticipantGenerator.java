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
    int n = 1;

    for (int i = 0; i < n; i++) {
      Participant participant = type == ParticipantType.Superhero ? new Superhero() : new Sidekick();
      this.party.arrive(participant);
      this.party.leave(participant);
    }
  }
}