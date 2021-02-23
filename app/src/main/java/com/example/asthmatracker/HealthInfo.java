package com.example.asthmatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asthmatracker.Model.healthinfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HealthInfo extends AppCompatActivity {

    EditText heartrate, spO2, temperature;
    Button submit;

    FirebaseDatabase db;
    DatabaseReference patientRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        heartrate = findViewById(R.id.editTextTextPersonName2);
        spO2 = findViewById(R.id.editTextTextPersonName3);
        temperature = findViewById(R.id.editTextTextPersonName4);
        submit = findViewById(R.id.button);

        db = FirebaseDatabase.getInstance();
        patientRef = db.getReference("Patient");

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
                        Toast.makeText(HealthInfo.this, "Something went wrong!" + e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}