package com.example.android.inventoryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

public class TransitionSimpleLayoutFragment extends Fragment {
    private static final String KEY_LAYOUT_RES_ID = "KEY_LAYOUT_RES_ID";
    private static final String KEY_TRANSITION_NAME = "KEY_TRANSITION_NAME";
    private static final String KEY_TRANSITION_NAME_VIEW_ID = "KEY_TRANSITION_NAME_VIEW_ID";

    private int layoutResId;
    @Nullable
    private String transitionName;
    private int transitionNameViewId;

    public static TransitionSimpleLayoutFragment newInstance(@LayoutRes int layoutResId) {
        return newInstance(layoutResId, null);
    }

    public static TransitionSimpleLayoutFragment newInstance(
            @LayoutRes int layoutResId, @Nullable String transitionName) {
        return newInstance(layoutResId, transitionName, View.NO_ID);
    }

    public static TransitionSimpleLayoutFragment newInstance(
            @LayoutRes int layoutResId,
            @Nullable String transitionName,
            @IdRes int transitionNameViewId) {
        TransitionSimpleLayoutFragment fragment = new TransitionSimpleLayoutFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT_RES_ID, layoutResId);
        args.putString(KEY_TRANSITION_NAME, transitionName);
        args.putInt(KEY_TRANSITION_NAME_VIEW_ID, transitionNameViewId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        Bundle args = getArguments();
        if (args != null) {
            layoutResId = args.getInt(KEY_LAYOUT_RES_ID);
            transitionName = args.getString(KEY_TRANSITION_NAME);
            transitionNameViewId = args.getInt(KEY_TRANSITION_NAME_VIEW_ID);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(layoutResId, viewGroup, false /* attachToRoot */);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        if (transitionName != null) {
            View transitionNameView =
                    transitionNameViewId == View.NO_ID ? view : view.findViewById(transitionNameViewId);
            ViewCompat.setTransitionName(transitionNameView, transitionName);
        }
    }
}
