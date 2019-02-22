package runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.*;
import javafx.util.Pair;
import data_types.Person;

public class Party {

	public static List<Person> partyList = new ArrayList<>();

	/* This variable will tell us if the list is being accessed currently. */
	public static boolean isBeingAccessed = false;

	static Lock lock = new ReentrantLock();
	static Condition isReady = lock.newCondition();

	public static void enter(Person person) {
		lock.lock();
		while (isBeingAccessed) {
			try {
				isReady.await(); // if the list is already being accessed somewhere, call await on the condition.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isBeingAccessed = true; // setting it true because we will access the list now
		partyList.add(person);
		if (person.isType()) {
			System.out.println("\t\t " + person.getName() + " entered");
		} else {
			System.out.println(person.getName() + " entered");
		}
		for (int i = 0; i < partyList.size(); i++) {
			Person sidekick = partyList.get(i);
			if (sidekick.getName().equalsIgnoreCase(person.getPartnerName())) {
				partyList.remove(sidekick); // sidekick leaving
				partyList.remove(person); // superhero leaving
				System.out.println("leaving : \t\t\t" + sidekick.getName() + "  with  " + person.getName());
				break; // remove one pair and step out of the loop
			}
		}
		isBeingAccessed = false; // done accessing the list
		isReady.signalAll(); // signal everyone who is waiting on the condition
		lock.unlock();
	}

}
