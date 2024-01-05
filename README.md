# Bookshelf-Keeper

### How to run the program

java -ea BookshelfKeeperProg

### Files

* **Bookshelf.java:** The interface for the Bookshelf class, that abstracts the idea of arranging books into a bookshelf so books don't fall down.

* **BookshelfKeeper.java:** The interface for the BookshelfKepper class that enables clients to perform efficient *put* or *pick* operation on a bookshelf while maintaining it in a sorted stage.

* **BookshelfKeeperProg.java:** A termal-based interactive program that allows the user to perform a series of *put* and *pick* operations on a BookshelfKeeper object.

### Description

A program that interacts with a bookshelf-keeper that maintains a shelf of books in increasing order by height. The program enables users to perform a series of *put* and *pick* operations via a BookshelfKeeper object that you’ll implement.

The *pick* operation refers to a case where you need to pick a book from a given position.

The *put* operation refers to putting a book of given height on the shelf, such that the bookshelf remains sorted after completing any operation.