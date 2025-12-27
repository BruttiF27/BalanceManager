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

    // Required fields - non hai bisogno di static, il tuo contesto non lo richiede mai (ragionaci, anche i test sono oggetti)
    private static Account accountTest;
    private static BalanceService bsTest;

    @BeforeEach
    void testSetup () {
        // Instantiate objects for Account and BalanceService
        accountTest = new Account("FamilyAccount");
        bsTest = new BalanceService();

        // Add members to the account
        accountTest.addMember(new Person("Mime", "Oney"));
        accountTest.addMember(new Person("Lat", "Tina"));
        accountTest.addMember(new Person("John", "Magic"));

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(accountTest.getGroupMembers().getFirst(),
                LocalDate.now(), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(accountTest.getGroupMembers().get(1),
                LocalDate.now(), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(accountTest.getGroupMembers().get(2),
                LocalDate.now(), 100.00, "Test3"));
    }

    @Test
    void calcCorrectMonthlyBalance () {
        // If the result equals 0, the calcs are correct TODO CHANGE YEARMONTH TEST
        assertEquals(0, bsTest.calcMonthlyBalance(accountTest, YearMonth.from(LocalDate.now())));
    }

    @Test
    void calcCorrectYearlyBalance () {
        // If the result equals 0, the calcs are correct TODO CHANGE YEAR TEST
        assertEquals(0, bsTest.calcYearlyBalance(accountTest, Year.from(LocalDate.now())));
    }

    @Test
    void calcCorrectAllTimeBalance () {
        // If the result equals 0, the calcs are correct
        assertEquals(0, bsTest.calcAllTimeBalance(accountTest));
    }

}
