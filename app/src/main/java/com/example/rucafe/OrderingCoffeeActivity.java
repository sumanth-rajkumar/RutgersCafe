package com.example.rucafe;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingCoffee GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class OrderingCoffeeActivity extends AppCompatActivity {


    private Spinner size;
    private CheckBox cream, whippedCream, milk, syrup, caramel;
    private Spinner qty;
    private TextView coffeeSubTotal;
    private Button addCoffeeToOrder;
    private static Coffee coffee;
    public static final int MAX_SIZE_OF_QUANTITY = 6;

    /**
     * Gives the list of quantity numbers
     * @return - List with all the quantities
     */
    public List<Integer> quantityList()
    {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < MAX_SIZE_OF_QUANTITY; i++)
        {
            list.add(i);
        }
        return list;
    }

    /**
     * Gives the list of size types
     * @return - List with all the size types
     */
    public List<String> sizesList()
    {
        List<String> list = new ArrayList<>(Arrays.asList(Coffee.SIZES));
        return list;
    }

    /**
     * This method sets the UI controls of the GUI immediately after it's opened
     * @param savedInstanceState - Bundle representing the saved instance of creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_ordering);
        initViews();
        size.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, sizesList()));
        qty.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, quantityList()));
        sizeSpinnerListener(size);
        qtySpinnerListener(qty);
        addCoffeeToOrderButtonListener(addCoffeeToOrder);

    }

    /**
     * Resets all user input fields to default values after item has been added
     */
    public void reset()
    {
        coffee = new Coffee();
        qty.setSelection(0);
        size.setSelection(0);
        coffeeSubTotal.setText(coffee.getItemCostText());
        cream.setChecked(false);
        caramel.setChecked(false);
        milk.setChecked(false);
        syrup.setChecked(false);
        whippedCream.setChecked(false);
    }

    /**
     * This method corresponds to the Add order button of the GUI
     * It will add the order to ListView in basket GUI based on user input
     * @param addCoffeeToOrder - Button being listened
     */
    private void addCoffeeToOrderButtonListener(Button addCoffeeToOrder)
    {
        addCoffeeToOrder.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view)
            {
                if(qty.getSelectedItem().equals(0))
                {
                    Toast.makeText(view.getContext(), " No coffee selected.", Toast.LENGTH_LONG).show();
                    reset();
                    coffeeSubTotal.setText("0.00");
                    return;
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setTitle("Add to order");
                alert.setMessage("Confirming adding order");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        OrderingBasketActivity.addToOrder(new Coffee(coffee));
                        reset();
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

    /**
     * This method corresponds to the Spinner of size types of the GUI
     * It will update subtotal based on user selection
     * @param size - Spinner being listened
     */
    private void sizeSpinnerListener(Spinner size)
    {
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectSize();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    /**
     * This method corresponds to the Spinner of qty of the GUI
     * It will update subtotal based on user selection
     * @param qty - Spinner being listened
     */
    private void qtySpinnerListener(Spinner qty)
    {
        qty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectQuantity();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }


    /**
     * This method corresponds to the size type spinner of the GUI
     * It will update sub-total text based on user input
     */
    void selectSize()
    {
        if (size == null) return;
        coffee.setSize((String) size.getSelectedItem());
        coffeeSubTotal.setText(coffee.getItemCostText());
    }

    /**
     * This method corresponds to the quantity spinner of the GUI
     * It will update sub-total text based on user input
     */
    void selectQuantity()
    {
        if (qty == null) return;
        coffee.setQuantity((Integer) qty.getSelectedItem());
        coffeeSubTotal.setText(coffee.getItemCostText());
    }


    /**
     * It initializes all the GUI components linking the code to the GUI
     */
    private void initViews()
    {
        coffee = new Coffee();
        size = findViewById(R.id.size);
        qty = findViewById(R.id.qty);
        coffeeSubTotal = findViewById(R.id.coffeeSubTotal);
        addCoffeeToOrder = findViewById(R.id.addCoffeeToOrder);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        whippedCream = findViewById(R.id.whippedCream);
        milk = findViewById(R.id.milk);
        caramel = findViewById(R.id.caramel);
        coffeeSubTotal.setText(coffee.getItemCostText());
    }

    /**
     * This method corresponds to each addIn checkbox of the GUI
     * It will update sub-total text based on user input
     * @param view - View of checkbox
     */
    @SuppressLint("NonConstantResourceId")
    public void checkBoxListener(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.cream:
            if (checked)
            {
                coffee.add(((CheckBox) view).getText());
            }
            else
            {
                coffee.remove(((CheckBox) view).getText());
            }
            case R.id.caramel:
                if (checked)
                {
                    coffee.add(((CheckBox) view).getText());
                }
                else
                {
                    coffee.remove(((CheckBox) view).getText());
                }
            case R.id.syrup:
                if (checked)
                {
                    coffee.add(((CheckBox) view).getText());
                }
                else
                {
                    coffee.remove(((CheckBox) view).getText());
                }
            case R.id.milk:
                if (checked)
                {
                    coffee.add(((CheckBox) view).getText());
                }
                else
                {
                    coffee.remove(((CheckBox) view).getText());
                }
            case R.id.whippedCream:
                if (checked)
                {
                    coffee.add(((CheckBox) view).getText());
                }
                else
                {
                    coffee.remove(((CheckBox) view).getText());
                }
        }
        coffeeSubTotal.setText(coffee.getItemCostText());
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
}