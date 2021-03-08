package com.example.android.inventoryapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.android.inventoryapp.view.AddProductFragment;
import com.example.android.inventoryapp.view.HomeFragment;
import com.example.android.inventoryapp.view.RecordFragment;
import com.example.android.inventoryapp.view.ReportFragment;
import com.example.android.inventoryapp.view.SignFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.MaterialFadeThrough;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fabAdd;
    private static final SparseIntArray LAYOUT_RES_MAP = new SparseIntArray();

    static {
        LAYOUT_RES_MAP.append(R.id.item_home, R.layout.fragment_home);
        LAYOUT_RES_MAP.append(R.id.item_record, R.layout.fragment_record);
        LAYOUT_RES_MAP.append(R.id.item_report, R.layout.fragment_report);
        LAYOUT_RES_MAP.append(R.id.item_sign, R.layout.fragment_sign);
        LAYOUT_RES_MAP.append(R.id.item_add, R.layout.fragment_add_product);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(R.id.item_home);
        
        bottomNavigationView = findViewById(R.id.bnv_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    replaceFragment(item.getItemId());
                    return true;
                });



    }

    @LayoutRes
    private static int getLayoutForItemId(@IdRes int itemId) {
        return LAYOUT_RES_MAP.get(itemId);
    }



    private void replaceFragment(@IdRes int itemId) {
        Fragment fragment ;
        switch (itemId) {
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
                fragment = new AddProductFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemId);
        }
        getLayoutForItemId(itemId);

        fragment.setEnterTransition(createTransition());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_main_container,fragment)
                .commit();

    }

    private MaterialFadeThrough createTransition() {
        MaterialFadeThrough fadeThrough = new MaterialFadeThrough();
        fadeThrough.setDuration(2000);
        // Add targets for this transition to explicitly run transitions only on these views. Without
        // targeting, a MaterialFadeThrough would be run for every view in the Fragment's layout.
        fadeThrough.addTarget(R.id.item_home);
        fadeThrough.addTarget(R.id.item_record);
        fadeThrough.addTarget(R.id.item_report);
        fadeThrough.addTarget(R.id.item_sign);
        fadeThrough.addTarget(R.id.item_add);


        return fadeThrough;
    }
/*
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
                            fragment = new AddProductFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fr_main_container, fragment).commit();

                    return true;
                }
            };*/

}