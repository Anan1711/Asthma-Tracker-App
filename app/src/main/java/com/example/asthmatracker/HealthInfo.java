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

    TextView heartRate, spO2, temperature, humidity, air, roomTemp, bloodpressure;
    Button submit, getData;


    FirebaseDatabase db;
    DatabaseReference patientRef;
    DatabaseReference HealthInfoRef1;
    DatabaseReference HealthInfoRef2;
    DatabaseReference HealthInfoRef3;
    DatabaseReference HealthInfoRef4;
    DatabaseReference HealthInfoRef5;
    DatabaseReference HealthInfoRef6;
    DatabaseReference HealthInfoRef7;
    DatabaseReference HealthInfoRef8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_info);

        heartRate = findViewById(R.id.editTextTextPersonName2);
        spO2 = findViewById(R.id.editTextTextPersonName3);
        temperature = findViewById(R.id.editTextTextPersonName4);

        humidity = findViewById(R.id.editTextTextPersonName6);
        air = findViewById(R.id.editTextTextPersonName7);
        roomTemp = findViewById(R.id.editTextTextPersonName8);
        //bloodpressure = findViewById(R.id.editTextTextPersonName9);
        submit = findViewById(R.id.button);
        getData = findViewById(R.id.button2);

        db = FirebaseDatabase.getInstance();
        patientRef = db.getReference("Patient");
        HealthInfoRef1 = db.getReference("HeartRate");
        HealthInfoRef2 = db.getReference("SpO2");
        HealthInfoRef3 = db.getReference("Temperature");

        HealthInfoRef5 = db.getReference("Humidity");
        HealthInfoRef6 = db.getReference("Air Quality");
        HealthInfoRef7 = db.getReference("roomtemp");
        HealthInfoRef8 = db.getReference("bp");


        getData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HealthInfoRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String HeartRate = snapshot.getValue().toString();
                            heartRate.setText(HeartRate);
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



                    HealthInfoRef5.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String Humidity = snapshot.getValue().toString();
                            humidity.setText(Humidity);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

                    HealthInfoRef6.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String AirQuality = snapshot.getValue().toString();
                            air.setText(AirQuality);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });

                    HealthInfoRef7.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String RoomTemp = snapshot.getValue().toString();
                            roomTemp.setText(RoomTemp);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });
                   /* HealthInfoRef8.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String BP = snapshot.getValue().toString();
                            bloodpressure.setText(BP);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(HealthInfo.this, "Something went wrong! " + error.getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    });*/


                }
            });


        // Submitting health information.
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                healthinfo healthinfo= new healthinfo();
                healthinfo.setHeartRate(heartRate.getText().toString());
                healthinfo.setSpO2(spO2.getText().toString());
                healthinfo.setTemperature(temperature.getText().toString());

                healthinfo.setHumidity(humidity.getText().toString());
                healthinfo.setAirQuality(air.getText().toString());
                healthinfo.setRoomTemp(roomTemp.getText().toString());
               // healthinfo.setBloodpressure(bloodpressure.getText().toString());

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