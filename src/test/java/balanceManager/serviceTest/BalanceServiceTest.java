package balanceManager.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import it.BruttiF27.balanceManager.service.BalanceService;

import static org.junit.jupiter.api.Assertions.assertEquals;
//non so se hai impostazioni strane sull'ide, ma di solito gli import statici si segnano alla fine della lista di import

public class BalanceServiceTest {

    // Required fields
    private Account accountTest;
    private BalanceService bsTest;
    private Person p1; // rinomina ste persone con il loro nome, cerca di evitare nomi poco descrittivi.
    private Person p2; // Java è un linguaggio molto verboso, siilo anche tu nello scrivere
    private Person p3; // tanto i nomi di variabile sono gratuiti e vengono sostituiti tutti dal compiler in maniera già ottimizzata

    @BeforeEach
    void testSetup () {
        // Instantiate objects for Account and BalanceService
        accountTest = new Account("FamilyAccount");
        bsTest = new BalanceService();

        p1 = new Person("Mime", "Oney");
        p2 = new Person("Lat", "Tina");
        p3 = new Person("John", "Magic");
        accountTest.addMember(p1);
        accountTest.addMember(p2);
        accountTest.addMember(p3);

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(p1,
                LocalDate.of(2025, 12, 25), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(p2,
                LocalDate.of(2024, 12, 10), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(p3,
                LocalDate.of(2001, 9, 27), 100.00, "Test3"));
    }

    @Test // If the result equals -60.00, the calculations are correct
    void calcCorrectMonthlyBalance () {
        assertEquals(-60.00, bsTest.calcMonthlyBalance(accountTest, YearMonth.of(2025, 12)));
    }

    @Test // If the result equals -60.00, the calculations are correct
    void calcCorrectPersonMonthlyBalance () {
        assertEquals(-60.00, bsTest.calcMonthlyBalance(accountTest, YearMonth.of(2025, 12), p1));
    }

    @Test // If the result equals 100.00, the calculations are correct
    void calcCorrectYearlyBalance () {
        assertEquals(100.00, bsTest.calcYearlyBalance(accountTest, Year.of(2001)));
    }

    @Test // If the result equals -40.00, the calculations are correct
    void calcCorrectPersonYearlyBalance () {
        assertEquals(-40.00, bsTest.calcYearlyBalance(accountTest, Year.of(2024), p2));
    }

    @Test // If the result equals 0, the calculations are correct
    void calcCorrectAllTimeBalance () {
        assertEquals(0, bsTest.calcAllTimeBalance(accountTest));
    }

    @Test // If the result equals 100.00, the calculations are correct
    void calcCorrectPersonAllTimeBalance () {
        assertEquals(100.00, bsTest.calcAllTimeBalance(accountTest, p3));
    }

}
