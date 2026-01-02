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
        // sai come si chiama un List<> che non ammette duplicati by-default? Set<>
        groupMembers.add(Objects.requireNonNull(person));
    }
    public List<Person> getGroupMembers () { return List.copyOf(groupMembers); } // vuoi rendere la lista modificabile anche se non produce side effects? ha senso?

    // ----- Transaction methods -----
    public void addTransaction (Transaction transaction) {
        // visto che lo riusi, perché non metti in una variabile requestingUser() ?
        if (!groupMembers.contains(transaction.requestingUser())) {
            throw new IllegalArgumentException("Not a member of the account"); // pro-tip: definisci le tue personali eccezioni in un package apposito. A quelle semmai fai estendere il giusto tipo di eccezione. Il messaggio dovrebbe indicare specifiche aggiuntive, non specificare il tipo concreto di eccezione
        }

        if (transaction.amount() == 0) {
            throw new IllegalArgumentException("Empty transaction"); // fai bene attenzione: qui basta avere un TransactionException con messaggi diversi in base al contesto, anche se puoi esagerare e prevedere specifiche più intensive
        }

        if (transaction.transactionDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Future date");
        }

        // Check every member and do ++ to the transaction count of the requester.
        for (Person groupMember : groupMembers) {
            if (groupMember.equals(transaction.requestingUser())) { // belle le classi di utility, ma davvero qua groupMember è non-null per definizione, quindi puoi tranquillamente usare equals
                groupMember.increaseTransactionCount();
                break;
            }
        }

        transactionList.add(Objects.requireNonNull(transaction)); // che succede a riga 36 se transaction è null? mi sa che è tardi per questo controllo
    }
    public List<Transaction> getTransactionList () { return Collections.unmodifiableList(transactionList); } //ottimo, ricorda che unmodifiableList rende non modificabile il riferimento che viene tornato, non fa side-effect dunque la collezione rimane modificabile concretamente

}
