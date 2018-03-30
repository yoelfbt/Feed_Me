package com.example.yoelfebryan.feedme.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yoel Febryan on 27/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "FeedMe.db";
    private static final int DATABASE_VERSION = 3;
    public DatabaseHelper (Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb1 = "create table customer(id integer primary key autoincrement,nama text null,nohp text null,alamat text null,password text null);";
        String tb2 = "create table seller(id_toko integer primary key autoincrement,nama_toko text null,nohp_toko text null,alamat_toko text null,password_toko text null);";
        String tb3 = "create table item(id_item integer primary key autoincrement, name text null, harga integer null, desc text null, image blog, id_seller integer, foreign key(id_seller) references seller(id_toko))";

        db.execSQL(tb1);
        db.execSQL(tb2);
        db.execSQL(tb3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tb1 = "drop table if exists customer";
        String tb2 = "drop table if exists seller";
        String tb3 = "drop table if exists item";

        db.execSQL(tb3);
        db.execSQL(tb1);
        db.execSQL(tb2);

        onCreate(db);
    }

    //cek nama apakah sudah pernah dipakai
    public Boolean checknama(String nama){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from customer where nama=?",new String[]{nama});
        if (cursor.getCount()>0) return false;
        else return true;
    }
    public Boolean checknama2(String nama_toko){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from seller where nama_toko=?",new String[]{nama_toko});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    //cek id dan password di database
    public Boolean namapass(String nama,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from customer where nama=? and password=?",new String[]{nama,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
    public Boolean namapass2(String nama,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from seller where nama_toko=? and password_toko=?",new String[]{nama,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
