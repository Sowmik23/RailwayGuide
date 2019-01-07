package com.example.sowmik.offline3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView SearchTrainCardView,LocateTrainCardView,TravelHistoryCardView,TrainInfoCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        SearchTrainCardView = findViewById(R.id.searchtrainid);
        LocateTrainCardView = findViewById(R.id.locatetrainid);
        TravelHistoryCardView = findViewById(R.id.travelhistoryid);
        TrainInfoCardView = findViewById(R.id.traininfoid);

        SearchTrainCardView.setOnClickListener(this);
        LocateTrainCardView.setOnClickListener(this);
        TravelHistoryCardView.setOnClickListener(this);
        TrainInfoCardView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.searchtrainid)
        {

            Intent intent = new Intent(SecondActivity.this,SearchTrainActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.locatetrainid)
        {

            Intent intent = new Intent(SecondActivity.this,LocateTrainActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.travelhistoryid)
        {

            Intent intent = new Intent(SecondActivity.this,TravelHistoryActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.traininfoid)
        {

            Intent intent = new Intent(SecondActivity.this,TrainInfoActivity.class);
            startActivity(intent);

        }


    }
}
