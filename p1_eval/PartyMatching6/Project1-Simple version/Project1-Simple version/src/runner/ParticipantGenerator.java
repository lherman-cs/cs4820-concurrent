package runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data_types.Person;
import javafx.util.Pair;

public class ParticipantGenerator implements Runnable {

	public static List<Pair<String, String>> superList = new ArrayList<Pair<String, String>>();

	public static void init() {
		superList.add(new Pair<String, String>("Batman", "Robin"));
		superList.add(new Pair<String, String>("Superman", "Superboy"));
		superList.add(new Pair<String, String>("Aquaman", "Aqualad"));
		superList.add(new Pair<String, String>("Green Arrow", "Speedy"));
		superList.add(new Pair<String, String>("Flash", "Kid Flash"));
	}

	@Override
	public void run() {
		Random rand = new Random();
		boolean coinFlip = rand.nextBoolean();
		int teamToUse = rand.nextInt(superList.size());

		if (coinFlip) {
			Person thePerson = new Person(superList.get(teamToUse).getKey(), true, superList.get(teamToUse).getValue()); // superHero
			Party.enter(thePerson);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Party.leave(thePerson.getName());

		} else {
			Person thePerson = new Person(superList.get(teamToUse).getValue(), false,
					superList.get(teamToUse).getKey()); // sidekick
			Party.enter(thePerson);
			Party.leave(thePerson.getName());
		}

	}

}
