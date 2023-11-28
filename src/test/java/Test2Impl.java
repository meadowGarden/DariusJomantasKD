import lt.techin.library.BookCatalog;
import lt.techin.library.FineCalculationStrategy;
import lt.techin.library.Library;
import lt.techin.library.test.SecondAbtractLibraryTest;

import java.math.BigDecimal;

public class Test2Impl extends SecondAbtractLibraryTest {


    @Override
    public Library getLibrary(BookCatalog bookCatalog, FineCalculationStrategy fineCalculationStrategy) {
        return null;
    }

    @Override
    protected FineCalculationStrategy getFineCalculatorStrategy(BigDecimal bigDecimal) {
        return null;
    }

    @Override
    protected BookCatalog getBookCatalog() {
        return null;
    }
}
