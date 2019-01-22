class TestCounter {

   public static void main(String args[]) {
      SynchronizedCounter my_c = new SynchronizedCounter();
      CountTen one = new CountTen(my_c);
      CountTen two = new CountTen(my_c);
      Thread t1 = new Thread(one);
      Thread t2 = new Thread(two);
      t1.start();
      t2.start();
   }
}
