package com.example.sowmik.offline3;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrainListActivity extends AppCompatActivity {


    //DbHelper dbHelper;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseTrains;

    private ListView listView;
    private TextView departureStation,arrivalStation;

    //List<String> trainNames = new ArrayList<>();

    ArrayList<String> trainsArrayList;
    ArrayAdapter<String> trainsArrayAdapter;
    Train trains;

    String u_name,d,a;

    ProgressDialog mprogressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        dbHelper = new DbHelper(this);
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseTrains = firebaseDatabase.getReference("train_list");


        mprogressDialog = new ProgressDialog(this);

        departureStation = findViewById(R.id.d_id);
        arrivalStation = findViewById(R.id.a_id);

        listView = findViewById(R.id.listviewid);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {

            final String departure1 = bundle.getString("DEPARTURE");
            final String arrival1 = bundle.getString("ARRIVAL");

            d = departure1;
            a = arrival1;

            u_name = bundle.getString("name");

            mprogressDialog.setMessage("Loading...");
            mprogressDialog.show();

            final String dep_arrival = departure1+arrival1;


            if (!isConnected(TrainListActivity.this)){

                mprogressDialog.dismiss();
                builder(TrainListActivity.this).show();
            }
            else {


                departureStation.setText("Departure: " + departure1);
                arrivalStation.setText("Arrival: " + arrival1);

                //            Cursor cursor = dbHelper.SearchByDepartureArrival(departure1,arrival1);
                //
                //
                //            StringBuffer stringBuffer = new StringBuffer();
                //
                //            while (cursor.moveToNext()) {
                //
                //
                //                trainNames.add(cursor.getString(1) +" "+ cursor.getString(2));
                //
                //            }


                if (departure1.equals(arrival1)){

                    showNoTrain(TrainListActivity.this).show();

                }
                else {

                    trainsArrayList = new ArrayList<>();
                    trainsArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trainsArrayList);
                    trains = new Train();


                    Query query = FirebaseDatabase.getInstance().getReference("train_list")
                            .orderByChild("sub_stations");


                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {


                                trains = ds.getValue(Train.class);

                                String str1 = trains.getSub_station().toString();
                                String str2 = trains.getDeparture().toString();

                                if (str1.toLowerCase().contains(departure1.toLowerCase()) && str1.toLowerCase().contains(arrival1.toLowerCase()) && departure1.equals(str2)) {

                                    trainsArrayList.add(trains.getName().toString() + "  " + trains.getNo().toString());

                                }
                            }

                            if (trainsArrayList.isEmpty()) {

                                showNoTrain(TrainListActivity.this).show();
                            }

                            listView.setAdapter(trainsArrayAdapter);

                            mprogressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }

                    });
                }
            }



        }



//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,trainNames);
//        listView.setAdapter(adapter);
//

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String value = trainsArrayList.get(position);

                //Toast.makeText(TrainListActivity.this,value+" "+position, Toast.LENGTH_SHORT).show();

                String train_no = value.replaceAll("\\D+","");
                Toast.makeText(TrainListActivity.this, ""+train_no, Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(TrainListActivity.this,TrainDetails.class);

                intent.putExtra("t_number","" + train_no);
                intent.putExtra("u_name",u_name);

                intent.putExtra("dep",d);
                intent.putExtra("arr",a);

                startActivity(intent);
            }
        });



    }



    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo !=null && networkInfo.isConnectedOrConnecting()) {

            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile!=null && mobile.isConnected()) || (wifi!=null && wifi.isConnectedOrConnecting())){

                return  true;
            }
            else return false;

        }
        else return false;
    }



    public AlertDialog.Builder builder(Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to hava Mobile Data or Wifi to access this.");

        return builder;

    }

    public AlertDialog.Builder  showNoTrain (Context context){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sorry!!!");
        builder.setMessage("No train found in this route.");

        return builder;

    }


}

