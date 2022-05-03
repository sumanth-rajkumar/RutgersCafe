package com.example.rucafe;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Coffee Class is a class that defines the properties that comes with an instance of a coffee
 * and the corresponding methods that go with it.
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class Coffee implements Customizable, MenuItem {



    private Set<String> addIns = new HashSet<>();
    private String size = TALL;
    private int quantity = 1;

    private static final double TALL_PRICE = 2.09;
    private static final double SHORT_PRICE = 1.69;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;

    private static final double ADDIN_PRICE = 0.30;

    public static final String TALL = "Tall";
    public static final String SHORT = "Short";
    public static final String GRANDE = "Grande";
    public static final String VENTI = "Venti";

    public static final String[] SIZES = new String[]{TALL, SHORT, GRANDE, VENTI};

    /**
     * Constructor that creates a Coffee object based off the given Coffee object
     * It assigns the proper values to the instance variables quantity, size, and addIns
     * @param copy - a Coffee object
     */
    public Coffee(Coffee copy)
    {
        this.quantity = copy.quantity;
        this.size = copy.size;
        addIns.addAll(copy.addIns);
    }

    /**
     * Empty constructor
     */
    public Coffee()
    {

    }

    /**
     * Sets the size type for a coffee object based on passed in string
     * @param size - a string representing size type
     */
    public void setSize(String size)
    {
        this.size = size;
    }

    /**
     * Sets the size type for a coffee object based on passed in string
     * @param quantity - an int representing quantity of coffee
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Checks if the passed in object is an instance of String or not and adds to addIns set
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if addIn has been added to set, false otherwise.
     */
    @Override
    public boolean add(Object obj)
    {
        if(obj instanceof String)
        {
            String addin = (String) obj;
            return addIns.add(addin);
        }
        return false;
    }

    /**
     * Checks if the passed in object is an instance of String or not and removes from addIns set
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if addIn has been removed from set, false otherwise.
     */
    @Override
    public boolean remove(Object obj)
    {

        if(obj instanceof String)
        {
            String addin = (String) obj;
            return addIns.remove(addin);
        }
        return false;
    }

    /**
     * Gives the unit price of the coffee depending on size and addin.
     * Overrides method from MenuItem interface
     * @return - double representing the unit price
     */
    @Override
    public double getUnitPrice()
    {

       return getSizePrice() + (addIns.size() * ADDIN_PRICE);
    }

    /**
     * Gives the price of the coffee depending on the size type.
     * Helper for getUnitPrice method
     * @return - double representing the size price
     */
    private double getSizePrice()
    {
        switch(size)
        {
        case TALL: return TALL_PRICE;
        case SHORT: return SHORT_PRICE;
        case GRANDE: return GRANDE_PRICE;
        case VENTI: return VENTI_PRICE;
        default: throw new RuntimeException("unknown size " + size);
        }
    }

    /**
     * Gives the number of coffees
     * Overrides method from MenuItem interface
     * @return - int representing the quantity
     */
    @Override
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Gives the size type of coffee with the quantity and addins if there are any
     * @return - String representing the size type, quantity, and addins if there are any
     */
    @Override
    public String toString()
    {
        if(addIns.isEmpty()) return "Coffee " + size + " (" + this.quantity + ") " + this.getItemCostText();

        return "Coffee " + size + " (" + this.quantity + ") " + this.getItemCostText() + " " + addIns;
    }

    /**
     * Checks if the passed in Coffee object is equal to this Coffee by checking
     * if each instance variable is equal
     * @param obj - object
     * @return true if instance var of passed in object is equal to the instance var of this
     * Coffee otherwise false.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Coffee coffee = (Coffee) obj;
        return quantity == coffee.quantity && Objects.equals(addIns, coffee.addIns) &&
            Objects.equals(size, coffee.size);
    }

    /**
     * Gives the hashcode value of coffee object
     * Acts as a helper to equals method
     * @return - int representing hash value
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(addIns, size, quantity);
    }
}
