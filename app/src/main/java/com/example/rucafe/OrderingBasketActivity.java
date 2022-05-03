package com.example.rucafe;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collection;
import java.util.List;

/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingDonuts and orderingCoffee GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class OrderingBasketActivity extends AppCompatActivity {

    private static Order order = new Order();
    private TextView orderSubtotal, salesTax, total;
    private ListView orderListView;
    private ArrayAdapter<MenuItem> orderItemsAdapter;
    private Button placeOrder;

    /**
     * Links all button and selections of GUI to do the respective actions when button clicked
     * or user selects
     * @param savedInstanceState - Bundle representing the saved instance of creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_ordering);
        initViews();

        orderItemsAdapter = new ArrayAdapter<>(this, R.layout.store_orders_text_view, order.getOrderItems());
        orderListView.setAdapter(orderItemsAdapter);

        placeOrderButtonListener(placeOrder);
        orderListViewListener(orderListView);
        updateOrderCosts();
    }

    /**
     * This method corresponds to the Place order button of the GUI
     * It will add the order to ListView in StoreOrder GUI based on user input
     * @param placeOrder - Button that is being listened
     */
    private void placeOrderButtonListener(Button placeOrder)
    {
        placeOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(orderItemsAdapter.isEmpty())
                {
                    Toast.makeText(view.getContext(), " No orders to be placed.", Toast.LENGTH_LONG).show();
                    return;
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Place order");
                alert.setMessage("Confirming placing order");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Order existingOrder = order;
                        existingOrder.getOrderItems();
                        StoreOrdersActivity.placeOrder(existingOrder);
                        resetOrder();
                        Toast.makeText(view.getContext(), " Order placed.", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(view.getContext(),
                                " Order not placed.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * This method is a listener that corresponds to the user selection click of an order
     * in the ListView and remove order items from ListView based on user button click
     * in alert dialogs
     * @param orderListView - ListView that is being listened
     */
    private void orderListViewListener(ListView orderListView)
    {
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Cancel Item");
                alert.setMessage("Confirming cancelling item");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        cancelItem(orderItemsAdapter.getItem(i));
                        updateOrderCosts();
                        Toast.makeText(view.getContext(), " Item cancelled.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(view.getContext(),
                                " Item not cancelled.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     * Adds to items ListView based on passed in Collection of MenuItems
     * @param orderItems - Collection of MenuItems
     */
    public static void addToOrder(Collection<? extends MenuItem> orderItems)
    {
        addToOrder(orderItems.toArray(new MenuItem[0]));
    }

    /**
     * Adds to items ListView based on passed in MenuItems array
     * @param orderItems - Array of MenuItems
     */
    public static void addToOrder(MenuItem... orderItems)
    {
        for (MenuItem orderItem : orderItems)
        {
            order.add(orderItem);
        }
    }

    /**
     * Shows the combined subtotal amount, sales tax amount, total amount of all orders in ListView
     */
    private void updateOrderCosts()
    {
        orderSubtotal.setText(MainActivity.formatPrice(order.getOrderSubTotal()));
        salesTax.setText(MainActivity.formatPrice(order.getSalesTax()));
        total.setText(MainActivity.formatPrice(order.getTotal()));
    }

    /**
     * Resets all user input fields to default values after order has been placed
     */
    public void resetOrder()
    {
        order = new Order();
        orderItemsAdapter.clear();
        updateOrderCosts();

    }

    /**
     * This method saves the data in an activity after going back and returning to it
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * This method is a helper that corresponds to removing an item from the ListView
     * just by clicking on it
     * It will remove the order item from the ListView based on user selection click
     * @param item - MenuItem object being removed
     */
    public void cancelItem(MenuItem item)
    {
        if(item == null) return;
        order.remove(item);
        orderItemsAdapter.remove(item);
    }

    /**
     * It initializes all the GUI components linking the code to the GUI
     */
    private void initViews()
    {
        orderSubtotal = findViewById(R.id.orderSubTotal);
        salesTax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        orderListView = findViewById(R.id.orders);
        placeOrder = findViewById(R.id.placeOrder);
    }

}



