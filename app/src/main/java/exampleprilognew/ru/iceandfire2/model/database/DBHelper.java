package exampleprilognew.ru.iceandfire2.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB";
    public static final String BOOKS ="books";
    private static final String KEY_ID="_ID";
    protected static final String KEY_NAME="name";
    protected static final String KEY_NUMBER_OF_PAGES ="numberOfPages";
    protected static final String KEY_RELEASED ="released";
    protected static final String KEY_CHARACTERS ="characters";
    protected static final String KEY_LISTCHARACTERS ="listCharacters";
    protected static final String KEY_AUTHORS ="authors";
    protected static final String KEY_PUBLISHER ="publisher";
    protected static final String KEY_COUNTRY ="country";
    protected static final String KEY_MEDIATYPE ="mediaType";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ BOOKS +
                "("+
                KEY_ID + " integer primary key,"+
                        KEY_NAME +" text,"+
                KEY_NUMBER_OF_PAGES +" integer,"+
                KEY_RELEASED +" text,"+
                KEY_CHARACTERS +" integer,"+
                KEY_LISTCHARACTERS +" text,"+
                KEY_AUTHORS +" text,"+
                KEY_PUBLISHER +" text,"+
                KEY_COUNTRY +" text,"+
                KEY_MEDIATYPE +" text"+
                ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ BOOKS);

        onCreate(db);
    }
}
