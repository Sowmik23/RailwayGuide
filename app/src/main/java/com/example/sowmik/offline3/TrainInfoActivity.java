package com.example.sowmik.offline3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TrainInfoActivity extends AppCompatActivity {

    private EditText trainName,trainNumber;
    private Button search1,search2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        trainName = findViewById(R.id.train_nameId);
        trainName = findViewById(R.id.train_numberId);

        search1 = findViewById(R.id.search_button1id);
        search2 = findViewById(R.id.search_button2id);

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrainInfoActivity.this,TrainDetails.class);
                startActivity(intent);
            }
        });


        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrainInfoActivity.this,TrainDetails.class);
                startActivity(intent);
            }
        });

    }
}
