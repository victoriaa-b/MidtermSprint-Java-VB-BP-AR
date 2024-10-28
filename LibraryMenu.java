import java.util.List;
import java.util.Scanner;

public class LibraryMenu {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add library item");
            System.out.println("2. Add author");
            System.out.println("3. Add patron");
            System.out.println("4. Borrow item");
            System.out.println("5. Return item");
            System.out.println("6. Search item by title");
            System.out.println("7. Get list of items in library");
            System.out.println("8. Get list of all authors");
            System.out.println("9. Get list of all patrons");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addLibraryItem();
                    break;
                case 2:
                    addAuthor();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    borrowItem();
                    break;
                case 5:
                    returnItem();
                    break;
                case 6:
                    searchItemByTitle();
                    break;
                case 7:
                    getLibraryItems();
                    break;
                case 8:
                    showAllAuthors();
                    break;
                case 9:
                    showAllPatrons();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addLibraryItem() {
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter item title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: "); // Ask for author's name
        String authorName = scanner.nextLine();
        
        // Find the author from the library's author list
        Author author = findAuthorByName(authorName);
        if (author == null) {
            System.out.println("Author not found. Would you like to create a new author? (y/n)");
            char createNew = scanner.nextLine().charAt(0);
            if (createNew == 'y') {
                System.out.print("Enter date of birth (YYYY-MM-DD): ");
                String dob = scanner.nextLine();
                author = new Author(authorName, dob);
                library.addAuthor(author);
                System.out.println("New author added: " + author);
            } else {
                System.out.println("Item cannot be added without a valid author.");
                return;
            }
        }
        
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter number of copies available: ");
        int copiesAvailable = scanner.nextInt();
        scanner.nextLine(); // Consume newline
    
        System.out.print("Is this a book or periodical (b/p)? ");
        char type = scanner.nextLine().charAt(0);
        
        LibraryItem item;
        if (type == 'b') {
            System.out.print("Enter format (Printed, Electronic, Audio): ");
            String format = scanner.nextLine();
            item = new Book(id, title, author, isbn, publisher, copiesAvailable, format); 
        } else {
            System.out.print("Enter format (Printed, Electronic): ");
            String format = scanner.nextLine();
            item = new Periodical(id, title, author, isbn, publisher, copiesAvailable, format);
        }
        
        library.addItem(item);
        System.out.println("Library item added: " + item);
    }
    
    private static Author findAuthorByName(String name) {
        for (Author author : library.getAuthors()) {
            if (author.getName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null;
    }    
    

    private static void borrowItem() {
        System.out.print("Enter patron's name: ");
        String patronName = scanner.nextLine();
        Patron patron = findPatronByName(patronName);
    
        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        System.out.print("Enter search term (title, ISBN, or author): ");
        String searchTerm = scanner.nextLine();
        List<LibraryItem> items = library.searchItemsByTerm(searchTerm);
        displayFoundItems(items);
    
        System.out.print("Enter the title of the item you want to borrow: ");
        String itemInput = scanner.nextLine();
        LibraryItem itemToBorrow = library.searchItemByTitle(itemInput);
        
        if (itemToBorrow != null) {
            library.borrowItem(patron, itemToBorrow);
            System.out.println("Item borrowed successfully: " + itemToBorrow);
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

        System.out.print("Enter search term (title, ISBN, or author): ");
        String searchTerm = scanner.nextLine();
        List<LibraryItem> items = library.searchItemsByTerm(searchTerm);
        displayFoundItems(items);
    
        System.out.print("Enter the title of the item you want to return: ");
        String itemInput = scanner.nextLine();
        LibraryItem itemToReturn = library.searchItemByTitle(itemInput);
        
        if (itemToReturn != null) {
            library.returnItem(patron, itemToReturn);
            System.out.println("Item returned successfully: " + itemToReturn);
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void displayFoundItems(List<LibraryItem> items) {
        if (items.isEmpty()) {
            System.out.println("No items found for the search term.");
        } else {
            System.out.println("Items found:");
            for (LibraryItem item : items) {
                System.out.println(item);
            }
        }
    }

    private static void addAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        Author author = new Author(name, dob);
        library.addAuthor(author);
        System.out.println("Author added: " + author);
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
        
        Patron patron;
        if (type == 's') {
            patron = new Student(name, address, phoneNumber);
        } else {
            System.out.print("Enter employee ID: ");
            String employeeId = scanner.nextLine();
            patron = new Employee(name, address, phoneNumber, employeeId);
        }
        library.addPatron(patron);
        System.out.println("Patron added: " + patron);
    }

    private static void searchItemByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        LibraryItem item = library.searchItemByTitle(title);
        if (item != null) {
            System.out.println("Item found: " + item);
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void getLibraryItems() {
        System.out.println("All items in the library:");
        List<LibraryItem> items = library.getItems();
    
        if (items.isEmpty()) {
            System.out.println("No items found in the library.");
        } else {
            for (LibraryItem item : items) {
                System.out.println(item);
            }
        }
    }

    private static void showAllAuthors() {
        List<Author> authors = library.getAuthors();
        if (authors.isEmpty()) {
            System.out.println("No authors are currently available.");
        } else {
            System.out.println("All of the Authors in the library:");
            for (Author author : authors) {
                System.out.println(author);
                List<LibraryItem> writtenItems = author.getWrittenItems();
                if (writtenItems.isEmpty()) {
                    System.out.println("  No items written.");
                } else {
                    System.out.println("  Written items:");
                    for (LibraryItem item : writtenItems) {
                        System.out.println("    - " + item.getTitle());
                    }
                }
            }
        }
    }

    private static void showAllPatrons() {
        List<Patron> patrons = library.getPatrons();
        if (patrons.isEmpty()) {
            System.out.println("No patrons are currently available.");
        } else {
            System.out.println("All of the Patrons in the library:");
            for (Patron patron : patrons) {
                System.out.println(patron);
                List<LibraryItem> borrowedItems = patron.getBorrowedItems();
                if (borrowedItems.isEmpty()) {
                    System.out.println("  No borrowed items.");
                } else {
                    System.out.println("  Borrowed items:");
                    for (LibraryItem item : borrowedItems) {
                        System.out.println("    - " + item.getTitle());
                    }
                }
            }
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
