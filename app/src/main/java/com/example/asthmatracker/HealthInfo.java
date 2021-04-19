package com.example.asthmatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asthmatracker.Model.healthinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HealthInfo extends AppCompatActivity {

    EditText heartrate, spO2, temperature;
    Button submit, getData;


    FirebaseDatabase db;
    DatabaseReference patientRef;
    DatabaseReference HealthInfoRef1;
    DatabaseReference HealthInfoRef2;
    DatabaseReference HealthInfoRef3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        heartrate = findViewById(R.id.editTextTextPersonName2);
        spO2 = findViewById(R.id.editTextTextPersonName3);
        temperature = findViewById(R.id.editTextTextPersonName4);
        submit = findViewById(R.id.button);
        getData = findViewById(R.id.button2);

        db = FirebaseDatabase.getInstance();
        patientRef = db.getReference("Patient");
        HealthInfoRef1 = db.getReference("HeartRate");
        HealthInfoRef2 = db.getReference("SpO2");
        HealthInfoRef3 = db.getReference("Temperature");


        getData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HealthInfoRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String HeartRate = snapshot.getValue().toString();
                            heartrate.setText(HeartRate);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

                    HealthInfoRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String SpO2 = snapshot.getValue().toString();
                            spO2.setText(SpO2);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

                    HealthInfoRef3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Temperature = snapshot.getValue().toString();
                            temperature.setText(Temperature);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });


        // Submitting health information.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                healthinfo healthinfo= new healthinfo();
                healthinfo.setHeartRate(heartrate.getText().toString());
                healthinfo.setSpO2(spO2.getText().toString());
                healthinfo.setTemperature(temperature.getText().toString());

                patientRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("healthInfo").setValue(healthinfo)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(HealthInfo.this, "Data Successfully Submitted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HealthInfo.this, "Something went wrong! " + e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}