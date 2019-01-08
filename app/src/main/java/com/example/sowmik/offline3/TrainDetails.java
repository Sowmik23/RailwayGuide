package com.example.sowmik.offline3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class TrainDetails extends AppCompatActivity implements View.OnClickListener {

    private Button datePickerButton;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        datePickerButton = findViewById(R.id.date_picker_id);
        datePickerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        DatePicker datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth())+1;
        int currentYear = datePicker.getYear();


        datePickerDialog = new DatePickerDialog(this,

                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        //textView.setText(dayOfMonth +"/"+ (month+1) +"/"+year);
                        Toast.makeText(TrainDetails.this, dayOfMonth +"/"+ (month+1) +"/"+year, Toast.LENGTH_SHORT).show();

                    }
                },currentYear,currentMonth,currentDay);

        datePickerDialog.show();

    }
}
