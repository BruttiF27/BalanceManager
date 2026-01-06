package it.BruttiF27.balanceManager.model;

import java.util.Objects;

/**
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

    public void increaseTransactionCount () { transactionCount++; }

    // ----- Methods for a correct use of Set<> -----
    @Override
    public boolean equals (Object obj) {
        // If it's the same object, return true
        if (this == obj) return true;
        // If the parameter isn't an instance of a Person class, return false
        if (!(obj instanceof Person pax)) return false;
        // Check if the full names are the same
        return personName.equals(pax.personName) && personLastName.equals(pax.personLastName);
    }

    @Override
    public int hashCode () {
        return Objects.hash(personName, personLastName);
    }

    // ----- Getter methods -----
    public String getPersonName () { return personName; }
    public String getPersonLastName () { return personLastName; }
    public String getFullName () { return personName + " " + personLastName; }
    public int getTransactionCount () { return transactionCount; }

}