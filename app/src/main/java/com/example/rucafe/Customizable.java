package com.example.rucafe;

/**
 * Customizable is an interface that defines the common operations for Coffee item and all orders
 * Implemented by Coffee, Orders, and StoreOrders
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public interface Customizable {
    
    /**
     * Adds an object
     * @param obj - object
     * @return true if object has been added, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Removes an object
     * @param obj - object
     * @return true if object has been removed, false otherwise.
     */
    boolean remove(Object obj);
}
