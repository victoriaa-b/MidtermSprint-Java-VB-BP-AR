import java.util.ArrayList;
import java.util.List;

// The Author class represents an author with a name, date of birth, 
// and a list of library items they have written. It provides methods 
// to add written items and retrieve information about the author.

class Author {
    private String name; // The name of the author
    private String dateOfBirth; // The author's date of birth
    private List<LibraryItem> writtenItems; // A list of items written by the author

    // Constructor to initialize the author's name and date of birth, 
    // and to create an empty list for written items.
    public Author(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.writtenItems = new ArrayList<>();
    }

    // Returns the author's name.
    public String getName() {
        return name;
    }

    // Adds a library item to the list of items written by the author.
    public void addWrittenItem(LibraryItem item) {
        writtenItems.add(item);
    }

    // Returns the list of written items.
    public List<LibraryItem> getWrittenItems() {
        return writtenItems;
    }

    // Returns a string representation of the author, including name and date of birth.
    @Override
    public String toString() {
        return String.format("%s (DOB: %s)", name, dateOfBirth);
    }
}


