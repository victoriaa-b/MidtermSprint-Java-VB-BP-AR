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

    public String getName() {
        return name;
    }

    public void addWrittenItem(LibraryItem item) {
        writtenItems.add(item);
    }

    public List<LibraryItem> getWrittenItems() {
        return writtenItems;
    }

    @Override
    public String toString() {
        return String.format("%s (DOB: %s)", name, dateOfBirth);
    }
}

