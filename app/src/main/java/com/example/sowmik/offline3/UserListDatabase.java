package com.example.sowmik.offline3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class UserListDatabase extends SQLiteOpenHelper {


    private Context context;

    private static final String DATABASE_NAME = "userdetails.db";
    private static final String TABLE_NAME = "user_details";
    private static final int VERSION_NUMBER = 1;

    private static final String ID = "id";
    private static final String USER_NAME = "name";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String PASSWORD = "password";
    private static final String MOBILE_NUMBER = "mobile_number";
    //private static final String travel_history;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+ID+"Integer PRIMARY KEY AUTOINCREMENT,"+USER_NAME+" VARCHAR(50) NOT NULL, "+EMAIL+" TEXT NOT NULL,"+ADDRESS+" TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL,"+MOBILE_NUMBER+" TEXT NOT NULL)";


    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL = "SELECT * FROM "+TABLE_NAME;





    public UserListDatabase(Context context ) {

        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context =  context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            Toast.makeText(context, "onCreate is called.", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);

        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {

            Toast.makeText(context, "onUpgrade is called.", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);

        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }


    public long insertData(String name,String email,String address,String password,String mobile_number){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME,name);
        contentValues.put(EMAIL,email);
        contentValues.put(ADDRESS,address);
        contentValues.put(PASSWORD,password);
        contentValues.put(MOBILE_NUMBER,mobile_number);

        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;

    }

    public Cursor checkUser(String username){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }




}
