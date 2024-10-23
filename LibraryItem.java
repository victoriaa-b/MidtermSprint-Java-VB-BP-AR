// check to see if needs packages later 

public class LibraryItem {
    private int id;
    private String title;
    private String author;
    private String ISBN;
    private String publisher;
    private int numOfCopies;
    private String itemType; // its either a book or periodicals 
    private String form;   // books can only be "Printed", "Electronic", "Audio" 
    // periodicals only have  "Printed" or "Electronic"

    // constructor 
    // books or peroidcal needs to be allowed 
    public LibraryItem(int id, String title, String author, String ISBN, String publisher, int numOfCopies, String itemType, String format) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.numOfCopies = numOfCopies;
        this.itemType = itemType;
        this.form = format;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumOfCopies() {
        return numOfCopies;
    }

    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    // display the data 
@Override
public String toString() {
    return "LibraryItem { " +
            "ID = " + id + 
            ", Title = '" + title + "'" +
            ", Author = '" + author + "'" +
            ", ISBN = '" + ISBN + "'" +
            ", Publisher = '" + publisher + "'" +
            ", Number of Copies = " + numOfCopies +
            ", Item Type = '" + itemType + "'" +
            ", Form = '" + form + "' " +
            "}";
}

}
