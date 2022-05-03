package com.example.rucafe;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Order Class is a class that takes in a Map of Orders with their respective data
 * @author Sumanth Rajkumar, Shantanu Jain
*/
public class StoreOrders implements Customizable {

    private Map<Integer,Order> orders;
    public static final String NL = "\n";
    private int lastOrderNumber = 0;

    /**
     * Gives a collection of all orders placed
     * @return - Collection of all orders
     */
    public Collection<Order> getOrders()
    {
        return orders.values();
    }

    /**
     * Gives the order number and order information
     * @return - String representing the order and order info
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, Order> entry : orders.entrySet())
        {
            builder.append("OrderNumber=").append(entry.getKey()).append(NL);
            builder.append("Order=").append(entry.getValue()).append(NL);
        }
        return builder.toString();
    }

    /**
     * Constructor that creates a StoreOrders object
     * It assigns the proper value to the instance variable orders
     */
    public StoreOrders()
    {
        this.orders = new HashMap<>();
    }

    /**
     * Checks if the passed in object is an instance of Order or not and adds to orders Map
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if Order has been added to Map, false otherwise.
     */
    @Override
    public boolean add(Object obj)
    {
        if(obj instanceof Order)
        {
            Order order = (Order) obj;
            lastOrderNumber++;
            orders.put(lastOrderNumber,order);
            order.setOrderNumber(lastOrderNumber);
            return true;
        }
        return false;
    }


    /**
     * Removes an Order object in the map based off passed in orderNumber acting as index value
     * @param orderNumber - int representing the index value
     * @return true if Order object in the Map has been removed, false otherwise
     */
    public boolean removeOrder(int orderNumber)
    {
       return orders.remove(orderNumber)!=null;
    }

    /**
     * Checks if the passed in object is an instance of Order or not and removes from orders Map
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if Order has been removed from Map, false otherwise.
     */
    @Override
    public boolean remove(Object obj)
    {
        if(obj instanceof Order)
        {
            Order order = (Order) obj;
            return orders.values().remove(order);
        }
        return false;
    }

}

