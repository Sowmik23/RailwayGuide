package com.example.sowmik.offline3;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TrainDetails extends AppCompatActivity implements View.OnClickListener {


    //    DbHelper dbHelper;
    UserListDatabase userListDatabase;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseTrains;

    Train trains;

    ProgressDialog mprogressDialog;

    private Button datePickerButton,webViewButton;
    private DatePickerDialog datePickerDialog;

    private TextView t_Name,t_no,departure,d_time,arrival,a_time,offday,availableSeats,subStations,ifAvailable;


    String user_name,de_parture,a_rrival,journey_date, j_time ;
    static String alarm_time;


    String de = "00";
    String ar = "00";

    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //dbHelper = new DbHelper(this);
        //SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        userListDatabase = new UserListDatabase(this);
        SQLiteDatabase sqLiteDatabase1 = userListDatabase.getWritableDatabase();


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseTrains = firebaseDatabase.getReference("train_list");

        trains = new Train();


        mprogressDialog = new ProgressDialog(this);

        datePickerButton = findViewById(R.id.date_picker_id);
        webViewButton = findViewById(R.id.purchaseticketbuttonid);


        t_Name = findViewById(R.id.t_nameid);
        t_no = findViewById(R.id.t_noid);
        departure = findViewById(R.id.departure_id);
        d_time = findViewById(R.id.departure_timeid);
        arrival = findViewById(R.id.arrival_id);
        a_time = findViewById(R.id.arrival_timeid);
        offday = findViewById(R.id.offday_id);

        availableSeats = findViewById(R.id.shovonchairid);
        ifAvailable = findViewById(R.id.ifavaliableid);
        subStations = findViewById(R.id.substationid);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null)
        {
            mprogressDialog.setMessage("Loading...");
            mprogressDialog.show();

            String no = bundle.getString("t_number");

            String t_name = bundle.getString("train_name");

            user_name = bundle.getString("u_name");

            de = bundle.getString("dep");
            ar = bundle.getString("arr");

            if (!isConnected(TrainDetails.this)){

                mprogressDialog.dismiss();
                builder(TrainDetails.this).show();
            }
            else {

                if (de != "00" && ar != "00") {

                    final String de_arr = de + ar;

                    Query q1 = FirebaseDatabase.getInstance().getReference("train_list")
                            .orderByChild("dep_arri")
                            .equalTo(de_arr);

                    q1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {


                                trains = ds.getValue(Train.class);
                                String abc = trains.getDep_arri().toString();


                                if (abc.equals(de_arr)) {
                                    flag = true;
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


                if (no != null) {


                    //                Cursor cursor = dbHelper.SearchByNumber(no);
                    //                StringBuffer stringBuffer = new StringBuffer();
                    //                while (cursor.moveToNext()) {
                    //
                    //
                    //                    //stringBuffer.append("TRAIN NAME: " + cursor.getString(1) + "\n");
                    //                    t_Name.setText(""+ cursor.getString(1));
                    //
                    //                    //stringBuffer.append("TRAIN NO: " + cursor.getString(2) + "\n");
                    //                    t_no.setText("TRAIN NO: "+cursor.getString(2));
                    //
                    //                    //stringBuffer.append("OFFDAY: " + cursor.getString(3) + "\n");
                    //                    offday.setText("OFFDAY: "+cursor.getString(3));
                    //
                    //                    //stringBuffer.append("DEPARTURE: " + cursor.getString(4) + "\n");
                    //                    de_parture = cursor.getString(4);
                    //                    departure.setText("DEPARTURE: "+de_parture);
                    //
                    //                    //stringBuffer.append("DEPARTURE TIME: " + cursor.getString(5) + "\n");
                    //                    d_time.setText("DEPARTURE TIME: "+cursor.getString(5));
                    //
                    //                    //stringBuffer.append("ARRIVAL: " + cursor.getString(6) + "\n");
                    //                    a_rrival = cursor.getString(6);
                    //                    arrival.setText("ARRIVAL: "+a_rrival);
                    //
                    //                    //stringBuffer.append("ARRIVAL TIME: " + cursor.getString(7) + "\n\n\n");
                    //                    a_time.setText("ARRIVAL TIME: "+cursor.getString(7));
                    //
                    //
                    //                }


                    Query query = FirebaseDatabase.getInstance().getReference("train_list")
                            .orderByChild("no")
                            .equalTo(no);


                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {


                                trains = ds.getValue(Train.class);

                                t_Name.setText(trains.getName().toString());

                                t_no.setText("Train No: " + trains.getNo().toString());
                                departure.setText("Departure Station: " + trains.getDeparture().toString());
                                de_parture = trains.getDeparture().toString();
                                d_time.setText("Departure Time: " + trains.getDep_time().toString());


                                j_time = trains.getDep_time();
                                j_time+=":00";

                                arrival.setText("Arrival Station: " + trains.getArrival().toString());
                                a_rrival = trains.getArrival().toString();
                                a_time.setText("Arrival Time: " + trains.getArri_time().toString());
                                offday.setText("Offday: " + trains.getOffday().toString());

                                subStations.setText("Sub-stations: " + trains.getSub_station().toString());


                                if (flag == true) {

                                    ifAvailable.setText("Available Seats :");

                                    String prices = (String) trains.getPrice();
                                    String newString = prices.replaceAll("_", "\n");
                                    availableSeats.setText(newString);
                                }


                            }
                            mprogressDialog.dismiss();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    });


                } else if (t_name != null) {


                    //                Cursor cursor = dbHelper.SearchByName(name);
                    //                StringBuffer stringBuffer = new StringBuffer();
                    //                while (cursor.moveToNext()) {
                    //
                    //
                    //                    //stringBuffer.append("TRAIN NAME: " + cursor.getString(1) + "\n");
                    //                    t_Name.setText(""+ cursor.getString(1));
                    //
                    //                    //stringBuffer.append("TRAIN NO: " + cursor.getString(2) + "\n");
                    //                    t_no.setText("TRAIN NO: "+cursor.getString(2));
                    //
                    //                    //stringBuffer.append("OFFDAY: " + cursor.getString(3) + "\n");
                    //                    offday.setText("OFFDAY: "+cursor.getString(3));
                    //
                    //                    //stringBuffer.append("DEPARTURE: " + cursor.getString(4) + "\n");
                    //                    de_parture = cursor.getString(4);
                    //                    departure.setText("DEPARTURE: "+de_parture);
                    //
                    //                    //stringBuffer.append("DEPARTURE TIME: " + cursor.getString(5) + "\n");
                    //                    d_time.setText("DEPARTURE TIME: "+cursor.getString(5));
                    //
                    //                    //stringBuffer.append("ARRIVAL: " + cursor.getString(6) + "\n");
                    //                    a_rrival = cursor.getString(6);
                    //                    arrival.setText("ARRIVAL: "+a_rrival);
                    //
                    //                    //stringBuffer.append("ARRIVAL TIME: " + cursor.getString(7) + "\n\n\n");
                    //                    a_time.setText("ARRIVAL TIME: "+cursor.getString(7));
                    //
                    //
                    //                }
                    //showSearchData("Resultset", stringBuffer.toString());


                    Query query = FirebaseDatabase.getInstance().getReference("train_list")
                            .orderByChild("name")
                            .equalTo(t_name);


                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {


                                trains = ds.getValue(Train.class);

                                t_Name.setText(trains.getName().toString());

                                t_no.setText("Train No: " + trains.getNo().toString());
                                departure.setText("Departure Station: " + trains.getDeparture().toString());
                                de_parture = trains.getDeparture().toString();
                                d_time.setText("Departure Time: " + trains.getDep_time().toString());


                                j_time = trains.getDep_time();
                                j_time+=":00";

                                arrival.setText("Arrival Station: " + trains.getArrival().toString());
                                a_rrival = trains.getArrival().toString();
                                a_time.setText("Arrival Time: " + trains.getArri_time().toString());
                                offday.setText("Offday: " + trains.getOffday().toString());

                                subStations.setText("Sub-stations: " + trains.getSub_station().toString());

                                if (flag == true) {

                                    ifAvailable.setText("Available Seats :");

                                    String prices = (String) trains.getPrice();
                                    String newString = prices.replaceAll("_", "\n");
                                    availableSeats.setText(newString);
                                }

                            }
                            mprogressDialog.dismiss();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    });


                }
            }

        }



        datePickerButton.setOnClickListener(this);

        webViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TrainDetails.this,PurchaseTicketActivity.class);
                startActivity(intent);

            }
        });



    }

    @Override
    public void onClick(View v) {

        DatePicker datePicker = new DatePicker(this);

        final int currentDay = datePicker.getDayOfMonth();
        final int currentMonth = (datePicker.getMonth())+1;
        final int currentYear = datePicker.getYear();


        datePickerDialog = new DatePickerDialog(this,

                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        journey_date = dayOfMonth +"/"+ (month+1) +"/"+year;
                        System.out.println("Here comes the : "+journey_date);

                        alarm_time = journey_date+j_time;

                        Toast.makeText(TrainDetails.this, dayOfMonth +"/"+ (month+1) +"/"+year, Toast.LENGTH_SHORT).show();

                            long rowId = userListDatabase.insertUserTravelHistory(user_name,de_parture,a_rrival,journey_date);

                            if (rowId == -1) {

                                Toast.makeText(TrainDetails.this, "An error has occured.", Toast.LENGTH_SHORT).show();

                            }
                            else {



                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                long time = 0, timeInMinute = 0;
                                Date d1 = null;
                                Date d2;

                                d2 = Calendar.getInstance().getTime();

                                String dt1 = currentDay + "/" + currentMonth + "/" + currentYear +" "+j_time  ;

                                try {
                                    d1 = format.parse(dt1);

                                    System.out.println("Alarm start time: "+d1);
                                    System.out.println("Current time: "+d2);
                                    System.out.println("Hello I am checking the alarm time just. Please be polite.");

                                    time = d1.getTime() - d2.getTime();
                                    System.out.println("Time: " + time/1000 + "seconds");
                                    //System.out.println(d2.getTime() + ", " + d1.getTime() + ", " + "Time: " + time + " (ms)/ Current time: " + System.currentTimeMillis());
                                    //timeInMinute = time / (1000 * 60);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                AlarmManager a = (AlarmManager) getSystemService(ALARM_SERVICE);
                                Intent intent = new Intent(getApplicationContext(), AlarmMod_2.class);
                                PendingIntent p1 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
                                a.setExact(AlarmManager.RTC, System.currentTimeMillis() + time, p1);





                                Toast.makeText(TrainDetails.this, "Successfully saved in history.", Toast.LENGTH_SHORT).show();
                            }

                        }
                },currentYear,currentMonth,currentDay);
        datePickerDialog.show();

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

