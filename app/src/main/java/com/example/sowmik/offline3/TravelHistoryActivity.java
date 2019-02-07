package com.example.sowmik.offline3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TravelHistoryActivity extends AppCompatActivity {

    DbHelper dbHelper;
    UserListDatabase userListDatabase;

    List<String> userHistories = new ArrayList<>();

    private ListView listView;

    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_history);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbHelper = new DbHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


        userListDatabase = new UserListDatabase(this);
        SQLiteDatabase sqLiteDatabase1 = userListDatabase.getWritableDatabase();


        listView = findViewById(R.id.historylistid);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {

            user_name = bundle.getString("name");
        }

        Cursor cursor = userListDatabase.SearchUserHistory(user_name);

        if (cursor.getCount()==0){

            userHistories.add("No history found.");
        }
        else {

            StringBuffer stringBuffer = new StringBuffer();

            while (cursor.moveToNext()) {


                userHistories.add(cursor.getString(2) + " - " + cursor.getString(3) + ", " + cursor.getString(4));

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userHistories);
        listView.setAdapter(adapter);

    }
}
