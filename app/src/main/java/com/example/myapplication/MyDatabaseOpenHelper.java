package com.example.myapplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String tableName = "times";

    public static int DATABASE_VERSION =1;

    public MyDatabaseOpenHelper(@Nullable Context context) {
        //db이름(name)은 alarm , factory는 null
        super(context, "alarm", null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //앱 설치후 한번만 작동!!!!
        String createSQL = "CREATE TABLE times (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hour TEXT," +
                "minute TEXT," +
                "memo TEXT);";

        //칼럼은 _id,hour,minute,memo
        db.execSQL(createSQL);

        //디폴트 시간들
        db.execSQL("insert into times (hour,minute) values(8,0),(12,30),(18,0)");

        //검색 내역 db
        db.execSQL("CREATE TABLE prescription (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "picture BLOB," +
                "taking TEXT," +
                "pills TEXT," +
                "date TEXT,"+
                "due TEXT,"+
                "warning TEXT,"+
                "detail TEXT"+
                ");");

        db.execSQL("insert into prescription (taking,pills,date,due,warning) values(?,?,?,?,?)",new String[]{
        "복용중","항히스타민제","2020-09-11","2020-09-12","안전"});


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table times");
            onCreate(db);
        }

    }





}
