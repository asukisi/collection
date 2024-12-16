import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class Book {
    private String title;
    private String genre;
    private Author author;

    public Book(String title, String genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book: " + title + " (Genre: " + genre + ", Author: " + author.getName() + ")";
    }
}

class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Author author = (Author) obj;
        return name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}

class Library {
    private List<Book> books;
    private Set<Author> authors;
    private HashMap<String, List<Book>> genreBooks;

    public Library() {
        this.books = new ArrayList<>();
        this.authors = new HashSet<>();
        this.genreBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        genreBooks.putIfAbsent(book.getGenre(), new ArrayList<>());
        genreBooks.get(book.getGenre()).add(book);
    }

    public void printBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void printAuthors() {
        System.out.println("Authors in the library:");
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    public void printBooksByGenre(String genre) {
        System.out.println("Books in genre " + genre + ":");
        List<Book> booksByGenre = genreBooks.get(genre);
        if (booksByGenre != null) {
            for (Book book : booksByGenre) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books in this genre.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Author author1 = new Author("George Orwell");
        Author author2 = new Author("J.K. Rowling");

        Book book1 = new Book("1984", "Dystopia", author1);
        Book book2 = new Book("Animal Farm", "Dystopia", author1);
        Book book3 = new Book("Harry Potter and the Philosopher's Stone", "Fantasy", author2);

        Library library = new Library();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.printBooks();
        library.printAuthors();
        library.printBooksByGenre("Dystopia");
        library.printBooksByGenre("Fantasy");
    }
}
