package it.BruttiF27.balanceManager.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    // ----- Fields -----
    private final String accountName;
    private final List<Person> groupMembers = new ArrayList<>();
    private final List<Transaction> transactionList = new ArrayList<>();

    // ----- Name methods -----
    public Account (String name) { this.accountName = name; } // io un controllino per null lo metterei, che non si sa mai (e lo aggiungerei ai test con eccezioni)
    public String getAccountName () { return accountName; }

    // ----- Member methods -----
    public void addMember (Person person) { groupMembers.add(person); } // io un controllino per null lo metterei, che non si sa mai (e lo aggiungerei ai test con eccezioni)
    public List<Person> getGroupMembers () { return List.copyOf(groupMembers); } // se vuoi una vista immutabile di una lista, è meglio usare Collections.unmodifiableList(list);

    // ----- Transaction methods -----
    public void addTransaction (Transaction transaction) { transactionList.add(transaction); } // io un controllino per null lo metterei, che non si sa mai (e lo aggiungerei ai test con eccezioni)
    public List<Transaction> getTransactionList () { return List.copyOf(transactionList); } // se vuoi una vista immutabile di una lista, è meglio usare Collections.unmodifiableList(list);

}
