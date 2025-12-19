package it.BruttiF27.balanceManager.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    // ----- Fields -----
    private final String accountName;
    private final List<Person> groupMembers = new ArrayList<>();
    private final List<Transaction> transactionList = new ArrayList<>();

    // ----- Name methods -----
    public Account (String name) { this.accountName = name; }
    public String getAccountName () { return accountName; }

    // ----- Member methods -----
    public void addMember (Person person) { groupMembers.add(person); }
    public List<Person> getGroupMembers () { return List.copyOf(groupMembers); }

    // ----- Transaction methods -----
    public void addTransaction (Transaction transaction) { transactionList.add(transaction); }
    public List<Transaction> getTransactionList () { return List.copyOf(transactionList); }

}