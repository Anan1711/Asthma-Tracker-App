package com.example.asthmatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfile extends AppCompatActivity {
    private TextView emailTextView,phoneTextView,fullnameTextView;
    private String email;
    private static final String PATIENTS = "Patient";
    private final String TAG = this.getClass().getName().toUpperCase();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference patientRef = rootRef.child(PATIENTS).child("PdLOgfKbEvXFpWJcztuew5W0oGd2");

        emailTextView = findViewById(R.id.email_textview);
        fullnameTextView = findViewById(R.id.fullName_textview);
        phoneTextView = findViewById(R.id.phone_textview);

        //Read from database
        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("fullName").getValue().toString();
                String name = dataSnapshot.child("email").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                emailTextView.setText(email);
                fullnameTextView.setText(name);
                phoneTextView.setText(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}