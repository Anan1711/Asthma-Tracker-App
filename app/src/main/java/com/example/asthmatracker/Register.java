package com.example.asthmatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    // Created some variables
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initializing variables;
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);

        // Getting the current instance of the firebase database
        fAuth = FirebaseAuth.getInstance();

        // Checking if the user already has an account
        // If so then sending the user to the main activity
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        // Handling Register button for authentication
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Storing the values
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                // Validation
                // if email and password fields are empty
                // if password length is smaller then 6
                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6) {
                    mPassword.setError("Password Must be 6 Characters or More ");
                    return;
                }

                // Registration is in progress
                progressBar.setVisibility(View.VISIBLE);

                // Register the user in Firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Checking is the registration is successful or not
                        // If successful sending the user to the main activity
                        // If not showing error message
                         if(task.isSuccessful()) {
                             Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getApplicationContext(),MainActivity.class));

                         } else {
                             Toast.makeText(Register.this, "Error occurred!" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                             progressBar.setVisibility(View.GONE);
                         }
                    }
                });
            }
        });

        // Redirecting to login page
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}