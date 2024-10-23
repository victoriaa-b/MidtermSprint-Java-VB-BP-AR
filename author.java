// check for packages
import java.util.ArrayList;
import java.util.List;

public class author {
    private String name;
    private String dateOfBirth;
    private List <LibraryItem> writtenItems;  // anything the author has worked on 

    // constructors
    public author (String name, String dateOfBirth){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.writtenItems = new ArrayList<>();
    }

    public void addWrittenItem(LibraryItem item) {
        writtenItems.add(item); // adding the author work to their list

    }

    public void removeWrittenItem(LibraryItem item) {
        writtenItems.remove(item); // removing the author work from the list 
    }

    // getters and setters

}
