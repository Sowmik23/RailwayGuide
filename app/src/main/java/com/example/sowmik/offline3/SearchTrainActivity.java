package com.example.sowmik.offline3;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchTrainActivity extends AppCompatActivity {

    DbHelper dbHelper;

    private Spinner departureSpinner,arrivalSpinner;
    private Button SearchButton;
    String[] Departure,Arrival;

    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbHelper = new DbHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        departureSpinner = findViewById(R.id.departurespinnarid);
        arrivalSpinner = findViewById(R.id.arrivalspinnarid);

        SearchButton = findViewById(R.id.searchbuttonid);

        Departure = getResources().getStringArray(R.array.from_names);
        Arrival  = getResources().getStringArray(R.array.to_names);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {

            user_name = bundle.getString("name");
        }


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Departure);
        departureSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Arrival);
        arrivalSpinner.setAdapter(adapter2);


        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String value1 = (String) departureSpinner.getSelectedItem();
                String value2 = (String) arrivalSpinner.getSelectedItem();

                if (value1.equals("Select Departure") || value2.equals("Select Arrival")) {

                    showErrorNotice("Warning!","Please select your DEPARTURE & ARRIVAL station.");

                }
                else {

//                    Cursor cursor = dbHelper.SearchByDepartureArrival(value1,value2);
//
//                    if (cursor.getCount() == 0) {
//
//                        showErrorNotice("Sorry!", "No train found");
//                        return;
//                    }
//                    else {

                    Intent intent = new Intent(SearchTrainActivity.this, TrainListActivity.class);

                    intent.putExtra("DEPARTURE", "" + value1);
                    intent.putExtra("ARRIVAL", "" + value2);

                    intent.putExtra("name",user_name);

                    startActivity(intent);
                    // }
                }


            }
        });


    }

    public void showErrorNotice(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }



}

