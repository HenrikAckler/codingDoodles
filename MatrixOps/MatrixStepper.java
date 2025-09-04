import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixStepper{
   private int n; //width
   private int m; //height
   private int[][] matrix;
   private int[][] undo;

   public static void main(String[] args) {
      System.out.println("Input dimensions and matrix file name, in format 'm n file.txt':");
      Scanner scnr = new Scanner(System.in);
      MatrixStepper myStepper = new MatrixStepper(scnr.nextInt(), scnr.nextInt(), scnr.next());
      scnr.nextLine();
      System.out.println();
      myStepper.printMatrix();
      
      while(true){ //TODO: ctrl + c is not a valid termination method
         //System.out.println("Enter any single character to undo. Can only go back one.");
         System.out.println("Enter 2 row number in the format '1 2' to perform a swap.");
         System.out.println("Enter a row number, then a multiplier or divisor, and finally a t for divisor and f for multiplier in the format '1 2 t' to mult/divide the row");
         System.out.println("Enter 2 row numbers, followed by a mult/divisor, t/f flag (t for divide), and a t/f flag (t for add). Example: '1 2 3 t f' means r_1 = r_1 - 1/3r_2");

         myStepper.step(scnr);
         
      }
      //scnr.close();
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
     // undo = new int[m][n];

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

      //deepCopy(matrix, undo); //first backup
      
   }

   public void deepCopy(int[][] source, int[][] destination){
      //deep copy
      for(int row = 0; row < matrix.length; row++){
         for(int col = 0; col < matrix[row].length; col++){
            destination[row][col] = source[row][col];
         }
      }
   }

   private void step(Scanner scnr){
      String[] input = scnr.nextLine().split(" "); //grab the input
      boolean div, add;
      //deepCopy(matrix, undo); //save it
      switch (input.length){ //remember that rows are 1 indexed, so compensate
         case 1:
            deepCopy(matrix, undo);
            break;
         case 2: //swap
            rowSwap(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
            break;
         case 3: //rowmult
            div = input[2].charAt(0) == 't';
            rowMult(Integer.parseInt(input[0])-1, Integer.parseInt(input[1]), div);
            break;
         case 5:
            div = input[3].charAt(0) == 't';
            add = input[4].charAt(0) == 't';
            rowOpThree(Integer.parseInt(input[0])-1, Integer.parseInt(input[1])-1, Integer.parseInt(input[2]), div, add);
            break;
      }
         printMatrix();
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
   private void rowSwap(int row1, int row2){
      int[] rowTmp = matrix[row1];
      matrix[row1] = matrix[row2];
      matrix[row2] = rowTmp;
   }

   /**multiply a row by a multiplier
    * 
    * @param target row to effect
    * @param mult multiplier if divide is false, divisor under 1 if divide is true
    * @param divide true if dividing, false if multiplying
    */
   private void rowMult(int target, int mult, boolean divide){
      if(divide){
         if(!checkDiv(matrix[target], mult)){return;} //no fail, just leave
      }

      //apply multiplier
      for(int i = 0; i < matrix[target].length; i++){
         matrix[target][i] = divide ? matrix[target][i] / mult : matrix[target][i] * mult;
      }
   }
   
   /**Perform a row operation type 3
    * 
    * @param target row to affect
    * @param toolRow row to use to affect the target
    * @param mult constant to multiply rowTool by
    * @param divide true if dividing, otherwise false
    * @param add true if adding, false if subtracting
    */
   private void rowOpThree(int target, int toolRow, int mult, boolean divide, boolean add){
      if(divide){
         if(!checkDiv(matrix[toolRow], mult)) {return;} //exit early if you can't do that
      }

      System.out.println(target +" ");
      
      int[] tool = new int[matrix[toolRow].length]; //temp array to store modified values
      for(int i = 0; i < matrix[toolRow].length; i++){
         tool[i] = divide ? matrix[toolRow][i] / mult : matrix[toolRow][i] * mult;            //modify value
         matrix[target][i] = add ? matrix[target][i] + tool[i] : matrix[target][i] - tool[i];//add or subtract
      }
   }
}