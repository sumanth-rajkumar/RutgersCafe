package com.example.rucafe;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * DonutRecViewAdapter Class is a class that gives the GUI a list of donuts with it's respective properties
 * and the corresponding methods that go with it.
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class DonutRecViewAdapter extends RecyclerView.Adapter<DonutRecViewAdapter.ItemsHolder> {

    private OrderingDonutsActivity context;
    private ArrayList<Donut> items;
    private ArrayList<ItemsHolder> holders = new ArrayList<>();
    public static final int MAX_SIZE_OF_QUANTITY = 6;

    /**
     * Constructor that creates a DonutRecViewAdapter object based off the given context and arraylist.
     * It assigns the proper values to the instance variables context and items
     * @param context - OrderingDonutsActivity class
     * @param items - ArrayList of donut objects
     */
    public DonutRecViewAdapter(OrderingDonutsActivity context, ArrayList<Donut> items)
    {
        this.context = context;
        this.items = items;

    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent - view of the GUI
     * @param viewType - int representing type of view
     * @return ItemHolder object based on parameters
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder   the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, @SuppressLint("RecyclerView") int position)
    {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < MAX_SIZE_OF_QUANTITY; i++)
        {
            list.add(i);
        }

        holder.tv_name.setText(items.get(position).getDonutFlavor());
        holder.tv_price.setText(Double.toString(items.get(position).getUnitPrice()));
        holder.im_item.setImageResource(items.get(position).getImage());
        holder.quantity.setAdapter(new ArrayAdapter<Integer>(context, R.layout.support_simple_spinner_dropdown_item, list));

        holder.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                    Integer qty = (Integer) holder.quantity.getSelectedItem();
                    items.get(position).setQuantity(qty);
                    context.refreshOrderTotal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        holder.quantity.setSelection(items.get(position).getQuantity());
        holders.add(holder);
    }

    /**
     * Resets all user input fields to default values after item has been added
     */
    public void reset()
    {
        items.stream().forEach(d -> d.setQuantity(0));
        holders.stream().forEach(h -> h.quantity.setSelection(0));
        context.refreshOrderTotal();
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount()
    {
        return items.size();
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Spinner quantity;

        /**
         * Constructor that creates a ItemsHolder object in the given view
         * It initializes all the GUI components linking the code to the GUI
         * @param itemView - view of ItemHolder object
         */
        public ItemsHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            quantity = itemView.findViewById(R.id.quantity);
            im_item = itemView.findViewById(R.id.im_item);

        }


    }
}
