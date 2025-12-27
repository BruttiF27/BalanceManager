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

    @Override // faccio io prima, anche in un record valgono le stesse regole di ereditarietà delle classi, i record sono classi dopotutto
    public String toString() {
        return "User: " + requestingUser + " - Date: " + transactionDate +
                "\nAmount: " + amount +
                "\nReason: " + description;
    }

}
