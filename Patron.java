import java.util.ArrayList;
import java.util.List;

abstract class Patron {
    protected String name;
    protected String address;
    protected String phoneNumber;
    protected List<LibraryItem> borrowedItems;

    public Patron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedItems = new ArrayList<>();
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    @Override
    public String toString() {
        return String.format("%s (Address: %s, Phone: %s)", name, address, phoneNumber);
    }
}

class Student extends Patron {
    public Student(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }
}

// Employee number
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

