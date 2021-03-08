package com.example.android.inventoryapp;

import android.app.Application;

import com.example.android.inventoryapp.view.adapters.InventoryAdapter;

public class MyApplication extends Application {
   static InventoryAdapter mInventoryAdapter;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static InventoryAdapter getInventoryAdapter(){
        return mInventoryAdapter;
    }
}
