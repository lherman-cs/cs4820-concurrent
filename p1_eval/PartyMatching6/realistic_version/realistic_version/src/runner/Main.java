package runner;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ParticipantGenerator.init();

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < 20; i++) {
			list.add(new Thread(new ParticipantGenerator()));
		}

		for (int i = 0; i < 20; i++) {
			((Thread) list.get(i)).start();
		}

		for (int i = 0; i < 20; i++) {
			((Thread) list.get(i)).join(1000);
		}

	}
}
