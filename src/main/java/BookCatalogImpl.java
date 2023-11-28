import lt.techin.library.Book;
import lt.techin.library.BookCatalog;
import lt.techin.library.BookNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookCatalogImpl implements BookCatalog {

    private List<Book> books;


    public BookCatalogImpl() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {

        if (book == null || book.getIsbn() == null || book.getIsbn().isEmpty() || book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (books.isEmpty()) {
            books.add(book);
        }

        for (Book b : books) {
            if (!b.getIsbn().equals(book.getIsbn())) {
                books.add(book);
            }
        }
    }

    @Override
    public Book getBookByIsbn(String s) {
        for (Book b : books) {
            if (b.getIsbn().equals(s)) {
                return b;
            }
        }
        throw new BookNotFoundException("book was not found");
    }

    @Override
    public List<Book> searchBooksByAuthor(String s) {

//        for (Book b : books) {
//            if (b.getAuthors().contains(s)) {
//                return b.getAuthors();
//            }
//        }

        return null;
    }

    @Override
    public int getTotalNumberOfBooks() {
        return books.size();
    }

    @Override
    public boolean isBookInCatalog(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBookAvailable(String isbn) {
        for (Book b : books) {
            if (b.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Book findNewestBookByPublisher(String s) {
        Book newestBook = books.get(0);
        for (Book b : books) {
            if (b.getPublicationYear() > newestBook.getPublicationYear()) {
                newestBook = b;
            }
        }


        return null;
    }

    @Override
    public List<Book> getSortedBooks() {
        return books.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Book>> groupBooksByPublisher() {
        return books.stream()
                .collect(Collectors.groupingBy(b -> b.getPublisher()));
    }

    @Override
    public List<Book> filterBooks(Predicate<Book> predicate) {
        return books.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return books.stream()
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }
}
