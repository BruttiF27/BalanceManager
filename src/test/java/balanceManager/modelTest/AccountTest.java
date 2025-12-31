package balanceManager.modelTest;

import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.BruttiF27.balanceManager.model.Account;

import java.time.LocalDate;

public class AccountTest {

    // Required fields
    private final Account accountTest = new Account("familyAccount");
    private final Person p1 = new Person("Fun", "Guy");;
    private Transaction transactionTest;

    @Test // If we give a null name to account, throw exception.
    public void checkNullAccount () { assertThrows(NullPointerException.class, () -> new Account(null)); }

    @Test // Same thing as before, but for members of an account
    public void checkNullMember () { assertThrows(NullPointerException.class, () -> accountTest.addMember(null)); }

    @Test // Same thing, but for transactions
    public void checkNullTransaction () { assertThrows(NullPointerException.class, () -> accountTest.addTransaction(null)); }

    @Test // Checks if the user is trying to add a duplicate member. If so, throws.
    public void checkDuplicateAccountMember () {
        accountTest.addMember(new Person("Fun", "Guy"));
        assertThrows(IllegalArgumentException.class, () -> accountTest.addMember(new Person("Fun", "Guy")));
    }

    @Test // Checks if the person requesting the transaction is a member of the account. If not, throws.
    public void checkTransactionRequestedByNotMember () {
        transactionTest = new Transaction(p1, LocalDate.now(), 100.00, "InvalidMember");
        assertThrows(IllegalArgumentException.class, () -> accountTest.addTransaction(transactionTest));
    }

    @Test // Checks if the transaction amount is 0. If yes, throws.
    public void checkNullTransactionValue () {
        transactionTest = new Transaction(p1, LocalDate.now(), 0, "InvalidAmount");
        assertThrows(IllegalArgumentException.class, () -> accountTest.addTransaction(transactionTest));
    }

    @Test // Checks if the user gave a future date. If yes, throws.
    public void checkFutureTransactionDate () {
        transactionTest = new Transaction(p1, LocalDate.now().plusDays(1), 0, "FutureDate");
        assertThrows(IllegalArgumentException.class, () -> accountTest.addTransaction(transactionTest));
    }

}