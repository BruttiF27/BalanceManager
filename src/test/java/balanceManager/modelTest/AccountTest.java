package balanceManager.modelTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.BruttiF27.balanceManager.model.Account;

public class AccountTest {

    // Required fields
    private Account accountTest;

    @Test
    public void checkNullAccount () { assertThrows(NullPointerException.class, () -> new Account(null)); }

    @Test
    public void checkNullMember () { assertThrows(NullPointerException.class, () -> accountTest.addMember(null)); }

    @Test
    public void checkNullTransaction () { assertThrows(NullPointerException.class, () -> accountTest.addTransaction(null)); }

}