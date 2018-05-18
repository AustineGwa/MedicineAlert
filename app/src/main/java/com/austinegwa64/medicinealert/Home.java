package com.austinegwa64.medicinealert;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.austinegwa64.medicinealert.Adapters.SqliteDatabaseAdapter;
import com.austinegwa64.medicinealert.Models.Medicine;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Medicine> loadedMedication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadedMedication = new ArrayList<>();


        recyclerView = findViewById(R.id.medication_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SqliteDatabaseAdapter( loadedMedication);
        recyclerView.setHasFixedSize(true);

        refresh();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_event){
            Intent i = new Intent(this, AddMedication.class);
            startActivity(i);
        } else if (id == R.id.refresh){

           Intent i = new Intent(this, MyService.class);
           startService(i);

        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        DatabaseCon databaseCon = new DatabaseCon(this);
        Cursor cursor = databaseCon.getALLMedication();

        while(cursor.moveToNext()){

                loadedMedication.clear();

                //int m_id = cursor.getInt(0);
                String name = cursor.getString(0);
                String desc = cursor.getString(1);
                String presc = cursor.getString(2);
                String start = cursor.getString(3);

                Medicine med = new Medicine("medicine name :" + name, "medicine description :" + desc, "medicine prescription :" + presc, "medicine start date :" + start);

                loadedMedication.add(med);

                cursor.moveToNext();
        }
       // cursor.moveToNext();


        if(!(loadedMedication.size() <1 )){

            recyclerView.setAdapter(adapter);

        } else {
            Toast.makeText(this, "no item in the database ", Toast.LENGTH_SHORT).show();
        }
    }




}
