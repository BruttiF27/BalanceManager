package balanceManager.modelTest;

import balanceManager.testUtils.SetupClass;
import it.BruttiF27.balanceManager.exceptions.TransactionException;
import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import it.BruttiF27.balanceManager.model.Account;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest extends SetupClass {

    @Override // Need an account and some members to perform tests
    public void configureSetup () {
        accountSetup();
        membersSetup();
    }

    @Test // If we give a null name to account, throw exception.
    public void checkNullAccount () { assertThrows(NullPointerException.class, () -> new Account(null)); }

    @Test // Same thing as before, but for members of an account
    public void checkNullMember () { assertThrows(NullPointerException.class, () -> accountTest.addMember(null)); }

    @Test // Same thing, but for transactions
    public void checkNullTransaction () { assertThrows(NullPointerException.class, () -> accountTest.addTransaction(null)); }

    @Test // Checks if the user is trying to add a duplicate member. If so, throws.
    public void checkDuplicateAccountMember () {
        assertThrows(IllegalArgumentException.class, () -> accountTest.addMember(new Person("Mime", "Oney")));
    }

    @Test // Checks if the person requesting the transaction is a member of the account. If not, throws.
    public void checkTransactionRequestedByNotMember () {
        Person personTest = new Person("Invalid", "Member");
        transaction1 = new Transaction(personTest, LocalDate.now(), 100.00, "InvalidMember");
        assertThrows(TransactionException.class, () -> accountTest.addTransaction(transaction1));
    }

    @Test // Checks if the transaction amount is 0. If yes, throws.
    public void checkNullTransactionValue () {
        transaction1 = new Transaction(accountMember1, LocalDate.now(), 0, "InvalidAmount");
        assertThrows(TransactionException.class, () -> accountTest.addTransaction(transaction1));
    }

    @Test // Checks if the user gave a future date. If yes, throws.
    public void checkFutureTransactionDate () {
        transaction1 = new Transaction(accountMember1, LocalDate.now().plusDays(1), 0, "FutureDate");
        assertThrows(TransactionException.class, () -> accountTest.addTransaction(transaction1));
    }

}