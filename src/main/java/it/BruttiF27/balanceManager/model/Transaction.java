package it.BruttiF27.balanceManager.model;

import java.time.LocalDate;

/*
 *  The class defines how a transaction is supposed to look like. It's got:
 *  - Person
 *  - Date
 *  - Amount of money requested/given
 *  - Small description
 */

public class Transaction {

    // This class handles data that it's supposed to stay constant. Keyword final required.
    private final Person requestingUser;
    private final LocalDate transactionDate;
    private final double amount;
    private final String description;

    public Transaction (Person user, LocalDate transactionDate, double amount, String description) {
        this.requestingUser = user;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
    }

    public Person getRequestingUser () { return requestingUser; }
    public LocalDate getTransactionDate () { return transactionDate; }
    public double getAmount () { return amount; }
    public String getDescription () { return description; }

    @Override
    public String toString () {
        return "Requested by: " + requestingUser.getFullName() +
                "\nDate: " + transactionDate +
                "\nAmount: " + amount +
                "\nReason: " + description;
    }

}