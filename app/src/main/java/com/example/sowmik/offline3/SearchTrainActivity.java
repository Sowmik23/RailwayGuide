package com.example.sowmik.offline3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchTrainActivity extends AppCompatActivity {

    private Spinner DivisionSpinner,FromSpinner,ToSpinner;
    private Button SearchButton;
    String[] Division,From,To;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        DivisionSpinner = findViewById(R.id.divisionspinnerid);
        FromSpinner = findViewById(R.id.fromspinnerid);
        ToSpinner = findViewById(R.id.tospinnerid);

        SearchButton = findViewById(R.id.searchbuttonid);

        Division =getResources().getStringArray(R.array.division_names);
        From = getResources().getStringArray(R.array.from_names);
        To  = getResources().getStringArray(R.array.to_names);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Division);
        DivisionSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,From);
        FromSpinner.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,To);
        ToSpinner.setAdapter(adapter3);


        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value1 = (String) DivisionSpinner.getSelectedItem();
               // Toast.makeText(SearchTrainActivity.this, "Division: "+value1, Toast.LENGTH_SHORT).show();
                String value2 = (String) FromSpinner.getSelectedItem();
                String value3 = (String) ToSpinner.getSelectedItem();

                Toast.makeText(SearchTrainActivity.this, "Selected items are:\nDivision: "+value1+"\nFrom: "+value2+"\nTo: "+value3+"\n", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
