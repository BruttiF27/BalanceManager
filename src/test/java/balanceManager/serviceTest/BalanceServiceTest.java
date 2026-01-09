package balanceManager.serviceTest;

import balanceManager.testUtils.SetupClass;
import java.time.YearMonth;
import java.time.Year;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceServiceTest extends SetupClass {

    @Override
    public void configureSetup() {
        accountSetup();
        balanceServiceSetup();
        membersSetup();
        transactionSetup();
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