// The LibraryItem class is an abstract base class representing an item in the library.
// It contains common attributes and methods that all library items will share, such as
// title, author, ISBN, publisher, and the number of copies available.

abstract class LibraryItem {
    protected String id; // Unique identifier for the library item
    protected String title; // Title of the item
    protected Author author; // Author of the item
    protected String isbn; // ISBN of the item
    protected String publisher; // Publisher of the item
    protected int copiesAvailable; // Number of copies available for borrowing

    // Constructor to initialize the library item with its attributes.
    public LibraryItem(String id, String title, Author author, String isbn, String publisher, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.copiesAvailable = copiesAvailable;
    }

    // Abstract method that must be implemented by subclasses to return the item type.
    public abstract String getItemType();

    // Getter methods for item attributes.
    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    // Method to borrow a specified number of copies of the item.
    public boolean borrowItem(int numberOfCopies) {
        if (copiesAvailable >= numberOfCopies) {
            copiesAvailable -= numberOfCopies; // Decrease available copies
            return true; // Successful borrowing
        } else {
            System.out.println("Not enough copies available to borrow.");
            return false; // Borrowing failed
        }
    }

    // Method to return a specified number of copies of the item.
    public void returnItem(int numberOfCopies) {
        copiesAvailable += numberOfCopies; // Increase available copies
    }

    // Returns a string representation of the library item, including type, title, author, etc.
    @Override
    public String toString() {
        return String.format("%s: %s by %s (ISBN: %s, Publisher: %s, Copies Available: %d)", 
                             getItemType(), title, author.getName(), isbn, publisher, copiesAvailable); 
    }
}

// The Book class represents a book in the library, extending the LibraryItem class.
// It adds a format attribute to specify the type of book (e.g., printed, electronic, audio).
class Book extends LibraryItem {
    private String format; // Format of the book ("Printed", "Electronic", or "Audio")

    // Constructor initializes the book's attributes and adds it to the author's written items.
    public Book(String id, String title, Author author, String isbn, String publisher, int copiesAvailable, String format) {
        super(id, title, author, isbn, publisher, copiesAvailable);
        this.format = format;
        author.addWrittenItem(this); // Add this book to the author's written items
    }

    // Returns the item type as "Book" along with its format.
    @Override
    public String getItemType() {
        return "Book (" + format + ")";
    }
}

// The Periodical class represents a periodical in the library, also extending LibraryItem.
// It specifies the format (printed or electronic) and behaves similarly to the Book class.
class Periodical extends LibraryItem {
    private String format; // Format of the periodical ("Printed" or "Electronic")

    // Constructor initializes the periodical's attributes and adds it to the author's written items.
    public Periodical(String id, String title, Author author, String isbn, String publisher, int copiesAvailable, String format) {
        super(id, title, author, isbn, publisher, copiesAvailable);
        this.format = format;
        author.addWrittenItem(this); // Add this periodical to the author's written items
    }

    // Returns the item type as "Periodical" along with its format.
    @Override
    public String getItemType() {
        return "Periodical (" + format + ")";
    }
}
