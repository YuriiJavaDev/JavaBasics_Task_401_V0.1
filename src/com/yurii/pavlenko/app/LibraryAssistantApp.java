package com.yurii.pavlenko.app;

import com.yurii.pavlenko.library.models.Book;

/**
 * Main application class to demonstrate the digital library assistant.
 */
public class LibraryAssistantApp {

    public static void main(String[] args) {
        // Create an instance of the record for the favorite book
        Book favorite = new Book("The Master and Margarita", "Mikhail Bulgakov");

        // Display information using accessor methods
        // Record accessors do not use the 'get' prefix (e.g., title() instead of getTitle())
        System.out.println("Book: " + favorite.title());
        System.out.println("Author: " + favorite.author());
    }
}