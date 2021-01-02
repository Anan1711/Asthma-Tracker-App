package com.example.asthmatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfile extends AppCompatActivity {
    private TextView emailTextView,nameTextView,weightTextView,phoneTextView;
    private String email;
    private static final String PATIENTS = "Patient";
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference patientRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference patientRef = rootRef.child(PATIENTS);

        emailTextView = findViewById(R.id.emailTextView);
        nameTextView = findViewById(R.id.nameTextView);
        weightTextView = findViewById(R.id.phoneTextView);
        phoneTextView = findViewById(R.id.weightTextView);

        //Read from database
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("email").getValue().equals(email)){
                        String email = dataSnapshot.child("email").getValue().toString();
                        String name = dataSnapshot.child("fullName").getValue().toString();
                        String weight = dataSnapshot.child("weight").getValue().toString();
                        String phone = dataSnapshot.child("phone").getValue().toString();
                        emailTextView.setText(email);
                        nameTextView.setText(name);
                        weightTextView.setText(weight);
                        phoneTextView.setText(phone);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}