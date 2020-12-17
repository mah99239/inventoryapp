package com.example.android.inventoryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ReportFragment extends Fragment {

    AutoCompleteTextView mAutoCompleteTextViewShow;
    TextInputEditText mTextInputEditTextSearch;
    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String []item =new String[]{"day","month","year"};

        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_report, container, false);
        mAutoCompleteTextViewShow= view.findViewById(R.id.actv_report_show);
        mTextInputEditTextSearch =view.findViewById(R.id.et_record_search);
        mListView =view.findViewById(R.id.lst_report);
        ArrayAdapter<String> adapter =new ArrayAdapter<>(
                getActivity(),
                R.layout.dropdown_item,
                item
        );

        mAutoCompleteTextViewShow.setAdapter(adapter);
        return view;
    }
}