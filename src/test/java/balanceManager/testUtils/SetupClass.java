package balanceManager.testUtils;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import it.BruttiF27.balanceManager.service.BalanceService;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public abstract class SetupClass {

    // Required fields
    protected Account accountTest;
    protected BalanceService bsTest;
    protected Person accountMember1;
    protected Person accountMember2;
    protected Person accountMember3;
    protected Transaction transaction1;
    protected Transaction transaction2;
    protected Transaction transaction3;

    // Required to make it run before each test
    @BeforeEach
    final void setUp () {
        configureSetup();
    }

    // Must be overridden: choose the methods for test initialization
    protected abstract void configureSetup ();

    // Account setup
    protected void accountSetup () {
        this.accountTest = new Account("FamilyAccount");
    }

    // Adds a set of simple member examples
    protected void membersSetup () {
        this.accountMember1 = new Person("Mime", "Oney");
        this.accountMember2 = new Person("Lat", "Tina");
        this.accountMember3 = new Person("John", "Magic");

        accountTest.addMember(accountMember1);
        accountTest.addMember(accountMember2);
        accountTest.addMember(accountMember3);
    }

    // Adds a list of simple transaction examples
    protected void transactionSetup () {
        this.transaction1 = new Transaction(accountMember1,
                LocalDate.of(2025, 12, 25), -60.00, "Test1");
        this.transaction2 = new Transaction(accountMember2,
                LocalDate.of(2024, 12, 10), -40.00, "Test2");
        this.transaction3 = new Transaction(accountMember3,
                LocalDate.of(2001, 9, 27), 100.00, "Test3");

        accountTest.addTransaction(transaction1);
        accountTest.addTransaction(transaction2);
        accountTest.addTransaction(transaction3);
    }

    // BalanceService setup
    protected void balanceServiceSetup () {
        this.bsTest = new BalanceService();
    }

}