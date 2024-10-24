
import java.util.ArrayList;
import java.util.List;

class Library {
    private List<LibraryItem> items;
    private List<Author> authors;
    private List<Patron> patrons;

    public Library() {
        items = new ArrayList<>();
        authors = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void removeItem(String id) {
        items.removeIf(item -> item.id.equals(id));
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public LibraryItem searchItemByTitle(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }

    public LibraryItem searchItemByISBN(String isbn) {
        for (LibraryItem item : items) {
            if (item.getISBN().equalsIgnoreCase(isbn)) {
                return item;
            }
        }
        return null;
    }

    public void borrowItem(Patron patron, LibraryItem item) {
        if (item != null) {
            patron.borrowItem(item);
        }
    }

    public void returnItem(Patron patron, LibraryItem item) {
        if (item != null) {
            patron.returnItem(item);
        }
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}

