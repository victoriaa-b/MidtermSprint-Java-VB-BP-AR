import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        // Remove from author's writtenItems if necessary
        for (Author author : authors) {
            author.getWrittenItems().removeIf(item -> item.id.equals(id));
        }
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

    public boolean borrowItem(Patron patron, LibraryItem item, int numberOfCopies) {
        if (item.getCopiesAvailable() >= numberOfCopies) {
            item.borrowItem(numberOfCopies); // Decrease available copies
            patron.borrowItem(item, numberOfCopies);  // Add item to patron's borrowed items
            return true; // Successfully borrowed
        } else {
            System.out.println("Not enough copies available.");
            return false; // Failed to borrow
        }
    }
    
    public boolean returnItem(Patron patron, LibraryItem item, int numberOfCopies) {
        Map<LibraryItem, Integer> borrowedItems = patron.getBorrowedItems();
    
        // Check if the item exists in the borrowed items and if enough copies are available
        if (borrowedItems.containsKey(item) && borrowedItems.get(item) >= numberOfCopies) {
            patron.returnItem(item, numberOfCopies); // Remove item from patron's borrowed items
            item.returnItem(numberOfCopies); // Increase available copies
            return true; // Successfully returned
        } else {
            System.out.println("This item was not borrowed by this patron or not enough copies to return.");
            return false; // Failed to return
        }
    }

    public List<LibraryItem> searchItemsByTerm(String term) {
        List<LibraryItem> matchingItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(term.toLowerCase()) ||
                item.getISBN().toLowerCase().contains(term.toLowerCase()) ||
                (item.getAuthor() != null && item.getAuthor().getName().toLowerCase().contains(term.toLowerCase()))) {
                matchingItems.add(item);
            }
        }
        return matchingItems;
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
