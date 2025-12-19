package it.BruttiF27.balanceManager.model;

import java.time.LocalDate;

//come per BalanceService, la javadoc! se proprio devi fare un commento almeno rendilo strutturalmente utile!
// non è sempre vera per ogni commento sta cosa, ma ponitela la domanda. Se stai descrivendo un entità o spiegando COME un metodo lavora, allora documentalo!
/**
 *  The class defines how a transaction is supposed to look like. It's got:
 *  - Person
 *  - Date
 *  - Amount of money requested/given
 *  - Small description
 */

public class Transaction { // questa classe poteva tranquillamente essere un record https://www.baeldung.com/java-record-keyword

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
