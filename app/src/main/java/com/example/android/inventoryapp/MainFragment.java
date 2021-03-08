package com.example.android.inventoryapp;

import android.util.SparseIntArray;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

import com.google.android.material.transition.MaterialFadeThrough;

public class MainFragment extends Fragment {
    private static final SparseIntArray LAYOUT_RES_MAP = new SparseIntArray();

    static {
        LAYOUT_RES_MAP.append(R.id.item_home, R.layout.fragment_home);
        LAYOUT_RES_MAP.append(R.id.item_record, R.layout.fragment_record);
        LAYOUT_RES_MAP.append(R.id.item_report, R.layout.fragment_report);
        LAYOUT_RES_MAP.append(R.id.item_sign, R.layout.fragment_sign);
        LAYOUT_RES_MAP.append(R.id.item_add, R.layout.fragment_add_product);

    }
    @LayoutRes
    private static int getLayoutForItemId(@IdRes int itemId) {
        return LAYOUT_RES_MAP.get(itemId);
    }



    private void replaceFragment(@IdRes int itemId) {
        Fragment fragment ;

        getLayoutForItemId(itemId);

        getLayoutForItemId(itemId);



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
}
