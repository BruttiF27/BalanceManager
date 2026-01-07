package it.BruttiF27.balanceManager.model;

import it.BruttiF27.balanceManager.exceptions.TransactionException;
import java.time.LocalDate;
import java.util.*;

/**
 * Class used to store account data as well as defining the dynamics
 */

public class Account {

    /**
     * Stores the name of the account
     */
    private final String accountName;
    /**
     * Stores the members of the account that must not be duplicates
     */
    private final Set<Person> groupMembers = new LinkedHashSet<>();
    /**
     * Stores the transaction list of an account
     */
    private final List<Transaction> transactionList = new ArrayList<>();

    /**
     * The constructor requires a name as a means to make it unique.
     * @param name  The account name
     */
    public Account (String name) { this.accountName = Objects.requireNonNull(name); }

    /**
     * Adds a member to the account. It must not be null
     * @param person    The new member
     */
    public void addMember (Person person) {
        if (!groupMembers.add(Objects.requireNonNull(person))) {
            throw new IllegalArgumentException("ERR: " + person + " is already a member");
        }
    }

    /**
     * Adds a transaction to the list. Checks if it's null, if the value is 0 or if it's made in the future.
     * Also increments the value of the requested transactions for the member that requested it.
     * @param transaction   The requested transaction
     */
    public void addTransaction (Transaction transaction) {
        Objects.requireNonNull(transaction, "ERR: Transaction must not be null");
        Person requester = transaction.requestingUser();

        if (!groupMembers.contains(requester)) {
            // Defined custom exception, messages specify correct usage
            throw new TransactionException("ERR: Must be a member of account to make transactions");
        }

        if (transaction.amount() == 0) {
            throw new TransactionException("ERR: Transaction value must not be zero");
        }

        if (transaction.transactionDate().isAfter(LocalDate.now())) {
            throw new TransactionException("ERR: Transaction must not have future dates");
        }

        // Check every member and do ++ to the transaction count of the requester.
        for (Person groupMember : groupMembers) {
            // Removed utility class with simplified expression
            if (groupMember.equals(requester)) {
                groupMember.increaseTransactionCount();
                break; //ottima ottimizzazione, bravo
            }
        }

        //groupMembers.stream().filter(requester::equals).forEach(Person::increaseTransactionCount) 
        // non sono sicurissimo che scrivo da github su browser, ma questa roba dovrebbe essere equivalente al ciclo. Forse meno ottimizzata (?) ma non lo so
        // tra l'altro ti fa notare un banalissimo check che dovresti fare ma che non fai perché tanto il codice ti nasconde il NullPointerException

        /*
        Assumiamo che transaction.requestingUser() == null
        if(!groupMembers.contains(null) == TRUE
        throw new TransactionException

        ma non posso aggiungerlo al gruppo (requireNotNull in addMember)
        bug! perché sono utente idiota e mi hai detto che transaction non deve essere null, non i suoi contenuti

        con lo stream ti rendi conto di sto potenziale NPE perché fai requester::equals che di fatto si traduce in requester.equals(member) ma sappiamo per certo che solo member è not-null
        */

        //te lo lascio come commento di confronto, in realtà il tuo codice va più che bene ma volevo solo farti vedere il flusso da una prospettiva diversa, non per forza più corretta (anzi!)
        
        transactionList.add(transaction);
    }

    // ----- Getter methods -----
    public String getAccountName () { return this.accountName; }
    public Set<Person> getGroupMembers () { return Collections.unmodifiableSet(groupMembers); }
    public List<Transaction> getTransactionList () { return Collections.unmodifiableList(transactionList); }

}
