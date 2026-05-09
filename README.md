# Home Library Assistant (JavaBasics_Task_401_V0.1)

## 📖 Description
Managing a large collection of books requires a structured and reliable data model. This project demonstrates the implementation of a **Java Record** to act as a digital entry for a home library assistant. By utilizing the `record` keyword, we benefit from built-in immutability, thread safety, and automatic generation of accessor methods (`title()` and `author()`). This approach minimizes boilerplate code while ensuring that book information remains consistent and easy to access.

## 📋 Requirements Compliance
- **Data Carrier**: Defined a `Book` record to encapsulate essential bibliographic details.
- **Immutability**: Guaranteed that once a book is added to the assistant, its details cannot be altered.
- **Clean Accessors**: Used standard record methods for retrieving data.

## 🚀 Architectural Stack
- Java 16+ (Records)

## 🏗️ Implementation Details
- **Book**: A record serving as the primary data model for the library.
- **LibraryAssistantApp**: The entry point for creating and verifying library records.

## 📋 Expected result
```text
Book: The Master and Margarita
Author: Mikhail Bulgakov
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_401/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── LibraryAssistantApp.java
    │                 └── library/
    │                     └── models/
    │                         └── Book.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.library.models.Book;

public class LibraryAssistantApp {
    public static void main(String[] args) {
        Book favorite = new Book("The Master and Margarita", "Mikhail Bulgakov");
        System.out.println("Book: " + favorite.title());
        System.out.println("Author: " + favorite.author());
    }
}
```
```java
package com.yurii.pavlenko.library.models;

public record Book(String title, String author) {
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
