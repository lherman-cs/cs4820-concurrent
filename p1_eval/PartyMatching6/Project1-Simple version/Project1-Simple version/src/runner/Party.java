package runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.*;
import javafx.util.Pair;
import data_types.Person;

public class Party {

	private static List<Person> partyList = Collections.synchronizedList(new ArrayList<>());


	public static void enter(Person p) {
		synchronized (partyList) {
			partyList.add(p);
			System.out.println(p.getName() + "  Entered ");
		}

	}

	public static void leave(String name) {
		synchronized (partyList) {
			for (int i = 0; i < partyList.size(); i++) {
				if (name.equalsIgnoreCase(partyList.get(i).getName())) {
					partyList.remove(i);
					System.out.println(name + "  Left ");
					break;
				}
			}
		}

	}

}
