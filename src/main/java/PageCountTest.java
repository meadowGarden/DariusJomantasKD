
import lt.techin.library.Author;
import lt.techin.library.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PageCountTest {

    @Test
    void testBookForPageCount() {

        BookCatalogImpl catalog = new BookCatalogImpl();
        Book book = this.createSampleBook();
        catalog.addBook(book);
        List<Book> books = catalog.filterByPageCount(book.getPageCount());

        Assertions.assertFalse(books.isEmpty(), "list is empty");
        Assertions.assertTrue(books.contains(book), "sample book should be added");

    }

    private Book createSampleBook() {
        return new Book("Sample Title", Arrays.asList(new Author("Sample Author", 1970)), "123-4567890123", 2020, "Sample Publisher", 300, new BigDecimal("29.99"), true);
    }


}
