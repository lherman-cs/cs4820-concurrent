class TestCounter2 {

   public static void main(String args[]) {
      SynchronizedCounter my_c = new SynchronizedCounter();
      CountTen one = new CountTen(my_c);
      CountTen two = new CountTen(my_c);
      Thread t1 = new Thread(one);
      Thread t2 = new Thread(two);
      System.out.printf("Before: %s\n", my_c.value());

      t1.start();
      t2.start();

      try {
         t1.join();
         t2.join();
      } catch (InterruptedException e) {
         System.out.println(e);
      }

      System.out.printf("After: %s", my_c.value());

   }
}
