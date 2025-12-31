package balanceManager.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import it.BruttiF27.balanceManager.service.BalanceService;

public class BalanceServiceTest {

    // Required fields
    private Account accountTest;
    private BalanceService bsTest;
    private Person p1;
    private Person p2;
    private Person p3;

    @BeforeEach
    void testSetup () {
        // Instantiate objects for Account and BalanceService
        accountTest = new Account("FamilyAccount");
        bsTest = new BalanceService();

        p1 = new Person("Mime", "Oney");
        p2 = new Person("Lat", "Tina");
        p3 = new Person("John", "Magic");

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(p1,
                LocalDate.of(2025, 12, 25), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(p2,
                LocalDate.of(2024, 12, 10), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(p3,
                LocalDate.of(2001, 9, 27), 100.00, "Test3"));
    }

    @Test
    void calcCorrectMonthlyBalance () {
        // If the result equals -60.00, the calculations are correct
        assertEquals(-60.00, bsTest.calcMonthlyBalance(accountTest, YearMonth.of(2025, 12)));
    }

    @Test
    void calcCorrectPersonMonthlyBalance () {
        // If the result equals -60.00, the calculations are correct
        assertEquals(-60.00, bsTest.calcMonthlyBalance(accountTest, YearMonth.of(2025, 12), p1));
    }

    @Test
    void calcCorrectYearlyBalance () {
        // If the result equals 100.00, the calculations are correct
        assertEquals(100.00, bsTest.calcYearlyBalance(accountTest, Year.of(2001)));
    }

    @Test
    void calcCorrectPersonYearlyBalance () {
        // If the result equals -40.00, the calculations are correct
        assertEquals(-40.00, bsTest.calcYearlyBalance(accountTest, Year.of(2024), p2));
    }

    @Test
    void calcCorrectAllTimeBalance () {
        // If the result equals 0, the calculations are correct
        assertEquals(0, bsTest.calcAllTimeBalance(accountTest));
    }

    @Test
    void calcCorrectPersonAllTimeBalance () {
        // If the result equals 100.00, the calculations are correct
        assertEquals(100.00, bsTest.calcAllTimeBalance(accountTest, p3));
    }

}