package com.example.sowmik.offline3;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SameTrainNameList extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseTrains;

    private ListView listView1;
    private TextView textView;

    ArrayList<String> trainsArrayList1;
    ArrayAdapter<String> trainsArrayAdapter1;
    Train trains;

    String u_name;


    ProgressDialog mprogressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_train_name_list);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseTrains = firebaseDatabase.getReference("train_list");

        listView1 = findViewById(R.id.trainlistid);
        textView = findViewById(R.id.trainnameid);

        mprogressDialog = new ProgressDialog(this);

        Bundle bundle = getIntent().getExtras();


        if(bundle!=null) {

            String t_name = bundle.getString("train_name");
            u_name = bundle.getString("u_name");


            final String trainName = t_name;

            mprogressDialog.setMessage("Loading...");
            mprogressDialog.show();

            textView.setText("Name: "+ t_name);


            if (!isConnected(SameTrainNameList.this)){

                mprogressDialog.dismiss();
                builder(SameTrainNameList.this).show();
            }
            else {

                trainsArrayList1 = new ArrayList<>();
                trainsArrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trainsArrayList1);
                trains = new Train();


                Query query = FirebaseDatabase.getInstance().getReference("train_list")
                        .orderByChild("name")
                        .equalTo(trainName);


                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {


                            trains = ds.getValue(Train.class);
                            trainsArrayList1.add(trains.getName().toString() + "  " + trains.getNo().toString());

                        }
                        listView1.setAdapter(trainsArrayAdapter1);


                        mprogressDialog.dismiss();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }

                });

            }


        }



        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String value = trainsArrayList1.get(position);

                //Toast.makeText(TrainListActivity.this,value+" "+position, Toast.LENGTH_SHORT).show();

                String train_no = value.replaceAll("\\D+","");
                Toast.makeText(SameTrainNameList.this, ""+train_no, Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(SameTrainNameList.this,TrainDetails.class);
                intent.putExtra("t_number","" + train_no);
                intent.putExtra("u_name",u_name);

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



}

