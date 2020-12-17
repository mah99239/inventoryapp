package com.example.android.inventoryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;


public class RecordFragment extends Fragment {
TextInputEditText mTextInputEditTextSearch;
ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_record, container, false);
        mTextInputEditTextSearch =view.findViewById(R.id.et_record_search);
        mListView =view.findViewById(R.id.lst_record);
        return view;
    }
}