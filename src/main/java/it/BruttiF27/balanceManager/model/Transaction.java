package it.BruttiF27.balanceManager.model;

import java.time.LocalDate;

/**
 *  The record defines how a transaction is supposed to look like. Fields used:
 *  - Person requestingUser         The account user that requested the transaction
 *  - LocalDate transactionDate     The date when the transaction was requested
 *  - double amount                 The amount of money requested/given
 *  - String description            The reason for the transaction
 */

public record Transaction (
        Person requestingUser,
        LocalDate transactionDate,
        double amount,
        String description) {

    @Override
    public String toString () {
        return "User: " + requestingUser + " - Date: " + transactionDate +
                "\nAmount: " + amount +
                "\nReason: " + description;
    }

}