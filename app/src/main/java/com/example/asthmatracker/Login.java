package com.example.asthmatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                // Validation
                // if email and password fields are empty
                // if password length is smaller then 6
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 6) {
                    mPassword.setError("Password Must be 6 Characters or More ");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));


                        } else {
                            Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                });

               /* Intent intent = new Intent(Login.this, PatientProfile.class);
                intent.putExtra("EMAIL",email);
                startActivity(intent);*/

            }

        });



        // Redirecting to Registration page
       mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }

   /*private void isUser(){
        final String userEnteredEmail = mEmail.getText().toString().trim();
        final String userEnteredPassword = mPassword.getText().toString().trim();

       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patient");

       Query checkUser = reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).orderByChild("email").equalTo(userEnteredEmail);


       checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){

                   String passwordFromDB = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("password").getValue(String.class);

                   if(passwordFromDB.equals(userEnteredPassword)){

                       String emailFromDB = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").getValue(String.class);
                       String fullNameFromDB = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("fullName").getValue(String.class);
                       String phoneFromDB = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("phone").getValue(String.class);
                       String weightFromDB = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("weight").getValue(String.class);

                       Intent intent = new Intent(getApplicationContext(), PatientProfile.class);


                       intent.putExtra("email",emailFromDB);
                       intent.putExtra("fullName", fullNameFromDB);
                       intent.putExtra("weight",weightFromDB);
                       intent.putExtra("phone", phoneFromDB);

                       startActivity(intent);
                   }
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
   }*/
}
