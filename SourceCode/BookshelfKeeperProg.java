import java.util.Scanner;
import java.util.ArrayList;

/**
 * class BookshelfKeeperProg
 *
 * Enables I/O in which a user can input a string of heights followed by pick <index> or put <height> commands
 * this class uses the BookshelfKeeper putHeight and pickPos methods
 */

public class BookshelfKeeperProg {

   /**
    *
    * Here the user first inputs a list of numbers that have to be positive and in non-decreasing
    * if requirements met, then it moves on into asking for pick or put until typing "end."
    */
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.println("Please enter initial arrangement of books followed by newline:");

      String heights = in.nextLine();

      if(checkHeights(heights)) {
         BookshelfKeeper bookshelf = addHeights(heights);

         moreAction(in, bookshelf);

      }

   }

   /**
    * checkHeights will check the heights and make sure that the inputted heights are positive and sorted
    * returns false if it doesn't meet the requirements and true if it does
    * it will also print out an error message depending on which requirement the heights don't satisfy
    */
   private static boolean checkHeights(String heights){
      Scanner heightReader = new Scanner(heights);
      ArrayList<Integer> heightList = new ArrayList<Integer>();
      while(heightReader.hasNextInt()){
         int i = heightReader.nextInt();
         if(i <= 0){
            System.out.println("ERROR: Height of a book must be positive.");
            System.out.println("Exiting Program.");
            return false;
         }
         heightList.add(i);
      }
      for(int i = 0; i < heightList.size() - 1; i++){
         if (heightList.get(i) > heightList.get(i+1)){
            System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            System.out.println("Exiting Program.");
            return false;
         }
      }
      return true;
   }

   /**
    * addHeights returns a BookshelfKeeper object
    * it inputs a String heights, if it is not empty it will add the numbers into a BookshelfKeeper object
    * otherwise it creates an empty BookshelfKeeper object
    */
   private static BookshelfKeeper addHeights(String nums){
      Scanner heightScanner = new Scanner(nums);
      ArrayList<Integer> heightList = new ArrayList<Integer>();
      BookshelfKeeper bookshelf;
      if(!nums.isEmpty()){
         while(heightScanner.hasNextInt()){
            int i = heightScanner.nextInt();
            heightList.add(i);
         }

         Bookshelf temp = new Bookshelf(heightList);
         bookshelf = new BookshelfKeeper(temp);
         System.out.println(bookshelf);
      }
      else {
         bookshelf = new BookshelfKeeper();
         System.out.println(bookshelf);
      }
      return bookshelf;
   }

   /**
    * moreAction passes parameters Scanner in and BookshelfKeeper bookshelf
    * it will putHeight if user entered "put" followed by a height
    * it will pickPos if user entered "pick" followed by an index
    * and it will terminate if user entered "end"
    * for each case it will error check and print out an error message followed by termination of the program
    */
   private static void moreAction(Scanner in, BookshelfKeeper bookshelf){
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      while(in.hasNextLine()){
         String action = in.next();
         if(action.equals("put")){
            int height = in.nextInt();
            if(height <= 0){
               System.out.println("ERROR: Height of a book must be positive.");
               System.out.println("Exiting Program.");
               break;
            }
            bookshelf.putHeight(height);
            System.out.println(bookshelf);
         }
         else if(action.equals("pick")){
            int index = in.nextInt();
            if(index >= bookshelf.getNumBooks()){
               System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
               System.out.println("Exiting Program.");
               break;
            }
            bookshelf.pickPos(index);
            System.out.println(bookshelf);
         }
         else if(action.equals("end")) {
            System.out.println("Exiting Program.");
            break;
         }
         else{
            System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
            System.out.println("Exiting Program.");
            break;
         }
      }
   }
}
