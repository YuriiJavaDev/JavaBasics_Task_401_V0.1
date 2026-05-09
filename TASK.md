### You've decided to organize your extensive home library and create a digital assistant to do so.

#### - First, you need to determine how information about each book will be stored. Create a special record class that will contain the book's title and author's name.

#### - Then, in the main part of your program, create an instance of this record, for example, for your favorite book, specifying its title and author.

#### - Finally, display all the information about this book to the screen to verify that the record was created correctly.

```java

public class LibraryAssistantApp {
    public static void main(String[] args) {
        // Create an instance of the record class for your favorite book
        Book favorite = new Book("The Master and Margarita", "Mikhail Bulgakov");

        // Display information about the book.
        // Data is accessed through the automatically generated title() and author() accessor methods.
        System.out.println("Book: " + favorite.title());
        System.out.println("Author: " + favorite.author());

        // Additional: record has an auto-generated toString()
        // System.out.println(favorite); // Example: Book[title=..., author=...]
    }
}
```
