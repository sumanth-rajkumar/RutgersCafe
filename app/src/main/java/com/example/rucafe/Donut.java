package com.example.rucafe;

import java.util.Objects;

/**
 * Donut Class is a class that defines the properties that comes with an instance of a donut
 * and the corresponding methods that go with it.
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class Donut implements MenuItem {


    private int quantity;
    private int image;
    private String flavor;

    private static final double CAKE_PRICE = 1.79;
    private static final double YEAST_PRICE = 1.59;
    private static final double HOLES_PRICE = 0.39;

    public static final String JELLY = "Jelly";
    public static final String GLAZED ="Glazed";
    public static final String CHOCOLATE_FROSTED = "Chocolate Frosted";
    public static final String STRAWBERRY_FROSTED = "Strawberry Frosted";
    public static final String SUGAR ="Sugar";
    public static final String LEMON_FILLED = "Lemon Filled";
    public static final String CINNAMON_SUGAR = "Cinnamon Sugar";
    public static final String OLD_FASHIONED ="Old Fashioned";
    public static final String BLUEBERRY = "Blueberry";
    public static final String GLAZED_HOLE = "Glazed Holes";
    public static final String JELLY_HOLE ="Jelly Holes";
    public static final String CINNAMON_SUGAR_HOLE = "Cinnamon Sugar Holes";


    /**
     * Constructor that creates a Donut object based off the given Donut object.
     * It assigns the proper values to the instance variables quantity, donutType, and flavor
     * @param other - a donut object
     */
    public Donut(Donut other)
    {
        this.flavor = other.flavor;
        this.quantity = other.quantity;
        this.image = other.image;
    }

    /**
     * Constructor that creates a Donut object based off the given string and int.
     * It assigns the proper values to the instance variables quantity, donutType, and flavor
     * @param flavor - passed in String representing flavor of donut
     * @param image - passed in Int representing the image index of donut
     */
    public Donut(String flavor, int image)
    {
        this.flavor = flavor;
        this.image = image;
    }


    /**
     * Gives the number of donuts
     * Overrides method from MenuItem interface
     * @return - int representing the number of donuts
     */
    @Override
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Sets a donut's quantity based on passed in int
     * @param q - int representing quantity
     */
    public void setQuantity(int q)
    {
        quantity = q;
    }

    /**
     * Gives the unit price of the donut depending on the flavor.
     * Overrides method from MenuItem interface
     * @return - double representing the unit price
     */
    @Override
    public double getUnitPrice()
    {
        switch (flavor)
        {
            case JELLY:
            case GLAZED:
            case CHOCOLATE_FROSTED:
            case STRAWBERRY_FROSTED:
            case SUGAR:
            case LEMON_FILLED:
                return YEAST_PRICE;
            case CINNAMON_SUGAR:
            case BLUEBERRY:
            case OLD_FASHIONED:
                return CAKE_PRICE;
            case GLAZED_HOLE:
            case JELLY_HOLE:
            case CINNAMON_SUGAR_HOLE:
                return HOLES_PRICE;
            default: throw new RuntimeException("unknown type " + flavor);
        }
    }

    /**
     * Gives the flavor of the donut
     * @return - String representing the flavor of donut
     */
    public String getDonutFlavor()
    {
        return flavor;
    }

    /**
     * Gives the image of the donut
     * @return - int representing the image index of donut
     */
    public int getImage() {
        return image;
    }

    /**
     * Gives the flavor of donut with the quantity
     * @return - String representing the flavor and quantity
     */
    @Override
    public String toString()
    {
        return flavor + " (" + this.quantity + ") " + this.getItemCostText();
    }

    /**
     * Checks if the passed in Donut object is equal to this Donut by checking
     * if each instance variable is equal
     * @param obj - object
     * @return true if instance var of passed in object is equal to the instance var of this
     * Donut otherwise false.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Donut donut = (Donut) obj;
        return quantity == donut.quantity  &&
            Objects.equals(flavor, donut.flavor);
    }

    /**
     * Gives the hashcode value of donut object
     * Acts as a helper to equals method
     * @return - int representing hash value
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(quantity, flavor);
    }
}
