abstract class LibraryItem {
    protected String id;
    protected String title;
    protected String author;
    protected String isbn;
    protected String publisher;
    protected int copiesAvailable;

    public LibraryItem(String id, String title, String author, String isbn, String publisher, int copiesAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
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
        getItemType(), title, author, isbn, publisher, copiesAvailable);
    }
}

class Book extends LibraryItem {
    private String format; // "Printed", "Electronic", or "Audio"

    public Book(String id, String title, String author, String isbn, String publisher, int copiesAvailable, String format) {
        super(id, title, author, isbn, publisher, copiesAvailable);
        this.format = format;
    }

    @Override
    public String getItemType() {
        return "Book (" + format + ")";
    }
}

class Periodical extends LibraryItem {
    private String format; // Format can be "Printed" or "Electronic"

    public Periodical(String id, String title, String author, String isbn, String publisher, int copiesAvailable, String format) {
        super(id, title, author, isbn, publisher, copiesAvailable);
        this.format = format;
    }

    @Override
    public String getItemType() {
        return "Periodical (" + format + ")";
    }
}
