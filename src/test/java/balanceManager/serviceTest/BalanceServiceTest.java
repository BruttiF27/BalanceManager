package balanceManager.serviceTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import it.BruttiF27.balanceManager.service.BalanceService;

public class BalanceServiceTest {

    @Test
    void calcCorrectMonthlyBalance () {
        // Instantiate objects for Account and BalanceService
        Account accountTest = new Account("PallePisello");
        BalanceService bsTest = new BalanceService();

        // Instantiate three Person objects
        // Valuta l'idea di instanziare gli oggetti Person in un @Setup o alla peggio in un @BeforeAll così da instanziare una volta sola tutto
        // ref: https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall
        Person p1 = new Person("Oney", "Mime");
        Person p2 = new Person("Tina", "Latt");
        Person p3 = new Person("Federico", "Brutti");

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(p1, LocalDate.now(), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(p2, LocalDate.now(), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(p3, LocalDate.now(), 100.00, "Test2"));

        // If the result equals 0, the calcs are correct TODO CHANGE YEARMONTH TEST
        assertEquals(0, bsTest.calcMonthlyBalance(accountTest, YearMonth.from(LocalDate.now())));
    }

    @Test
    void calcCorrectYearlyBalance () {
        // Instantiate objects for Account and BalanceService
        Account accountTest = new Account("PallePisello");
        BalanceService bsTest = new BalanceService();

        // Instantiate three Person objects
        Person p1 = new Person("Oney", "Mime");
        Person p2 = new Person("Tina", "Latt");
        Person p3 = new Person("Federico", "Brutti");

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(p1, LocalDate.now(), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(p2, LocalDate.now(), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(p3, LocalDate.now(), 100.00, "Test2"));

        // If the result equals 0, the calcs are correct TODO CHANGE YEAR TEST
        assertEquals(0, bsTest.calcYearlyBalance(accountTest, Year.from(LocalDate.now())));
    }

    @Test
    void calcCorrectAllTimeBalance () {
        // Instantiate objects for Account and BalanceService
        Account accountTest = new Account("PallePisello");
        BalanceService bsTest = new BalanceService();

        // Instantiate three Person objects
        Person p1 = new Person("Oney", "Mime");
        Person p2 = new Person("Tina", "Latt");
        Person p3 = new Person("Federico", "Brutti");

        // Add transactions to the list
        accountTest.addTransaction(new Transaction(p1, LocalDate.now(), -60.00, "Test1"));
        accountTest.addTransaction(new Transaction(p2, LocalDate.now(), -40.00, "Test2"));
        accountTest.addTransaction(new Transaction(p3, LocalDate.now(), 100.00, "Test2"));

        // If the result equals 0, the calcs are correct
        assertEquals(0, bsTest.calcAllTimeBalance(accountTest));
    }

}
