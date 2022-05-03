package com.example.rucafe;

/**
 * MenuItem is an interface that defines the common operations for all MenuItems
 * Implemented by Coffee and Donut
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public interface MenuItem {

    /**
     * Gives the unit price of a menu item
     * @return - double representing the unit price
     */
    double getUnitPrice();

    /**
     * Gives the formatted sub-total cost of a menu item
     * @return - String representing the formatted subtotal
     */
    default String getItemCostText()
    {
       return String.format("%1$.2f", getItemCost());
    }

    /**
     * Gives the sub-total cost of a menu item
     * @return - double representing the subtotal
     */
    default double getItemCost()
    {
        return  getUnitPrice() *  getQuantity();
    }

    /**
     * Gives the quantity of a menu item
     * @return - int representing the quantity
     */
    int getQuantity();
}
