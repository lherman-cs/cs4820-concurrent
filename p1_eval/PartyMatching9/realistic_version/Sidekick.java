import java.util.Random;

public class Sidekick {
    int rank;
    Random rand;
    String type;

    Sidekick() {
        type = "Sidekick";
        rand = new Random();
        rank = rand.nextInt(3);
    }
}
