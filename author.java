// check for packages
import java.util.ArrayList;
import java.util.List;

class Author {
    private String name;
    private String dateOfBirth;
    private List<LibraryItem> writtenItems;

    public Author(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.writtenItems = new ArrayList<>();
    }

    public void addWrittenItem(LibraryItem item) {
        writtenItems.add(item);
    }

    public String getName() {
        return name;
    }

    public List<LibraryItem> getWrittenItems() {
        return writtenItems;
    }

    @Override
    public String toString() {
        return "Author: " + name + ", DOB: " + dateOfBirth;
    }
}


