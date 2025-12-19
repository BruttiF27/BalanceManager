package it.BruttiF27.balanceManager.model;

/*
 *  This class defines the entities that are allowed to ask for money
 */

public class Person {

    private final String personName;
    private final String personLastName;
    private int transactionCount;

    public Person (String name, String lastName) {
        this.personName = name;
        this.personLastName = lastName;
        this.transactionCount = 0;
    }

    public String getFullName () { return personName + " " + personLastName; }
    public void increaseTransactionCount () { transactionCount++; }
    public int getTransactionCount () { return transactionCount; }

}