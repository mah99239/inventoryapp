package com.example.android.inventoryapp.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import com.example.android.inventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class CheckAdapter extends CursorAdapter {
    int mCount = 1;
List<Integer> list;
    public CheckAdapter(Context context, Cursor c) {

        super(context, c, 0);
        list= new ArrayList<>();

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_check, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        View viewFooter = view.inflate(context, R.layout.list_footer_check, null);
        TextView nameTextView = view.findViewById(R.id.tv_check_name);
        final TextView priceTextView = view.findViewById(R.id.tv_check_price);
        final TextView totalTextView = view.findViewById(R.id.tv_check_total);
        final EditText countEditText = view.findViewById(R.id.et_check_count);
        TextView counterAllTextView = viewFooter.findViewById(R.id.tv_footer_check_countall);

        ImageButton mPlusButton = view.findViewById(R.id.btn_check_plus);
        ImageButton mMinusButton = view.findViewById(R.id.btn_check_minuse);
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE);

        String inventoryName = cursor.getString(nameColumnIndex);
        final int intInventoryPrice = cursor.getInt(priceColumnIndex);
        //  if(cursor.moveToFirst()) {


        String inventoryPrice = String.valueOf(intInventoryPrice);
        nameTextView.setText(inventoryName);
        priceTextView.setText(inventoryPrice);
        countEditText.setText("1");
        Object as = cursor.getColumnNames();

        totalTextView.setText(inventoryPrice);
        counterAllTextView.setText(String.valueOf(as));

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = Integer.parseInt(countEditText.getText().toString());
                //  if(check ==1)check=1;
                
                countEditText.setText(String.valueOf(plusCount(check)));
                list.add(Integer.parseInt(countEditText.getText().toString()));
                totalTextView.setText(String.valueOf(subTotal(Integer.parseInt(countEditText.getText().toString()), intInventoryPrice)));
            }
        });
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = Integer.parseInt(countEditText.getText().toString());
                //if(check ==1)check=1;
                countEditText.setText(String.valueOf(minusCount(check)));
                totalTextView.setText(String.valueOf(subTotal(Integer.parseInt(countEditText.getText().toString()), intInventoryPrice)));


            }
        });
    }

    private int plusCount(int plus) { return  plus != 1000 ? plus+1 :1000 ;}

    private int minusCount(int minus) { return  minus > 1 ? minus-1 :1 ; }

    private int subTotal(int count, int price) {
    return   count==1 ? price :price *count; }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public long getItemId(int position) {

        
        return super.getItemId(position);
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
