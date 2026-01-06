package balanceManager.serviceTest;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import it.BruttiF27.balanceManager.service.BalanceService;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceServiceTest {

    // Required fields
    private Account accountTest;
    private BalanceService bsTest;
    private Person accountMember1;
    private Person accountMember2;
    private Person accountMember3;

    @BeforeEach
    void testSetup () {
        // Instantiate objects for Account and BalanceService
        accountTest = new Account("FamilyAccount");
        bsTest = new BalanceService();

        accountMember1 = new Person("Mime", "Oney");
        accountMember2 = new Person("Lat", "Tina");
        accountMember3 = new Person("John", "Magic");
        accountTest.addMember(accountMember1);
        accountTest.addMember(accountMember2);
        accountTest.addMember(accountMember3);

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(accountMember1,
                LocalDate.of(2025, 12, 25), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(accountMember2,
                LocalDate.of(2024, 12, 10), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(accountMember3,
                LocalDate.of(2001, 9, 27), 100.00, "Test3"));
    }

    @Test // If the result equals -60.00, the calculations are correct
    void calcCorrectMonthlyBalance () {
        assertEquals(-60.00, bsTest.calcMonthlyBalance(accountTest, YearMonth.of(2025, 12)));
    }

    @Test // If the result equals -60.00, the calculations are correct
    void calcCorrectPersonMonthlyBalance () {
        assertEquals(-60.00, bsTest.calcMonthlyBalance(accountTest, YearMonth.of(2025, 12), accountMember1));
    }

    @Test // If the result equals 100.00, the calculations are correct
    void calcCorrectYearlyBalance () {
        assertEquals(100.00, bsTest.calcYearlyBalance(accountTest, Year.of(2001)));
    }

    @Test // If the result equals -40.00, the calculations are correct
    void calcCorrectPersonYearlyBalance () {
        assertEquals(-40.00, bsTest.calcYearlyBalance(accountTest, Year.of(2024), accountMember2));
    }

    @Test // If the result equals 0, the calculations are correct
    void calcCorrectAllTimeBalance () {
        assertEquals(0, bsTest.calcAllTimeBalance(accountTest));
    }

    @Test // If the result equals 100.00, the calculations are correct
    void calcCorrectPersonAllTimeBalance () {
        assertEquals(100.00, bsTest.calcAllTimeBalance(accountTest, accountMember3));
    }

}