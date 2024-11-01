import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class LibraryMenu {
    private static Library library = new Library(); // The main library object
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    public static void main(String[] args) {
        initializeLibrary(); // Populate the library with initial data

        // Main menu loop for user interaction
        while (true) {
            displayMenu(); // Show available options to the user

            int choice; // User's menu choice
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; // Prompt for input again
            }

            // Handle the user's choice
            switch (choice) {
                case 1 -> addLibraryItem(); // Add a new library item
                case 2 -> addAuthor(); // Add a new author
                case 3 -> addPatron(); // Add a new patron
                case 4 -> borrowItem(); // Borrow an item
                case 5 -> returnItem(); // Return an item
                case 6 -> searchItemByTitle(); // Search for an item by title
                case 7 -> getLibraryItems(); // List all items in the library
                case 8 -> showAllAuthors(); // List all authors
                case 9 -> showAllPatrons(); // List all patrons
                case 10 -> {
                    System.out.println("Exiting...");
                    return; // Exit the program
                }
                default -> System.out.println("Invalid choice. Try again."); // Handle invalid choice
            }
        }
    }

    private static void initializeLibrary() {
        // Initialize library with predefined authors and items
        Author author1 = new Author("J.K. Rowling", "1965-07-31");
        Author author2 = new Author("George R.R. Martin", "1948-09-20");
        Author author3 = new Author("J.R.R. Tolkien", "1892-09-03");
        Author author4 = new Author("Dean Koontz", "1965-07-31");
        Author author5 = new Author("Stephen King", "1948-09-20");
        Author author6 = new Author("Suzanne Collins", "1892-09-03");
        Author author7 = new Author("Stieg Larson", "1892-09-03");
        Author author8 = new Author("Zanny Minton Beddoes", "1965-07-31");
        Author author9 = new Author("Susan Goldberg", "1948-09-20");
        Author author10 = new Author("Dr. Amy Wilson", "1892-09-03");
        library.addAuthor(author1);
        library.addAuthor(author2);
        library.addAuthor(author3);
        library.addAuthor(author4);
        library.addAuthor(author5);
        library.addAuthor(author6);
        library.addAuthor(author7);
        library.addAuthor(author8);
        library.addAuthor(author9);
        library.addAuthor(author10);

        // Add predefined library items
        library.addItem(new Book("1", "Harry Potter and the Sorcerer's Stone", author1, "978-0439708180", "Scholastic", 5, "Printed"));
        library.addItem(new Book("2", "A Game of Thrones", author2, "978-0553103540", "Bantam Books", 3, "Printed"));
        library.addItem(new Periodical("3", "The New England Journal of Medicine", author3, "0028-4793", "Massachusetts Medical Society", 10, "Printed"));
        library.addItem(new Book("4", "The Hobbit", author4, "978-0547928227", "Houghton Mifflin Harcourt", 4, "Printed"));
        library.addItem(new Book("5", "Harry Potter and the Chamber of Secrets", author5, "978-0439708180", "Scholastic", 5, "Printed"));
        library.addItem(new Book("6", "The Hunger Games", author6, "978-0547928227", "Houghton Mifflin Harcourt", 4, "Printed"));
        library.addItem(new Book("7", "The Good Guy", author7, "978-0439708180", "Scholastic", 5, "Printed"));
        library.addItem(new Periodical("8", "National Geographic", author8, "0013-0613", "Nat Geo Society", 2, "Printed"));
        library.addItem(new Periodical("9", "The Economist", author9, "0013-0613", "Economist Group", 3, "Printed"));
        library.addItem(new Periodical("10", "Journal of Medicine", author10, "0028-4793", "Medical Journal Co.", 4, "Printed"));


        // Add predefined patrons
        Patron patron1 = new Student("Alice Smith", "123 Maple St", "555-1234");
        Patron patron2 = new Employee("Bob Johnson", "456 Oak St", "555-5678", "E123");
        Patron patron3 = new Student("Charlie Brown", "789 Pine St", "555-8765");
        Patron patron4 = new Employee("Diana Prince", "321 Elm St", "555-4321", "E456");
        library.addPatron(patron1);
        library.addPatron(patron2);
        library.addPatron(patron3);
        library.addPatron(patron4);
    }

    private static void displayMenu() {
        // Display the main menu options
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
    }

    private static void addLibraryItem() {
        // Prompt for details to add a new library item
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter item title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();

        // Find or create the author
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
                return; // Exit if no valid author is found
            }
        }

        // Prompt for other details of the library item
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter number of copies available: ");
        int copiesAvailable = Integer.parseInt(scanner.nextLine());

        System.out.print("Is this a book or periodical (b/p)? ");
        char type = scanner.nextLine().charAt(0);

        // Create the appropriate library item based on type
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

        library.addItem(item); // Add the new item to the library
        System.out.println("Library item added: " + item);
    }

    private static Author findAuthorByName(String name) {
        // Search for an author by name in the library
        return library.getAuthors().stream()
                .filter(author -> author.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static void borrowItem() {
        // Process the borrowing of an item by a patron
        System.out.print("Enter patron's name: ");
        String patronName = scanner.nextLine();
        Patron patron = findPatronByName(patronName);

        if (patron == null) {
            System.out.println("Patron not found.");
            return; // Exit if patron not found
        }

        // Search for items based on user input
        System.out.print("Enter search term (title, ISBN, or author): ");
        String searchTerm = scanner.nextLine();
        List<LibraryItem> items = library.searchItemsByTerm(searchTerm);
        displayFoundItems(items); // Display found items

        System.out.print("Enter the title of the item you want to borrow: ");
        String itemInput = scanner.nextLine();
        LibraryItem itemToBorrow = library.searchItemByTitle(itemInput);

        if (itemToBorrow != null) {
            System.out.print("Enter the number of copies to borrow: ");
            int numberOfCopies = Integer.parseInt(scanner.nextLine());
            if (library.borrowItem(patron, itemToBorrow, numberOfCopies)) {
                System.out.println("Item borrowed successfully: " + itemToBorrow);
            } else {
                System.out.println("Borrowing failed.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void returnItem() {
        // Process the returning of an item by a patron
        System.out.print("Enter patron's name: ");
        String patronName = scanner.nextLine();
        Patron patron = findPatronByName(patronName);

        if (patron == null) {
            System.out.println("Patron not found.");
            return; // Exit if patron not found
        }

        System.out.print("Enter search term (title, ISBN, or author): ");
        String searchTerm = scanner.nextLine();
        List<LibraryItem> items = library.searchItemsByTerm(searchTerm);
        displayFoundItems(items); // Display found items

        System.out.print("Enter the title of the item you want to return: ");
        String itemInput = scanner.nextLine();
        LibraryItem itemToReturn = library.searchItemByTitle(itemInput);

        if (itemToReturn != null) {
            System.out.print("Enter the number of copies to return: ");
            int numberOfCopies = Integer.parseInt(scanner.nextLine());
            if (library.returnItem(patron, itemToReturn, numberOfCopies)) {
                System.out.println("Item returned successfully: " + itemToReturn);
            } else {
                System.out.println("Returning failed.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void displayFoundItems(List<LibraryItem> items) {
        // Display the list of found items based on a search
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
        // Prompt for details to add a new author
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();

        Author author = new Author(name, dob);
        library.addAuthor(author); // Add the new author to the library
        System.out.println("Author added: " + author);
    }

    private static void addPatron() {
        // Prompt for details to add a new patron
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
        library.addPatron(patron); // Add the new patron to the library
        System.out.println("Patron added: " + patron);
    }

    private static void searchItemByTitle() {
        // Search for an item by its title
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
        // Display all items currently in the library
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
        // Display all authors in the library
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
        // Display all patrons in the library
        List<Patron> patrons = library.getPatrons();
        if (patrons.isEmpty()) {
            System.out.println("No patrons are currently available.");
        } else {
            System.out.println("All of the Patrons in the library:");
            for (Patron patron : patrons) {
                System.out.println(patron);
                Map<LibraryItem, Integer> borrowedItems = patron.getBorrowedItems(); // Access borrowed items map
                if (borrowedItems.isEmpty()) {
                    System.out.println("  No borrowed items.");
                } else {
                    System.out.println("  Borrowed items:");
                    for (Map.Entry<LibraryItem, Integer> entry : borrowedItems.entrySet()) {
                        System.out.println("    - " + entry.getKey().getTitle() + " (Copies: " + entry.getValue() + ")");
                    }
                }
            }
        }
    }
    
    private static Patron findPatronByName(String name) {
        // Search for a patron by name in the library
        return library.getPatrons().stream()
                .filter(patron -> patron.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
