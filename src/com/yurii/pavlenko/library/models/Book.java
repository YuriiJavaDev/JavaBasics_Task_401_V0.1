package com.yurii.pavlenko.library.models;

/**
 * A record class representing a book in the library.
 * Records automatically provide accessors, toString, equals, and hashCode.
 */
public record Book(String title, String author) {
}