import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Party {
    private ArrayList<Superhero> superheroes;
    private ArrayList<Sidekick> sidekicks;
    private Lock lock;
    private Condition superCondition;

    Party() {
        superheroes = new ArrayList<>();
        sidekicks = new ArrayList<>();
        lock = new ReentrantLock();
        superCondition = lock.newCondition();
    }

    void arrive(Superhero sup) {
        synchronized (this) {
            superheroes.add(sup);
            System.out.println("Hero of rank " + sup.rank + " has arrived");
        }
    }

    void arrive(Sidekick sid) {
        synchronized (this) {
            sidekicks.add(sid);
            System.out.println("Sidekick of rank " + sid.rank + " has arrived");
        }
    }

    boolean leave(Superhero hero) {
        lock.lock();
        Random rand = new Random();
        int num;

        num = rand.nextInt(sidekicks.size() - 1);

        try {
            if (sidekicks.size() <= 0) {
                superCondition.awaitNanos(500);
            }
        } catch (InterruptedException ignored) {
        }

        System.out.println("Hero of rank " + hero.rank + " has invited a Sidekick of rank " + sidekicks.get(num).rank
                + " to leave");
        if (hero.rank <= sidekicks.get(num).rank) {
            System.out.println(
                    "A Hero of rank " + hero.rank + " is leaving with a Sidekick of rank " + sidekicks.get(num).rank);
            superheroes.remove(hero);
            sidekicks.remove(num);
            lock.unlock();
            return true;
        }
        lock.unlock();
        return false;
    }

    void emptyLists() {
        synchronized (this) {
            System.out.println(superheroes.size() + " heros went home alone and " + sidekicks.size()
                    + " sidekicks went home alone");
            superheroes.clear();
            sidekicks.clear();
        }
    }

    int partyCount() {
        synchronized (this) {
            return superheroes.size() + sidekicks.size();
        }
    }
}
