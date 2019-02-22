package runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data_types.Person;
import javafx.util.Pair;

public class ParticipantGenerator implements Runnable {

	public static List<Pair<String, String>> superList = new ArrayList<Pair<String, String>>();

	public static void init() {
		// initializing the Pair list with superhero and sidekick names
		superList.add(new Pair<String, String>("Batman", "Robin"));
		superList.add(new Pair<String, String>("Superman", "Superboy"));
		superList.add(new Pair<String, String>("Aquaman", "Aqualad"));
		superList.add(new Pair<String, String>("Green Arrow", "Speedy"));
		superList.add(new Pair<String, String>("Flash", "Kid Flash"));
	}

	@Override
	public void run() {
		Random rand = new Random();
		boolean coinFlip = rand.nextBoolean();// random coin flip for deciding if we are going to add superhero or
												// sidekick
		int teamToUse = rand.nextInt(superList.size());// Random int value for team number within list size range

		if (coinFlip) {
			// Create superhero
			Person thePerson = new Person(superList.get(teamToUse).getKey(), true, superList.get(teamToUse).getValue()); // superHero
			Party.enter(thePerson);
		} else {
			// Create sidekick
			Person thePerson = new Person(superList.get(teamToUse).getValue(), false,
					superList.get(teamToUse).getKey()); // sidekick
			Party.enter(thePerson);
		}
	}

}
