import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// The Library class manages a collection of library items, authors, and patrons. 
// It provides methods for adding and removing items, searching for items, 
// and handling borrowing and returning of items by patrons.

class Library {
    private List<LibraryItem> items; // List of library items (books, magazines, etc.)
    private List<Author> authors; // List of authors associated with the library items
    private List<Patron> patrons; // List of patrons (library users)

    // Constructor initializes empty lists for items, authors, and patrons.
    public Library() {
        items = new ArrayList<>();
        authors = new ArrayList<>();
        patrons = new ArrayList<>();
    }

    // Adds a library item to the collection.
    public void addItem(LibraryItem item) {
        items.add(item);
    }

    // Removes a library item by its ID and updates authors' written items accordingly.
    public void removeItem(String id) {
        items.removeIf(item -> item.id.equals(id));
        // Remove from authors' written items if necessary
        for (Author author : authors) {
            author.getWrittenItems().removeIf(item -> item.id.equals(id));
        }
    }

    // Adds an author to the library's author list.
    public void addAuthor(Author author) {
        authors.add(author);
    }

    // Adds a patron to the library's patron list.
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    // Searches for a library item by its title and returns it if found.
    public LibraryItem searchItemByTitle(String title) {
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null; // Return null if not found
    }

    // Searches for a library item by its ISBN and returns it if found.
    public LibraryItem searchItemByISBN(String isbn) {
        for (LibraryItem item : items) {
            if (item.getISBN().equalsIgnoreCase(isbn)) {
                return item;
            }
        }
        return null; // Return null if not found
    }

    // Allows a patron to borrow a specified number of copies of a library item.
    public boolean borrowItem(Patron patron, LibraryItem item, int numberOfCopies) {
        if (item.getCopiesAvailable() >= numberOfCopies) {
            item.borrowItem(numberOfCopies); // Decrease available copies
            patron.borrowItem(item, numberOfCopies); // Add item to patron's borrowed items
            return true; // Successfully borrowed
        } else {
            System.out.println("Not enough copies available.");
            return false; // Failed to borrow
        }
    }

    // Allows a patron to return a specified number of copies of a library item.
    public boolean returnItem(Patron patron, LibraryItem item, int numberOfCopies) {
        Map<LibraryItem, Integer> borrowedItems = patron.getBorrowedItems();
    
        // Check if the item exists in the borrowed items and if enough copies are being returned
        if (borrowedItems.containsKey(item) && borrowedItems.get(item) >= numberOfCopies) {
            patron.returnItem(item, numberOfCopies); // Remove item from patron's borrowed items
            item.returnItem(numberOfCopies); // Increase available copies
            return true; // Successfully returned
        } else {
            System.out.println("This item was not borrowed by this patron or not enough copies to return.");
            return false; // Failed to return
        }
    }

    // Searches for items that match a search term in title, ISBN, or author's name.
    public List<LibraryItem> searchItemsByTerm(String term) {
        List<LibraryItem> matchingItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(term.toLowerCase()) ||
                item.getISBN().toLowerCase().contains(term.toLowerCase()) ||
                (item.getAuthor() != null && item.getAuthor().getName().toLowerCase().contains(term.toLowerCase()))) {
                matchingItems.add(item); // Add matching item to the list
            }
        }
        return matchingItems; // Return list of matching items
    }

    // Returns the list of all library items.
    public List<LibraryItem> getItems() {
        return items;
    }

    // Returns the list of patrons registered at the library.
    public List<Patron> getPatrons() {
        return patrons;
    }

    // Returns the list of authors associated with the library.
    public List<Author> getAuthors() {
        return authors;
    }
}
