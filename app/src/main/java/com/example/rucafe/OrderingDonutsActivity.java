package com.example.rucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


/**
 * This class is what controls all the actions for each GUI components using the inputs given by the
 * user in the orderingDonuts GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class OrderingDonutsActivity extends AppCompatActivity {

    private Button addToOrder;
    private static ArrayList<Donut> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.jelly, R.drawable.glazed, R.drawable.chocolatefrosted,
            R.drawable.strawberryfrosted, R.drawable.sugar, R.drawable.lemonfilled, R.drawable.cinammonsugar,
            R.drawable.oldfashioned, R.drawable.blueberry, R.drawable.glazedholes, R.drawable.jellyholes, R.drawable.cinammonsugarholes};

    private RecyclerView recyclerView;
    private DonutRecViewAdapter donutAdapter;
    private TextView subTotal;
    public static final int MAX_SIZE_OF_FLAVORS = 12;

    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * items to be display in the RecyclerView.
     * @param savedInstanceState - Bundle representing the saved instance of creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts_ordering);

        recyclerView = findViewById(R.id.donutRecView);
        subTotal = findViewById(R.id.subTotal);
        addToOrder = findViewById(R.id.addToOrder);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addToOrderButtonListener(addToOrder);
        initialize();

    }

    /**
     * Shows the combined subtotal amount of all items in RecyclerView
     */
    public void refreshOrderTotal()
    {
        double totalPrice =  items.stream().mapToDouble(MenuItem::getItemCost).sum();
        subTotal.setText(MainActivity.formatPrice(totalPrice));
    }

    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void initialize()
    {
        if(items.isEmpty())
        {

            String[] itemNames = getResources().getStringArray(R.array.itemNames);
            for (int i = 0; i < itemNames.length; i++)
            {
                items.add(new Donut(itemNames[i], itemImages[i]));
            }
        }
        donutAdapter = new DonutRecViewAdapter(this, items);
        recyclerView.setAdapter(donutAdapter);

    }

    /**
     * This method is a listener that corresponds to the button Add To Order
     * Adds items to ListView in basketGUI based on user button click in alert dialogs
     * @param addToOrder - Button being listened
     */
    private void addToOrderButtonListener(Button addToOrder)
    {
        addToOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Collection<Donut> zeroItems = items.stream().filter(d -> d.getQuantity()==0).map(Donut::new).collect(Collectors.toList());
                if(zeroItems.size() == MAX_SIZE_OF_FLAVORS)
                {
                    Toast.makeText(view.getContext(), " No donut was selected.", Toast.LENGTH_LONG).show();
                    return;
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Add to order");
                alert.setMessage("Confirming adding order");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Collection<Donut> nonZeroItems = items.stream().filter(d -> d.getQuantity()>0).map(Donut::new).collect(Collectors.toList());
                        OrderingBasketActivity.addToOrder(nonZeroItems);
                        donutAdapter.reset();
                        Toast.makeText(view.getContext(), " Order added.", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(view.getContext(),
                                " Order not added.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

}
