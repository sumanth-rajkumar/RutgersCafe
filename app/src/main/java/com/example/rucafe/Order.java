package com.example.rucafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Order Class is a class that takes in a list of MenuItems with their respective data
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class Order implements Customizable {


    private ArrayList<MenuItem> list;
    private int orderNumber;
    public static final float taxRate = 6.625f/100;
    public static final String NL = "\n";

    /**
     * Constructor that creates an Order object
     * It assigns the proper value to the instance variable list
     */
    public Order()
    {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor that creates a Order object based off the given Order object.
     * It assigns the proper values to the instance variables quantity, donutType, and flavor
     * @param other - passed in Order object
     */
    public Order(Order other)
    {
        this.list = new ArrayList<>(other.list);
        this.orderNumber = other.orderNumber;
    }



    /**
     * Sets an order's unique number based off passed in number
     * @param number - unique order number to be assigned
     */
    public void setOrderNumber(int number)
    {
        this.orderNumber = number;
    }

    /**
     * Sets an order's unique number based off passed in number
     * @return - int representing order's unique number
     */
    public int getOrderNumber()
    {
        return orderNumber;
    }


    /**
     * Checks if the passed in object is an instance of MenuItem or not and adds to list ArrayList
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if MenuItem has been added to ArrayList, false otherwise.
     */
    @Override
    public boolean add(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            MenuItem item = (MenuItem) obj;
            list.add(item);
            return true;
        }
        return false;
    }

    /**
     * Checks if the passed in object is an instance of MenuItem or not and removes from list ArrayList
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if MenuItem has been removed from ArrayList, false otherwise.
     */
    @Override
    public boolean remove(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            MenuItem item = (MenuItem) obj;
            return list.remove(item);
        }
        return false;
    }

    /**
     * Gives the ArrayList of MenuItems as a List
     * @return - List of MenuItems
     */
    public List<MenuItem> getOrderItems()
    {
        return list;
    }

    /**
     * Gives the sub-total cost of all the MenuItems in an order
     * @return - double representing the subtotal
     */
    public double getOrderSubTotal()
    {
       return list.stream().mapToDouble(item -> item.getItemCost()).sum();
    }

    /**
     * Gives the sales tax amount on the subtotal
     * @return - double representing the sales tax
     */
    public double getSalesTax()
    {
        return getOrderSubTotal() * taxRate;
    }

    /**
     * Gives the total cost of an Order after sales tax is applied
     * @return - double representing the total
     */
    public double getTotal()
    {
        return getOrderSubTotal() * (1 + taxRate);
    }


    /**
     * Gives the order#, sub-total cost, sales tax amount, total cost of Order, and MenuItems
     * @return - String representing the order#, sub-total cost, sales tax amount, total cost of Order, and MenuItems
     */
    @Override
    public String toString()
    {

        StringBuilder builder = new StringBuilder();

        builder.append("Order#").append(this.getOrderNumber()).append(NL);
        for(MenuItem item: list)
        {
            builder.append(item).append(NL);
        }
        builder.append("Order total: $ ").append(MainActivity.formatPrice(this.getTotal()));
        builder.append(" (subtotal: ").append(MainActivity.formatPrice(this.getOrderSubTotal()));
        builder.append(", tax: ").append(MainActivity.formatPrice(this.getSalesTax())).append(")");

        return builder.toString();
    }
}
