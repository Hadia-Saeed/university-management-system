import java.io.Serializable;

public class Book implements Serializable{
    private String bookID, title, author;
    private boolean isAvailable;

    public Book(String bookID, String title, String author) {
        setBookID(bookID);
        setTitle(title);
        setAuthor(author);
        setIsAvailable(true); // default to available when created
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public String toString() {
        return "BookID: " + bookID + "\nTitle: " + title + "\nAuthor: " + author + "\nAvailable: " + isAvailable;
    }

}//end of Book class
