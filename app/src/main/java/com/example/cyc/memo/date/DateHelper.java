package com.example.cyc.memo.date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cyc on 17-9-15.
 */

public class DateHelper extends SQLiteOpenHelper {

    private static String dateName="data1.db";
    private static int Date_VERSION=1;
    public static final String DATE_FORMAT="YYYY-MM-DD HH:MM:SS";
    public static final String CREATE_MESSAGE="Create table memo_date (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "CREATE_TIME STRING," +
            "TITLE TEXT," +
            "CONTENT TEXT," +
            "CLOCK_IS BOOLEAN," +
            "CLOCK_TIME TEXT," +
            "MUSIC_NAME TEXT," +
            "EDIT_LAST TEXT)";
    public DateHelper(Context context){
        super(context,dateName,null,Date_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table memo_data (_id integer primary key autoincrement," +
                "create_time text," +
                        "title text," +
                        "content text," +
                        "clock_is text," +
                        "clock_time text," +
                        "music_name text," +
                        "edit_last text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
