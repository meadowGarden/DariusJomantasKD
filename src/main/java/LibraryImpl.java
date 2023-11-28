import lt.techin.library.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryImpl implements Library {

    List<LibraryMember> members;


    BookCatalog bookCatalog;
    FineCalculationStrategy fineCalculationStrategy;

    public LibraryImpl (BookCatalog bookCatalog, FineCalculationStrategy fineCalculationStrategy) {
        this.bookCatalog = bookCatalog;
        this.fineCalculationStrategy = fineCalculationStrategy;
        this.members = new ArrayList<>();
    }


    @Override
    public void addBookToCatalog(Book book) {
        bookCatalog.addBook(book);
    }

    @Override
    public void registerMember(LibraryMember libraryMember) {
        if (libraryMember.getMemberId() == null ||
                libraryMember.getMemberId().isEmpty() ||
                libraryMember.getName() == null ||
                libraryMember.getName().isEmpty()) {
            throw new IllegalArgumentException();
        }
        members.add(libraryMember);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return  bookCatalog.getBookByIsbn(isbn);
    }

    @Override
    public void borrowBook(String s, String s1) {

    }

    @Override
    public void returnBook(String s, String s1) {

    }

    @Override
    public BigDecimal calculateFine(String s, LocalDate localDate) {
        return null;
    }

    @Override
    public List<LibraryMember> getAllMembers() {
        return members;
    }

    @Override
    public List<LibraryMember> getSortedByAge() {
        return members.stream()
                .sorted((m, n) -> n.getDateOfBirth().compareTo(m.getDateOfBirth()))
                .collect(Collectors.toList());
    }

    @Override
    public List<LibraryMember> getUnderAgeMembers(int i) {
//        return members.stream()
//                .filter(m -> m.getDateOfBirth());
        return null;
    }

    @Override
    public Map<Integer, List<LibraryMember>> getGroupedByYearOfBirth() {
        return members.stream()
                .collect(Collectors.groupingBy(b -> b.getDateOfBirth().getYear()));
    }

    @Override
    public void addBookToCatalog(OldBook oldBook) {

    }
}
