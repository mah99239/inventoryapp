package com.example.android.inventoryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;


public class SignFragment extends Fragment {

    TextInputEditText mTextInputEditTextUser;
    TextInputEditText mTextInputEditTextPass;
    TextInputEditText mTextInputEditTextCPass;
    Button mButtonConfirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        mTextInputEditTextUser = view.findViewById(R.id.et_login_user);
        mTextInputEditTextPass = view.findViewById(R.id.et_sign_pass);
        mTextInputEditTextCPass = view.findViewById(R.id.et_sign_cpass);
        mButtonConfirm = view.findViewById(R.id.btn_login_confirm);

        return view;
    }
}