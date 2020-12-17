package com.example.android.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class LoginInActivity extends AppCompatActivity {
    TextInputEditText mTextInputEditTextUser;
    TextInputEditText mTextInputEditTextPass;
    Button mButtonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        mTextInputEditTextUser = findViewById(R.id.et_login_user);
        mTextInputEditTextPass =findViewById(R.id.et_login_pass);
        mButtonLogin =findViewById(R.id.btn_login_confirm);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}