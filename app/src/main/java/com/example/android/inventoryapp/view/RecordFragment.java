package com.example.android.inventoryapp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.inventoryapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialFadeThrough;


public class RecordFragment extends Fragment {
TextInputEditText mTextInputEditTextSearch;
ListView mListView;
    Typeface courgette;
    TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialFadeThrough exitTransition = new MaterialFadeThrough();

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        courgette = Typeface.createFromAsset(getActivity().getAssets(),"Courgette-Regular.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_record, container, false);
        mTextInputEditTextSearch =view.findViewById(R.id.et_record_search);
        mListView =view.findViewById(R.id.lst_record);
        textView=view.findViewById(R.id.textView);
        textView.setTypeface(courgette);
        return view;
    }
}