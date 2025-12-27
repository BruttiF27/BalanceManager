package balanceManager.serviceTest;

import it.BruttiF27.balanceManager.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Person;

import java.time.LocalDate;

public class AccountTest {

    // Required fields
    private static Account accountTest;

    @BeforeEach
    void testSetup () {
        accountTest = new Account("familyAccount");
        accountTest.addMember(new Person("Mime", "Oney"));
        accountTest.addTransaction(new Transaction(accountTest.getGroupMembers().getFirst(), LocalDate.now(), -60.00, "Test1"));
    // cerca di rendere autonoma ogni operazione in caso di setup, così da riciclare variabili e non dipendere troppo da metodi che dovrebbero piuttosto essere oggetto di test e non di setup, tldr: preferisci i costruttori ai getter
    }

    @Test
    public void checkNullAccount () {
        assertThrows(NullPointerException.class, () ->
            new Account(null)
        );
    }

    @Test
    public void checkNullMember () {
        assertThrows(NullPointerException.class, () ->
            accountTest.addMember(null)
        );
    }

    @Test
    public void checkNullTransaction () {
        assertThrows(NullPointerException.class, () ->
            accountTest.addTransaction(null)
        );
    }

}
