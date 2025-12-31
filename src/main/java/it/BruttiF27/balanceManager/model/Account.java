package it.BruttiF27.balanceManager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {

    // ----- Fields -----
    private final String accountName;
    private final List<Person> groupMembers = new ArrayList<>();
    private final List<Transaction> transactionList = new ArrayList<>();

    // ----- Name methods -----
    public Account (String name) { this.accountName = Objects.requireNonNull(name); }
    public String getAccountName () { return this.accountName; }

    // ----- Member methods -----
    public void addMember (Person person) {
        // Check every member. If there's a duplicate, throw.
        for (Person groupMember : groupMembers) {
            if (Objects.equals(groupMember.getFullName(), person.getFullName())) {
                throw new IllegalArgumentException("Person is already a member");
            }
        }

        groupMembers.add(Objects.requireNonNull(person));
    }
    public List<Person> getGroupMembers () { return List.copyOf(groupMembers); }

    // ----- Transaction methods -----
    public void addTransaction (Transaction transaction) {
        if (!groupMembers.contains(transaction.requestingUser())) {
            throw new IllegalArgumentException("Not a member of the account");
        }

        if (transaction.amount() == 0) {
            throw new IllegalArgumentException("Empty transaction");
        }

        if (transaction.transactionDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Future date");
        }

        // Check every member and do ++ to the transaction count of the requester.
        for (Person groupMember : groupMembers) {
            if (Objects.equals(groupMember, transaction.requestingUser())) {
                groupMember.increaseTransactionCount();
                break;
            }
        }

        transactionList.add(Objects.requireNonNull(transaction));
    }
    public List<Transaction> getTransactionList () { return Collections.unmodifiableList(transactionList); }

}