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

    List<BorrowInfo> borrowLog;
    BookCatalog bookCatalog;
    FineCalculationStrategy fineCalculationStrategy;

    public LibraryImpl (BookCatalog bookCatalog, FineCalculationStrategy fineCalculationStrategy) {
        this.bookCatalog = bookCatalog;
        this.fineCalculationStrategy = fineCalculationStrategy;
        this.members = new ArrayList<>();
        this.borrowLog = new ArrayList<>();
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
                libraryMember.getName().isEmpty() ||
                LocalDate.now().isBefore(libraryMember.getDateOfBirth())
        ) {
            throw new IllegalArgumentException();
        }
        members.add(libraryMember);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new BookNotFoundException("book not found");
        }
        return  bookCatalog.getBookByIsbn(isbn);
    }

    @Override
    public void borrowBook(String memberId, String isbn) {

        if (memberId == null || memberId.isEmpty() || !bookCatalog.isBookAvailable(isbn)) {
            throw new IllegalArgumentException();
        }

        for (LibraryMember m : members) {
            m.borrowBook(getBookByIsbn(isbn));
        }
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
    public List<LibraryMember> getUnderAgeMembers(int ageLimit) {
        return members.stream()
                .filter(m -> (LocalDate.now().getYear() - m.getDateOfBirth().getYear()) <= 18)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<LibraryMember>> getGroupedByYearOfBirth() {
        return members.stream()
                .collect(Collectors.groupingBy(b -> b.getDateOfBirth().getYear()));
    }

    @Override
    public void addBookToCatalog(OldBook oldBook) {
//        bookCatalog.addBook(new BookAdapter(oldBook));

    }
}
