/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
      Representation invariant:

      -- books CANNOT be null
      -- heights HAVE to be in non-decreasing order
      -- totalCalls >= 0
      -- lastCalls >= 0

   */
   
   // <add instance variables here>
   private Bookshelf books;
   private int lastCall;
   private int totalCalls;



   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      books = new Bookshelf();
      lastCall = 0;
      totalCalls = 0;
      assert isValidBookshelfKeeper();

   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      books = sortedBookshelf;
      lastCall = 0;
      totalCalls = 0;
      assert isValidBookshelfKeeper();

   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted 
    * after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      int calls = 0;
      int height = books.getHeight(position);
      Bookshelf temp = new Bookshelf();

      //starting from front side
      if(whichSide(height)){
         int index = 0;
         while(index < position){
            temp.addFront(books.removeFront());
            calls++;
            index++;
         }
         books.removeFront();
         calls++;
         for(int i = 0; i < temp.size(); i++) {
            books.addFront(temp.getHeight(i));
            calls++;
         }
         totalCalls += calls;
         lastCall = calls;
      }
      //starting from the end
      else{
         int index2 = books.size()-1;
         while(index2 > position){
            temp.addFront(books.removeLast());
            calls++;
            index2--;
         }
         books.removeLast();
         calls++;
         for(int i = 0; i < temp.size(); i++){
            books.addLast(temp.getHeight(i));
            calls++;
         }
         totalCalls += calls;
         lastCall = calls;
      }
      assert isValidBookshelfKeeper();
      return calls;
   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted 
    * after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      int calls = 0;
      Bookshelf temp = new Bookshelf();
      if(books.size() == 0){
         books.addFront(height);
         calls++;
         totalCalls += calls;
         lastCall = calls;
      }
      //starts from the front side
      else if(whichSide(height)){
         while(height > books.getHeight(0)){
            temp.addFront(books.removeFront());
            calls++;
         }
         books.addFront(height);
         calls++;
         for(int i = 0; i < temp.size(); i++){
            books.addFront(temp.getHeight(i));
            calls++;
         }
         totalCalls += calls;
         lastCall = calls;
      }
      //starts from the end
      else{
         while(height < books.getHeight(books.size()-1)){
            temp.addFront(books.removeLast());
            calls++;
         }
         books.addLast(height);
         calls++;
         for(int i = 0; i < temp.size(); i++){
            books.addLast(temp.getHeight(i));
            calls++;
         }
         totalCalls += calls;
         lastCall = calls;
      }
      assert isValidBookshelfKeeper();
      return calls;
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      int total = totalCalls;
      assert isValidBookshelfKeeper();
      return total;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      int size = books.size();
      assert isValidBookshelfKeeper();
      return size;
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed 
    * by the number of bookshelf mutator calls made to perform the last pick or put operation, 
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      String output = books.toString();
      assert isValidBookshelfKeeper();
      return output + " " + lastCall + " " + totalCalls;
      
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      if(books == null){
         return false;
      }
      if(!books.isSorted()){
         return false;
      }

      return true;
   }

   // add any other private methods here

   /**
    * whichSide takes in an int height and
    * returns true if it will start from the front
    * returns false if it will start from the end
    */
   private boolean whichSide(int height){
      if(height <= books.getHeight((books.size()-1)/2)){
         return true;
      }
      return false;
   }


}
