
import java.util.HashMap;
import java.util.Map;

abstract class Patron {
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected Map<LibraryItem, Integer> borrowedItems; // Change to Map for counting copies

    public Patron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedItems = new HashMap<>(); // Initialize with HashMap
    }

    public String getName() {
        return name; // Ensure this method exists
    }

    public void borrowItem(LibraryItem item, int numberOfCopies) {
        borrowedItems.put(item, borrowedItems.getOrDefault(item, 0) + numberOfCopies);
    }

    public void returnItem(LibraryItem item, int numberOfCopies) {
        if (borrowedItems.containsKey(item)) {
            int currentCount = borrowedItems.get(item);
            if (currentCount >= numberOfCopies) {
                borrowedItems.put(item, currentCount - numberOfCopies);
                if (currentCount == numberOfCopies) {
                    borrowedItems.remove(item); // Remove the item if all copies are returned
                }
            } else {
                System.out.println("You cannot return more copies than you have borrowed.");
            }
        } else {
            System.out.println("This item was not borrowed by this patron.");
        }
    }

    // Method to display borrowed items
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

    public Map<LibraryItem, Integer> getBorrowedItems() {
        return borrowedItems;
    }

    @Override
    public String toString() {
        return String.format("%s (Address: %s, Phone: %s)", name, address, phoneNumber);
    }
}

// Student class
class Student extends Patron {
    public Student(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }
}

// Employee class
class Employee extends Patron {
    private String employeeId;

    public Employee(String name, String address, String phoneNumber, String employeeId) {
        super(name, address, phoneNumber);
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId;
    }
}
