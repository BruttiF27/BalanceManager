package balanceManager.modelTest;

import it.BruttiF27.balanceManager.model.Person;
import it.BruttiF27.balanceManager.model.Transaction;
import org.junit.jupiter.api.Test;
import it.BruttiF27.balanceManager.model.Account;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows; // anche qui, sempre rispettando la convenzione

public class AccountTest {

    // Required fields
    private final Account accountTest = new Account("familyAccount"); // inoltre, sempre per convenzione, solitamente una variabile final che viene instanziata a compile time, non ha senso di non essere anche static, dunque costante
    private final Person p1 = new Person("Fun", "Guy");; // anche qui, inventati un nome significativo, in questo contesto può essere persino "person"
    private Transaction transactionTest;

    // ma manca il setup della transaction o mi sono perso io?
    
    @Test // If we give a null name to account, throw exception.
    public void checkNullAccount () { assertThrows(NullPointerException.class, () -> new Account(null)); }

    @Test // Same thing as before, but for members of an account
    public void checkNullMember () { assertThrows(NullPointerException.class, () -> accountTest.addMember(null)); }

    @Test // Same thing, but for transactions
    public void checkNullTransaction () { assertThrows(NullPointerException.class, () -> accountTest.addTransaction(null)); }

    @Test // Checks if the user is trying to add a duplicate member. If so, throws.
    public void checkDuplicateAccountMember () {
        accountTest.addMember(new Person("Fun", "Guy")); // perché non riusi lo stesso riferimento? o alla peggio, crei una nuova variabile locale con gli stessi valori?
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
