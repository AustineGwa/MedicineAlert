package com.austinegwa64.medicinealert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.austinegwa64.medicinealert.Models.Medicine;

public class AddMedication extends AppCompatActivity {

    EditText medName, medDesc, startDte;
    TextView error;
    Spinner  medprescription;
    Button save;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medName = findViewById(R.id.name);
        medDesc = findViewById(R.id.desc);
        startDte = findViewById(R.id.startDate);
        medprescription = findViewById(R.id.prescription);
        error = findViewById(R.id.error);

        save = findViewById(R.id.save);
        image = findViewById(R.id.medImage);

        final DatabaseCon databaseCon = new DatabaseCon(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =  medName.getText().toString();
                String desc =  medDesc.getText().toString();
                String start = startDte.getText().toString();
                String prescription = medprescription.getSelectedItem().toString();

                Medicine med = new Medicine(name,desc,prescription,start);
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(start) && !TextUtils.isEmpty(prescription)) {
                    databaseCon.addNewMedication(med);
                    medName.setText("");
                    medDesc.setText("");
                    startDte.setText("");
                   // medprescription.setSelected(0);

                    Toast.makeText(getApplicationContext(), " data has been saved succesfully ", Toast.LENGTH_SHORT).show();

                } else{

                    error.setText("Please fill all the entries");
                }

            }
        });
    }
}
