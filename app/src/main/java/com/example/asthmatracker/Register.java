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

import com.example.asthmatracker.Model.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    // Created some variables
    EditText mFullName, mEmail, mPassword, mPhone, mAge, mWeight;
    Button mRegisterBtn;
    TextView mLoginBtn;

    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference patientRef;


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
        mAge = findViewById(R.id.age);
        mWeight = findViewById(R.id.weight);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);

        // Getting the current instance of the firebase database
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        patientRef = db.getReference("Patient");


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
                // Authentication
                String name = mFullName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String age = mAge.getText().toString().trim();
                String weight = mWeight.getText().toString().trim();

                String con = "[0-9]{11}";



                // Validation
                // if email and password fields are empty
                // if password length is smaller then 6
                if(TextUtils.isEmpty(name)) {
                    mFullName.setError("Name cannot be empty");
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if(!(email.endsWith("@gmail.com")) && !(email.endsWith("@yahoo.com"))){
                    mEmail.setError("Not a valid email");
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
                if(TextUtils.isEmpty(phone)) {
                    mPhone.setError("Phone number cannot be empty");
                    return;
                }
                if(phone.length() < 11 ){
                    mPhone.setError("Phone number must be 11 digits");
                    return;
                }
                if(!(phone.startsWith("01"))){
                    mPhone.setError("Phone number must start with 01");
                    return;
                }
                if(TextUtils.isEmpty(age)) {
                    mAge.setError("Please provide your age");
                    return;
                }
                if(TextUtils.isEmpty(weight)) {
                    mWeight.setError("Please provide your weight");
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

                             // save to db
                             Patient patient = new Patient();
                             patient.setFullName(mFullName.getText().toString());
                             patient.setEmail(mEmail.getText().toString());
                             patient.setPassword(mPassword.getText().toString());
                             patient.setPhone(mPhone.getText().toString());
                             patient.setAge(mAge.getText().toString());
                             patient.setWeight(mWeight.getText().toString());


                             patientRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(patient)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Register.this, "Patient registered successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this, "Something went wrong!" + e.getMessage() , Toast.LENGTH_SHORT).show();
                                }
                            });

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