package com.example.android.inventoryapp.view;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.android.inventoryapp.R;
import com.example.android.inventoryapp.model.contact.InventoryContract;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialFadeThrough;

public class AddProductFragment extends Fragment {

    TextInputEditText mNameTextInputEditText;
    TextInputEditText mPriceTextInputEditText;
    TextInputEditText mQuantityTextInputEditText;
    Button mButtonAdd;
    Button mButtonCancel;
    private Uri mCurrentUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialFadeThrough exitTransition = new MaterialFadeThrough();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        mNameTextInputEditText = view.findViewById(R.id.et_addProduct_name);
        mPriceTextInputEditText = view.findViewById(R.id.et_addProduct_price);
        mQuantityTextInputEditText = view.findViewById(R.id.et_addProduct_quantity);

        mButtonAdd = view.findViewById(R.id.btn_addProduct_add);
        mButtonCancel = view.findViewById(R.id.btn_addProduct_cancel);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });
        return view;
    }

    public void saveProduct() {
        String name = mNameTextInputEditText.getText().toString().trim();
        String stringPrice = mPriceTextInputEditText.getText().toString().trim();
        String stringQuantity = mQuantityTextInputEditText.getText().toString().trim();
        if (mCurrentUri == null && TextUtils.isEmpty(name)) {
            return;
        }
        int price = 0;
        int quantity = 0;


        if (!TextUtils.isEmpty(stringPrice) && !TextUtils.isEmpty(stringQuantity)) {
            price = Integer.parseInt(stringPrice);
            quantity = Integer.parseInt(stringQuantity);
        }

        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_NAME, name);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_PRICE, price);
        values.put(InventoryContract.InventoryEntry.COLUMN_INVENTORY_QUANTITY, quantity);

        if (mCurrentUri == null) {
            Uri newRowId = getActivity().getContentResolver().insert(InventoryContract.CONTENT_URI, values);
            if (newRowId == null) {
                Toast.makeText(getContext(),getString(R.string.addProduct_saveFailed) , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(),getString(R.string.addProduct_saveSuccessful) , Toast.LENGTH_SHORT);
            }


        }

    }

}