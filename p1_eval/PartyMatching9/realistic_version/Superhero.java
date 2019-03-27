import java.util.Random;
import java.util.UUID;

public class Superhero {

    int rank;
    Random rand;
    String type;
    UUID partyNumber;

    Superhero() {
        type = "Superhero";
        rand = new Random();
        rank = rand.nextInt(3);
        partyNumber = UUID.randomUUID();
    }
}
