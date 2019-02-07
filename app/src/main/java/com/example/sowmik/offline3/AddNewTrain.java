package com.example.sowmik.offline3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddNewTrain extends AppCompatActivity {


    //DbHelper dbHelper;

    private EditText trainName,trainNo,offday,departurStation,departureTime,arrivalStation,arrivalTime;
    private Button insertButton,displayAlldata;

    DatabaseReference databaseTrains ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_train);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        dbHelper = new DbHelper(this);
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        databaseTrains = FirebaseDatabase.getInstance().getReference("train_list");



        trainName = findViewById(R.id.train_nameid);
        trainNo = findViewById(R.id.train_noid);
        offday = findViewById(R.id.offdayid);
        departurStation = findViewById(R.id.departurestationid);
        departureTime = findViewById(R.id.departuretimeid);
        arrivalStation = findViewById(R.id.arrivalstationid);
        arrivalTime = findViewById(R.id.arrivaltimeid);

        insertButton = findViewById(R.id.insertbuttonid);
        //displayAlldata = findViewById(R.id.displaydataid);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addNewTrain();


//                DatabaseReference dbNode = FirebaseDatabase.getInstance().getReference("t_list").getRoot().child("no");
//
//                dbNode.setValue(null);

//                final FirebaseDatabase firebaseDatabase;
//                databaseTrains = FirebaseDatabase.getInstance().getReference();
//                Query query = databaseTrains.child("t_list").orderByChild("no").equalTo(trainNo.getText().toString());
//                Query query1 = databaseTrains.child("t_list").orderByChild("name_no");
//                Query query2 = databaseTrains.child("t_list").orderByChild("name");
//                Query query3 = databaseTrains.child("t_list").orderByChild("id");
//                Query query4 = databaseTrains.child("t_list").orderByChild("departure_TIME");
//                Query query5 = databaseTrains.child("t_list").orderByChild("departure");
//                Query query6 = databaseTrains.child("t_list").orderByChild("dep_arri");
//                Query query7 = databaseTrains.child("t_list").orderByChild("arrival_TIME");
//
//                query.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
//                            dataSnapshot1.getRef().setValue(null);
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        Log.e("TAG", "onCancelled", databaseError.toException());
//                    }
//                });





                //if (validInput()==true) {
//
//                    long rowId = dbHelper.insertData(name, no, holiday, DEPARTURE, d_time, ARRIVAL, a_time);
//
//                    if (rowId == -1) {
//
//                        Toast.makeText(AddNewTrain.this, "Unsuccessful to insert", Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                        Toast.makeText(AddNewTrain.this, "Inserted data in row " + rowId, Toast.LENGTH_SHORT).show();
//
//
//                        trainName.setText("");
//                        trainNo.setText("");
//                        OFFDAY.setText("");
//                        departurStation.setText("");
//                        departureTime.setText("");
//                        arrivalStation.setText("");
//                        arrivalTime.setText("");
//                    }
                }

//            }
        });


//        displayAlldata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Cursor cursor = dbHelper.displayAllData();
//
//                if (cursor.getCount()==0){
//
//                    showData("Error","No data found");
//                    return;
//                }
//                StringBuffer stringBuffer = new StringBuffer();
//
//                while (cursor.moveToNext()){
//
//
//                    stringBuffer.append("TRAIN NAME: "+cursor.getString(1)+"\n");
//                    stringBuffer.append("TRAIN NO: "+cursor.getString(2)+"\n");
//                    stringBuffer.append("OFFDAY: "+cursor.getString(3)+"\n");
//                    stringBuffer.append("DEPARTURE: "+cursor.getString(4)+"\n");
//                    stringBuffer.append("DEPARTURE TIME: "+cursor.getString(5)+"\n");
//                    stringBuffer.append("ARRIVAL: "+cursor.getString(6)+"\n");
//                    stringBuffer.append("ARRIVAL TIME: "+cursor.getString(7)+"\n\n\n");
//                }
//
//                showData("Resultset",stringBuffer.toString());
//
//            }
//        });

    }

//    public void showData(String title,String message){
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setCancelable(true);
//        builder.show();
//    }


    private void addNewTrain() {

        String name = trainName.getText().toString();
        String no = trainNo.getText().toString();
        String holiday = offday.getText().toString();
        String departure = departurStation.getText().toString();
        String d_time = departureTime.getText().toString();
        String arr = arrivalStation.getText().toString();
        String a_time = arrivalTime.getText().toString();

        String name_no = name + no;
        String dep_arr = departure + arr;
        String sub_station = departure+" "+arr;


        if (validInput() == true) {

            String id = databaseTrains.push().getKey();

            Train train = new Train(id,name,no,holiday,departure,d_time,arr,a_time,name_no,dep_arr,sub_station);

            databaseTrains.child(id).setValue(train);

            Toast.makeText(this, "Train added.", Toast.LENGTH_SHORT).show();
        }
    }




    private boolean validInput() {

        Boolean allInputValid = true;
        for (EditText input
                : new EditText[]{trainName,trainNo,offday,departurStation,departureTime,arrivalStation,arrivalTime}) {
            if (input.getText().toString().isEmpty()) {

                allInputValid = false;
                showError(input, R.string.required);
            }
        }
        return allInputValid;
    }


    private void showError(EditText field, int messageRes) {
        field.setError(getString(messageRes));
    }



}
