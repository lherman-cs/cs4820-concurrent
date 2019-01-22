
class CountTen implements Runnable {
   private SynchronizedCounter c;

   public CountTen(SynchronizedCounter c) {
      this.c = c;
   }

   @Override
   public void run() {
      for (int i = 0; i < 10; i++) {
         this.c.increment();
         // try {
         // Thread.sleep(500);
         // } catch (InterruptedException e) {
         // System.out.println("Interrupted");
         // }
      }
      System.out.println(this.c.value());
   }
}
