public class NumberSpiral {
   public static void main(String[] args){
      //setup variables
      int nNum = Integer.parseInt(args[0]);
      int[][] spiral = new int[nNum][nNum];
      int m, x, y;
      int parity = nNum % 2 == 0 ? -1 : 1;

      //solve
      for(int n = 0; n < nNum * nNum ; n++){
         m = (int)Math.floor(Math.sqrt(n));
         x = (int)(Math.pow(-1, m + 1) * ( (n - (m *(m + 1))) * ((Math.floor(2 * Math.sqrt(n)) + 1) % 2) + Math.ceil(m/2.0) ) );
         y = (int)(Math.pow(-1, m) * ( (n - (m *(m + 1))) * ((Math.floor(2 * Math.sqrt(n)) ) % 2) - Math.ceil(m/2.0) ) );

         spiral[(nNum - (nNum % 2)) / 2 + (parity * x)][(nNum - 1)/2 - (parity * y)] = (nNum * nNum) - n;
      }
      
      for (int[] line : spiral) {
         System.out.print("\n");
         for (int i : line) {
            System.out.print(i+", ");
         }
      }
   }
}
