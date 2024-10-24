/// this isnt done, just the example that was given 
// add parts as we go

import java.util.Scanner;
import java.util.List;

public class LibraryMenu {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // The menu main window
        while (true) {
            System.out.println();
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Library Item");
            System.out.println("2. Borrow Item");
            System.out.println("3. Return Item");
            System.out.println("4. Add Author");
            System.out.println("5. Add Patron");
            System.out.println("6. Search Item by Title");
            System.out.println("7. Get list of books in library");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            System.out.println();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addLibraryItem();
                    break;
                case 2:
                    borrowItem();
                    break;
                case 3:
                    returnItem();
                    break;
                case 4:
                    addAuthor();
                    break;
                case 5:
                    addPatron();
                    break;
                case 6:
                    searchItemByTitle();
                    break;
                case 7:
                    getBookLists(); // Updated method call
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addLibraryItem() {
        // Get and store entered info by user
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter item title: ");
        String title = scanner.nextLine();
        System.out.print("Enter item author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter number of copies available: ");
        int copiesAvailable = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Is this a book or periodical (b/p)? ");
        char type = scanner.nextLine().charAt(0);
        if (type == 'b') {
            library.addItem(new Book(id, title, author, isbn, publisher, copiesAvailable));
        } else {
            System.out.print("Is it electronic (y/n)? ");
            boolean isElectronic = scanner.nextLine().equalsIgnoreCase("y");
            library.addItem(new Periodical(id, title, author, isbn, publisher, copiesAvailable, isElectronic));
        }
        System.out.println("Library item added.");
    }

    private static void borrowItem() {
        System.out.print("Enter patron's name: ");
        String patronName = scanner.nextLine();
        Patron patron = findPatronByName(patronName);

        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        System.out.print("Enter item title to borrow: ");
        String title = scanner.nextLine();
        LibraryItem item = library.searchItemByTitle(title);
        if (item != null) {
            library.borrowItem(patron, item);
            System.out.println("Item borrowed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void returnItem() {
        System.out.print("Enter patron's name: ");
        String patronName = scanner.nextLine();
        Patron patron = findPatronByName(patronName);

        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        System.out.print("Enter item title to return: ");
        String title = scanner.nextLine();
        LibraryItem item = library.searchItemByTitle(title);
        if (item != null) {
            library.returnItem(patron, item);
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void addAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        Author author = new Author(name, dob);
        library.addAuthor(author);
        System.out.println("Author added.");
    }

    private static void addPatron() {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patron address: ");
        String address = scanner.nextLine();
        System.out.print("Enter patron phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Is this a student or employee (s/e)? ");
        char type = scanner.nextLine().charAt(0);
        Patron patron = (type == 's') ? new Student(name, address, phoneNumber) : new Employee(name, address, phoneNumber);
        library.addPatron(patron);
        System.out.println("Patron added.");
    }

    private static void searchItemByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        LibraryItem item = library.searchItemByTitle(title);
        if (item != null) {
            System.out.println(item);
        } else { 
            System.out.println("Item not found.");
        }
    }

    private static void getBookLists() {
        System.out.println("All books in library:");
        List<LibraryItem> items = library.getItems();
    
        boolean foundBook = false;
        for (LibraryItem item : items) {
            if (item instanceof Book) { // Check if item is an instance of Book
                System.out.println(item); // Print the item details
                foundBook = true;
            }
    }
    
    if (!foundBook) {
        System.out.println("No books found in the library.");
    }
    }

    private static Patron findPatronByName(String name) {
        for (Patron patron : library.getPatrons()) {
            if (patron.name.equalsIgnoreCase(name)) {
                return patron;
            }
        }
        return null;
    }
}

