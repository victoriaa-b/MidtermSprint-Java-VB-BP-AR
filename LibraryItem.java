abstract class LibraryItem {
    protected String id;
    protected String title;
    protected Author author;
    protected String isbn;
    protected String publisher;
    protected int copiesAvailable;

    public LibraryItem(String id, String title, Author author, String isbn, String publisher, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.author = author; // Store Author object
        this.isbn = isbn;
        this.publisher = publisher;
        this.copiesAvailable = copiesAvailable;
    }

    public abstract String getItemType();

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return isbn;
    }

    public Author getAuthor() {  // Getter for author
        return author;
    }

    public void borrowItem() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        } else {
            System.out.println("Item is currently checked out.");
        }
    }

    public void returnItem() {
        copiesAvailable++;
    }

    @Override
    public String toString() {
        return String.format("%s: %s by %s (ISBN: %s, Publisher: %s, Copies Available: %d)", 
        getItemType(), title, author.getName(), isbn, publisher, copiesAvailable); // Use author.getName()
    }
}

class Book extends LibraryItem {
    private String format; // "Printed", "Electronic", or "Audio"

    public Book(String id, String title, Author author, String isbn, String publisher, int copiesAvailable, String format) {
        super(id, title, author, isbn, publisher, copiesAvailable); // Pass Author object directly
        this.format = format;
        author.addWrittenItem(this); // Add this book to the author's written items
    }

    @Override
    public String getItemType() {
        return "Book (" + format + ")";
    }
}

class Periodical extends LibraryItem {
    private String format; // Format can be "Printed" or "Electronic"

    public Periodical(String id, String title, Author author, String isbn, String publisher, int copiesAvailable, String format) {
        super(id, title, author, isbn, publisher, copiesAvailable); // Pass Author object directly
        this.format = format;
        author.addWrittenItem(this); // Add this periodical to the author's written items
    }

    @Override
    public String getItemType() {
        return "Periodical (" + format + ")";
    }
}
