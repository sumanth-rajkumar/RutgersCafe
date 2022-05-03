package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * This class is the Main GUI that has the buttons that lead to all the other GUIs
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton orderDonuts, orderCoffee, basketOrder, storeOrder;

    /**
     * Links all button of GUI to go the respective activities when button clicked
     * @param savedInstanceState - Bundle representing the saved instance of creation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


        orderDonuts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent donut = new Intent(MainActivity.this, OrderingDonutsActivity.class);
                startActivity(donut);
            }
        });

        orderCoffee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderingCoffeeActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            }
        });


        basketOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent basket = new Intent(MainActivity.this, OrderingBasketActivity.class);
                startActivity(basket);
            }
        });

       storeOrder.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Gives the formatted cost of a menu item
     * @param val - double representing cost
     * @return - String representing the formatted cost
     */
    @SuppressLint("DefaultLocale")
    public static String formatPrice(double val)
    {
        return String.format("%1$.2f", val);
    }

    /**
     * It initializes all the GUI components linking the code to the GUI
     */
    private void initViews()
    {
        orderDonuts = findViewById(R.id.orderDonuts);
        orderCoffee = findViewById(R.id.orderCoffee);
        storeOrder = findViewById(R.id.storeOrder);
        basketOrder = findViewById(R.id.basketOrder);

    }


}