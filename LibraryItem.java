// check to see if needs packages later 

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

// Part of LibraryItem but still it's own class
class Book extends LibraryItem {
    public Book(String id, String title, String author, String isbn, String publisher, int copiesAvailable) {
        super(id, title, author, isbn, publisher, copiesAvailable);
    }

    @Override
    public String getItemType() {
        return "Book";
    }
}

class Periodical extends LibraryItem {
    private boolean isElectronic;

    public Periodical(String id, String title, String author, String isbn, String publisher, int copiesAvailable, boolean isElectronic) {
        super(id, title, author, isbn, publisher, copiesAvailable);
        this.isElectronic = isElectronic;
    }

    @Override
    public String getItemType() {
        return isElectronic ? "Electronic Periodical" : "Printed Periodical";
    }
}
