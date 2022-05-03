package com.example.rucafe;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingDonuts, orderingCoffee, orderingBasket GUI
 * @author Sumanth Rajkumar, Shantanu Jain
*/
public class StoreOrdersActivity extends AppCompatActivity {
    private static StoreOrders store = new StoreOrders();


    private ListView storeOrders;
    private ArrayAdapter<Order> storeOrdersAdapter;

    /**
     * This method sets the UI controls of the GUI immediately after it's opened
     * @param savedInstanceState - Bundle representing the saved instance of creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_store);
        storeOrders = findViewById(R.id.storeOrders);

        storeOrdersAdapter = new ArrayAdapter<Order>(this, R.layout.store_orders_text_view, new ArrayList(store.getOrders()));
        storeOrders.setAdapter(storeOrdersAdapter);
        storeOrdersListViewListener(storeOrders);
    }

    /**
     * It will add the order items with its specific order number to ListView in store GUI based on user input
     * @param order - order item to be placed in store ListView
     */
    public static void placeOrder(Order order)
    {
        store.add(new Order(order));
    }

    /**
     * This method is a helper that corresponds to the user selection click of an order
     * in the ListView
     * It will remove order items from ListView
     * @param order - Order object being removed
     */
    public void cancelOrder(Order order)
    {
        if(order == null) return;
        store.removeOrder(order.getOrderNumber());
        storeOrdersAdapter.remove(order);
    }

    /**
     * This method is a listener that corresponds to the user selection click of an order
     * in the ListView and remove order items from ListView based on user button click
     * in alert dialogs
     * @param storeOrders - ListView being listened
     */
    private void storeOrdersListViewListener(ListView storeOrders)
    {
        storeOrders.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Cancel Order");
                alert.setMessage("Confirming cancelling order");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        cancelOrder(storeOrdersAdapter.getItem(i));
                        Toast.makeText(view.getContext(), " Order cancelled.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(view.getContext(),
                                " Order not cancelled.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

}


