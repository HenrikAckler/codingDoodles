import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixStepper{
   private int n; //width
   private int m; //height
   private int[][] matrix;

   public static void main(String[] args) {
      System.out.println("Input dimensions and matrix file name, in format 'm n file.txt':");
      Scanner scnr = new Scanner(System.in);
      MatrixStepper myStepper = new MatrixStepper(scnr.nextInt(), scnr.nextInt(), scnr.next());
      System.out.println();
      myStepper.printMatrix();
      System.out.println("doing a rowOp");
      myStepper.rowOpThree(myStepper.matrix[0], myStepper.matrix[1], 2, true, false);
      myStepper.printMatrix();
   }

   /**Makes a new matrix stepper, sets everything up
    * 
    * @param m
    * @param n
    */
   public MatrixStepper(int m, int n, String fileName){
      this.n = n;
      this.m = m;
      matrix = new int[m][n];

      //start scanning the file
      try {
         File file = new File(fileName);
         Scanner fileScan = new Scanner(file);

         for(int i = 0; i < n * m ; i++){
            matrix[i / n][i % n] = fileScan.nextInt(); //absuing integer division
         }

         fileScan.close();
      } catch (Exception e) {
         System.out.println("Reading file failed. Womp womp.");
      }
   }

   /**Prints out the matrix to console */
   private void printMatrix(){
      for(int[] row : matrix){
         System.out.println(Arrays.toString(row));  
      }
   }

   /**Checks to make sure the row is divisible
    * 
    * @param row row to divide
    * @param divisor int to divide by
    * @return true if divisible, false otherwise
    */
   private boolean checkDiv(int[] row, int divisor){
      for(int cell : row){
         if(cell % divisor != 0) { return false;}
      }
      return true;
   }

   /**Swap rows. I think this works
    * 
    * @param row1
    * @param row2
    */
   private void rowSwap(int[] row1, int[] row2){
      int[] rowTmp = row1;
      row1 = row2;
      row2 = rowTmp;
   }

   /**multiply a row by a multiplier
    * 
    * @param rowTarget row to effect
    * @param mult multiplier if divide is false, divisor under 1 if divide is true
    * @param divide true if dividing, false if multiplying
    */
   private void rowMult(int[] rowTarget, int mult, boolean divide){
      if(divide){
         if(!checkDiv(rowTarget, mult)){return;} //no fail, just leave
      }

      //apply multiplier
      for(int i = 0; i < rowTarget.length; i++){
         rowTarget[i] = divide ? rowTarget[i] / mult : rowTarget[i] * mult;
      }
   }
   
   /**Perform a row operation type 3
    * 
    * @param rowTarget row to affect
    * @param rowTool row to use to affect the target
    * @param mult constant to multiply rowTool by
    * @param divide true if dividing, otherwise false
    * @param add true if adding, false if subtracting
    */
   private void rowOpThree(int[] rowTarget, int[] rowTool, int mult, boolean divide, boolean add){
      if(divide){
         if(!checkDiv(rowTool, mult)) {return;} //exit early if you can't do that
      }
      
      int[] tool = new int[rowTool.length]; //temp array to store modified values
      for(int i = 0; i < rowTool.length; i++){
         tool[i] = divide ? rowTool[i] / mult : rowTool[i] * mult; //modify value
         rowTarget[i] = add ? rowTarget[i] + tool[i] : rowTarget[i] - tool[i];//add or subtract
      }
   }
}