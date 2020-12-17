package com.example.android.inventoryapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bnv_main);
      //  bottomNavigationView.getMenu().findItem(R.id.placeholder).setEnabled(false);
        // bottomNavigationView = findViewById(R.id.bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fr_main_container, new HomeFragment()).commit();
      //  fabAdd =findViewById(R.id.fab_main_add);
      
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()) {
                        case R.id.item_home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.item_record:
                            fragment = new RecordFragment();
                            break;
                        case R.id.item_report:
                            fragment = new ReportFragment();
                            break;
                        case R.id.item_sign:
                            fragment = new SignFragment();
                            break;
                        case R.id.item_add:
                            fragment=new AddProductFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fr_main_container, fragment).commit();

                    return true;
                }
            };

}