package it.BruttiF27.balanceManager.service;

import java.time.Year;
import java.time.YearMonth;
import java.util.function.Predicate;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Transaction;

/**
 *  Class used for the analysis of the balances:
 *  - monthly/yearly/allTime totals
 *  - per-person spending
 *  - graph datasets
 *  - statistics
 */

public class BalanceService {

    /**
     * Calculates the monthly balance of the given account
     * @param acc   The account
     * @param month The month
     */
    public double calcMonthlyBalance (Account acc, YearMonth month) {
        return sumTransactions(acc, t -> YearMonth.from(t.transactionDate()).equals(month));
    }

    /**
     * Calculates the yearly balance of the given account
     * @param acc   The account
     * @param year  The year
     */
    public double calcYearlyBalance (Account acc, Year year) {
        return sumTransactions(acc, t -> Year.from(t.transactionDate()).equals(year));
    }

    /**
     * Calculates the total balance of the given account
     * @param acc   The account
     */
    public double calcAllTimeBalance (Account acc) {
        return sumTransactions(acc, t -> true);
    }

    /**
     * Calculates the balance of a determined set of transactions in the given account
     * @param acc       The account
     * @param filter    The means to discern the requested type of transaction
     */
    private double sumTransactions (Account acc, Predicate<Transaction> filter) {
        // Stream through acc's transactionList
        return acc.getTransactionList().stream()
                // Filter out the elements where !filter
                .filter(filter)
                // Convert every amount variable in transactionList to a double type, then sum them
                .mapToDouble(Transaction::amount).sum();
    }

    // TODO Functions to calc per-person (not per-account) spending monthly, yearly and allTime

}