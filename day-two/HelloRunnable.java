public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
      HelloRunnable h;
      h = new HelloRunnable();
      Thread t;
      t = new Thread(h);
      t.start();
    }

}
