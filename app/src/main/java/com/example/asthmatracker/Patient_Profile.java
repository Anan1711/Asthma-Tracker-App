package com.example.asthmatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Patient_Profile extends AppCompatActivity {

    // Variables
    EditText mFullName, mAge, mPhone, mWeight;
    Button mRegisterBtn, mBackBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__profile);

        // Initialize Variables
        mFullName = findViewById(R.id.fullName);
        mAge = findViewById(R.id.age);
        mPhone = findViewById(R.id.phone);
        mWeight = findViewById(R.id.weight);
        mRegisterBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);
        mBackBtn = findViewById(R.id.backBtn);
        
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Storing the values
                String name = mFullName.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String weight = mWeight.getText().toString().trim();

                // Validation
                if(TextUtils.isEmpty(name)) {
                    mFullName.setError("Name cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(age)) {
                    mAge.setError("Age cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(phone)) {
                    mPhone.setError("Please provide a phone number");
                    return;
                }
                if(TextUtils.isEmpty(weight)) {
                    mWeight.setError("Please provide your weight");
                    return;
                }

                // Registration is in progress
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        // Redirecting to Registration page
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}