package com.example.sowmik.offline3;

import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class UserListDatabase extends SQLiteOpenHelper {


    private Context context;

    private static final String DATABASE_NAME = " userdetails.db ";
    private static final String TABLE_NAME = " user_details ";
    private static final int VERSION_NUMBER = 2;

    private static final String ID = "id";
    private static final String USER_NAME = "name";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String PASSWORD = "password";
    private static final String MOBILE_NUMBER = "mobile_number";
    //private static final String travel_history;

    private static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + USER_NAME + " VARCHAR(50) NOT NULL , " + EMAIL + " TEXT NOT NULL ,"+ADDRESS+" TEXT NOT NULL ," + PASSWORD + " TEXT NOT NULL ," + MOBILE_NUMBER + " TEXT NOT NULL )";


    private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String SELECT_ALL = " SELECT * FROM " + TABLE_NAME;





    public UserListDatabase(Context context ) {

        super(context, DATABASE_NAME , null, VERSION_NUMBER);
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


    public long insertData(UserDetails userDetails){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(ADDRESS,userDetails.getAddress());
        contentValues.put(PASSWORD,userDetails.getPassword());
        contentValues.put(MOBILE_NUMBER,userDetails.getMobile_number());

        long rowId = sqLiteDatabase.insert(TABLE_NAME ,null,contentValues);
        return rowId;

    }

    public Boolean findPassword(String uname,String pas){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME ,null);

        Boolean result = false;

        if (cursor.getCount()==0)
        {
            Toast.makeText(context, "No data found.", Toast.LENGTH_SHORT).show();
        }
        else {

            while (cursor.moveToNext()){
                String username = cursor.getString(1);
                String password = cursor.getString(4);

                if (username.equals(uname) && password.equals(pas)){

                    result = true;
                    break;
                }
            }

        }

        return result;
    }


}
