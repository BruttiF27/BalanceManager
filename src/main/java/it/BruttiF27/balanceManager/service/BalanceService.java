package it.BruttiF27.balanceManager.service;

import java.time.Year;
import java.time.YearMonth;
import java.util.function.Predicate;

import it.BruttiF27.balanceManager.model.Account;
import it.BruttiF27.balanceManager.model.Transaction;

/*
 *  Class used for the analysis of the balances:
 *  - monthly/yearly/allTime totals
 *  - per-person spending
 *  - graph datasets
 *  - statistics
 */

public class BalanceService {

    public double calcMonthlyBalance (Account acc, YearMonth month) {
        return sumTransactions(acc, t -> YearMonth.from(t.getTransactionDate()).equals(month));
    }

    public double calcYearlyBalance (Account acc, Year year) {
        return sumTransactions(acc, t -> Year.from(t.getTransactionDate()).equals(year));
    }

    public double calcAllTimeBalance (Account acc) {
        return sumTransactions(acc, t -> true);
    }

    private double sumTransactions (Account acc, Predicate<Transaction> filter) {
        // Stream through acc's transactionList
        return acc.getTransactionList().stream()
                // Filter out the elements where !filter
                .filter(filter)
                // Convert every amount variable in transactionList to a double type, then sum them
                .mapToDouble(Transaction::getAmount).sum();
    }

    // TODO Functions to calc per-person spending monthly, yearly and allTime

}