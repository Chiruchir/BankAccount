import java.util.Arrays;
class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}
class Library {
    private Book[] books;
    private int maxBooks;

    public Library(int maxBooks) {
        this.maxBooks = maxBooks;
        this.books = new Book[maxBooks];
    }
    public void addBook(Book book) {
        for (int i = 0; i < maxBooks; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }
    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book != null && book.isAvailable()) {
                System.out.println(book);
            }
        }
    }
    public Book borrowBook(String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equals(title) && book.isAvailable()) {
                book.setAvailable(false);
                return book;
            }
        }
        return null;
    }
    public void returnBook(Book book) {
        for (Book libraryBook : books) {
            if (libraryBook != null && libraryBook.equals(book)) {
                libraryBook.setAvailable(true);
                break;
            }
        }
    }
}
class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Book book1 = new Book("The Catcher in the Rye", "J.D. Salinger");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee");
        Book book3 = new Book("1984", "George Orwell");
        Library library = new Library(5);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.displayAvailableBooks();
        Member member = new Member("John Doe");
        Book borrowedBook = library.borrowBook("The Catcher in the Rye");
        if (borrowedBook != null) {
            System.out.println(member.getName() + " borrowed: " + borrowedBook);
        } else {
            System.out.println("Book not available or not found.");
        }
        library.displayAvailableBooks();
        library.returnBook(borrowedBook);
        System.out.println(member.getName() + " returned: " + borrowedBook);
        library.displayAvailableBooks();
    }
}
