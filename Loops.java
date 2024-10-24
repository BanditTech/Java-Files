class Loops {
   public static void main(String[] string) {
      System.out.println("\n");
      for (int i = 1; i < 6; i++) {
         if (i % 2 == 0)
            System.out.println("For " + i);
         }                  
      int n = 0;
      while (n < 5) {
         n++;
         if (n % 2 == 0)
            System.out.println("While " + n);
         }
      n = 0;
      do {
         n++;
         if (n % 2 == 0)
            System.out.println("Do " + n);
      } while (n < 4);
   }
}