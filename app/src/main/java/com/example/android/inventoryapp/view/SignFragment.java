package com.example.android.inventoryapp.view;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;

import com.example.android.inventoryapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialFadeThrough;


public class SignFragment extends Fragment {

    TextInputEditText mTextInputEditTextUser;
    TextInputEditText mTextInputEditTextPass;
    TextInputEditText mTextInputEditTextCPass;
    Button mButtonConfirm;
    Placeholder mPlaceholder;
    Drawable mDrawable;
    ImageView imageView;
    boolean mConfirm = false;
    ConstraintLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialFadeThrough exitTransition = new MaterialFadeThrough();


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        mTextInputEditTextUser = view.findViewById(R.id.et_login_user);
        mTextInputEditTextPass = view.findViewById(R.id.et_sign_pass);
        mTextInputEditTextCPass = view.findViewById(R.id.et_sign_cpass);
        mButtonConfirm = view.findViewById(R.id.btn_login_confirm);
        imageView = view.findViewById(R.id.imageView);
        mPlaceholder = view.findViewById(R.id.placeholder);
        layout = view.findViewById(R.id.layout);
        mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(layout);

                if (mConfirm) {
                    imageView.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.ic_baseline_mood_24);
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                    imageView.setImageResource(R.drawable.ic_baseline_mood_bad_24);
                }
                mPlaceholder.setContentId(imageView.getId());
            }
        });
        return view;
    }
}