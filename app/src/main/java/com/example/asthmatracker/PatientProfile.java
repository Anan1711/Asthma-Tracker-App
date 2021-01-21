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


        emailTextView = findViewById(R.id.emailTextView);
        nameTextView = findViewById(R.id.nameTextView);
        weightTextView = findViewById(R.id.phoneTextView);
        phoneTextView = findViewById(R.id.weightTextView);
        
        showAllData();
    }

    private void showAllData() {

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("fullName");
        String phone = intent.getStringExtra("phone");
        String weight = intent.getStringExtra("weight");

        emailTextView.setText(email);
        nameTextView.setText(name);
        weightTextView.setText(weight);
        phoneTextView.setText(phone);
    }
}