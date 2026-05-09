## Differences between record and class, limitations of record.

### 1. Comparing Record and Class: What Are the Main Differences?

In Java, we have two main ways to describe our data types: through regular classes (**class**) and through record classes (**record**). At first glance, both options allow us to store and process data. But if you dig a little deeper, there are more differences than might seem!

#### Difference Table: Class vs. Record

| **Characteristic** | **Regular Class** | **Record Class** |
| --- | --- | --- |
| **Mitigability** | Any: can make fields final or not | Immutable: all fields are final |
| **Inheritance** | Can extend, not final by default | Always final, cannot be a superclass |
| **Fields** | Any: static, non-static, final or non-final, any types | Only record components (private final), plus static fields |
| Getters/Setters** | Write them yourself (or generate them with Lombok) | Getters are automatically generated (field name as method name), no setters |
| Equals/HashCode/ToString** | Usually write them manually/generate them (equals, hashCode, toString) | Generated automatically for all components |
| Constructors** | Any, as many as you like | One main one (for all components), a compact constructor can be added |
| Interfaces** | Can be implemented | Can be implemented |
| Additional Methods** | Any | Can be added, but only methods (not fields) |
| Usage in Collections** | It's possible, but you need to implement **equals/hashCode** correctly | Ideal for keys/values, everything is already implemented |

#### Example for clarity

**Regular class:**

```java
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    
    @Override
    public boolean equals(Object o) {
        /* ... */
    }
    @Override
    public int hashCode() {
        /* ... */
    }
    @Override
    public String toString() {
        /* ... */
    }
}
```

**Record Class:**

```java
public record Person(String name, int age) {
}
```

That's it! One line of code—and we get the same thing (and even better). And no risk of forgetting to implement something important.

### 2. Limitations of Record Classes

Record classes aren't just a "short syntax," but a separate concept with its own strict rules. Let's take a closer look.

#### Record is always final

A record class is, by definition, always **final**. This means you can't create a subclass of **record**:

```java
public record Point(int x, int y) {
}

// public class ColoredPoint extends Point { } // Compilation error!
```

If you need to extend behavior, use regular classes or composition (embed record in a class).

#### Record cannot be a superclass

A record class cannot be a parent of other classes; it is always final. This makes sense: if this were possible, someone could add a mutable field, and the whole concept of "immutable data" would collapse.

#### Only final fields (components)

All record components are declared in the header and are private final by default. You cannot add non-static fields to the body of a **record**:

```java
public record User(String login, String email) {
    // int counter; // Error! You cannot add non-static fields
    static int totalUsers = 0; // Yes, it's a static field
}
```

#### No setters

A record class cannot have setters for its components. Any attempt to add a method like **setX(int x)** will be pointless: you won't be able to change the field's value after the object is created.

```java
public record Point(int x, int y) {
    // public void setX(int x) { this.x = x; } // Error: You cannot change a final field
}
```

#### No empty constructor

A **record** class always has only a primary constructor, which accepts values for all components. Cannot create a **record** without specifying all data:

```java
    Point p = new Point(1, 2); // OK
    // Point p = new Point(); // Error: no parameterless constructor
```

#### No non-static initializers

A Record class cannot contain non-static initializers (those written in curly braces outside of methods):

```java
public record User(String login) {
    // { /* ... */ } // Error: non-static initializers are not allowed
}
```

#### Inheritance Restrictions

A Record class cannot explicitly inherit another class (except for **java.lang.Record**, which is hidden from us as the base class for all records). But implementing interfaces is welcome!

```java
public interface Printable {
    void print();
}

public record Book(String title) implements Printable {
    @Override
    public void print() {
        System.out.println("Printing a book: " + title);
    }
}
```

#### Not suitable for complex business logic

**record** is about data, not behavior. If your object has complex logic, mutable state, a lifecycle, or a bunch of dependencies, **record** won't help. It's better to use a regular class.

### 3. When should you use record classes?

- **DTO (Data Transfer Object):** for transferring immutable data between application layers, services, microservices, or REST controllers (e.g., in JSON responses).
- **Value Object:** objects defined only by their values.
- **Keys and values in collections:** when the correct implementation of **equals** and **hashCode** is important (e.g., for use in **HashMap** or **Set**).
- **Calculation results:** when you need to return multiple values from a method at once (for example, **record Pair<T, U>(T first, U second)**).

#### Example: DTO for a REST controller

```java
public record UserDto(String login, String email) {
}
```

Now you can safely return an object of this type from the controller without worrying about someone changing its fields.

#### Example: Key for a HashMap

```java
public record Point(int x, int y) {
}

    Map<Point, String> pointNames = new HashMap<>();
    pointNames.put(new Point(1, 2), "A");
    pointNames.put(new Point(3, 4), "B");
    
    // Everything works correctly: equals and hashCode are already implemented!
```

### 4. When should you NOT use record classes?

- **Mutable state:** if at least one field should change after the object is created.
- **Complex logic:** if the object has complex behavior, many methods, or nested objects with mutable state.
- **Inheritance:** if a class hierarchy, abstract base classes, or method overriding are required.
- **Business logic entities:** for example, objects that live in a database and have a unique identifier.

#### Example: When a regular class is needed

```java
public class Account {
    private String id;
    private int balance;
    
    public Account(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }
    
    public void deposit(int amount) {
        balance += amount;
    }
    public void withdraw(int amount) {
        balance -= amount;
    }
    // getters, setters, equals, hashCode, toString...
}
```

Here it's clear that the object's state is changing—**record** is not suitable.

### 5. Practical Examples: Choosing Between Record and Class

#### Example 1: Record — the Perfect Choice

```java
public record Rectangle(int width, int height) {
    public int area() {
        return width * height;
    }
}
```

- A rectangle is defined only by its width and height.
- There's no need to change these values after creation.
- You can add the useful **area()** method.
- Java will do the rest for you.

#### Example 2: Class — the Best Option

```java
public class MutableRectangle {
    private int width;
    private int height;
    
    public MutableRectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int area() {
        return width * height;
    }
}
```

Need to resize a rectangle after creation? Use a regular class.

### 6. Common Errors When Working with Record Classes

**Error №1: Attempting to add a non-static field.**

The Record class does not allow declaring non-static fields outside the component list. If you try, the compiler will generate an error. For example:

```java
public record City(String name) {
    // int population; // Error!
}
```

**Error №2: Wanting to add a setter.**

Record doesn't support setters for components. Any attempt to change a field's value after the object's creation results in a compilation error.

**Error №3: Attempting to inherit from or derive from Record.**

Record is always final. You can't inherit from Record, and Record can't derive from another class (except the hidden java.lang.Record).

**Error №4: Using Record for mutable objects.**

If you plan to change the object's state after creation, Record isn't for you! Use a regular class.

**Error №5: Forgetting about constructor restrictions.**

A Record class must have a constructor that accepts values for all components. There is no parameterless constructor!
