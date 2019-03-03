import java.util.concurrent.ArrayBlockingQueue;

class Party {
  private final int CAP = 100;
  private final ArrayBlockingQueue<Sidekick> sidekicks = new ArrayBlockingQueue<Sidekick>(CAP);

  public void arrive(Participant participant) {
    if (participant instanceof Superhero) {
      System.out.println("Superhero " + participant.id + " arrives");
    } else {
      Sidekick sidekick = (Sidekick) participant;
      System.out.println("Sidekick " + sidekick.id + " arrives");
      sidekicks.add(sidekick);
    }
  }

  public void leave(Participant participant) {
    if (participant instanceof Superhero) {
      Superhero superhero = (Superhero) participant;
      while (true) {
        try {
          Sidekick sidekick = sidekicks.take();
          if (superhero.invite(sidekick))
            break;
          else
            sidekicks.add(sidekick);

        } catch (InterruptedException e) {
          continue;
        }
      }
      System.out.println("Superhero " + superhero.id + " exits party");

    } else {
      Sidekick sidekick = (Sidekick) participant;

      while (!sidekick.accept()) {
      }
      System.out.println("Sidekick " + sidekick.id + " exits party");
    }
  }
}