package it.BruttiF27.balanceManager.model;

import java.time.LocalDate;

/**
 *  The record defines how a transaction is supposed to look like. It's got:
 *  - Person
 *  - Date
 *  - Amount of money requested/given
 *  - Small description
 */

public record Transaction (
        Person requestingUser,
        LocalDate transactionDate,
        double amount,
        String description) {

    public String transactionFormat () {
        return "User: " + requestingUser + " - Date: " + transactionDate +
                "\nAmount: " + amount +
                "\nReason: " + description;
    }

}