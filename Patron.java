import java.util.HashMap;
import java.util.Map;

// The Patron class is an abstract base class representing a library user.
// It stores the patron's details and tracks borrowed library items, 
// allowing for borrowing and returning of items.

abstract class Patron {
    protected String name; // Patron's name
    protected String address; // Patron's address
    protected String phoneNumber; // Patron's phone number
    protected Map<LibraryItem, Integer> borrowedItems; // Map to track borrowed items and their counts

    // Constructor to initialize the patron's attributes and borrowed items map.
    public Patron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedItems = new HashMap<>(); // Initialize borrowed items as a HashMap
    }

    // Returns the patron's name.
    public String getName() {
        return name;
    }

    // Allows a patron to borrow a specified number of copies of a library item.
    public void borrowItem(LibraryItem item, int numberOfCopies) {
        borrowedItems.put(item, borrowedItems.getOrDefault(item, 0) + numberOfCopies);
    }

    // Allows a patron to return a specified number of copies of a library item.
    public void returnItem(LibraryItem item, int numberOfCopies) {
        if (borrowedItems.containsKey(item)) {
            int currentCount = borrowedItems.get(item);
            if (currentCount >= numberOfCopies) {
                borrowedItems.put(item, currentCount - numberOfCopies);
                // Remove the item from the map if all copies are returned
                if (currentCount == numberOfCopies) {
                    borrowedItems.remove(item);
                }
            } else {
                System.out.println("You cannot return more copies than you have borrowed.");
            }
        } else {
            System.out.println("This item was not borrowed by this patron.");
        }
    }

    // Displays the list of items borrowed by the patron.
    public void displayBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println(name + " has no borrowed items.");
        } else {
            System.out.println(name + "'s borrowed items:");
            for (Map.Entry<LibraryItem, Integer> entry : borrowedItems.entrySet()) {
                System.out.println(entry.getValue() + " copies of: " + entry.getKey().getTitle());
            }
        }
    }

    // Returns the map of borrowed items.
    public Map<LibraryItem, Integer> getBorrowedItems() {
        return borrowedItems;
    }

    // Returns a string representation of the patron, including their contact details.
    @Override
    public String toString() {
        return String.format("%s (Address: %s, Phone: %s)", name, address, phoneNumber);
    }
}

// The Student class represents a student patron of the library, extending Patron.
class Student extends Patron {
    public Student(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }
}

// The Employee class represents an employee patron of the library, extending Patron.
// It adds an employee ID to the patron's details.
class Employee extends Patron {
    private String employeeId; // Unique ID for the employee

    // Constructor initializes the employee's attributes and their employee ID.
    public Employee(String name, String address, String phoneNumber, String employeeId) {
        super(name, address, phoneNumber);
        this.employeeId = employeeId;
    }

    // Returns a string representation of the employee, including their employee ID.
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId;
    }
}
