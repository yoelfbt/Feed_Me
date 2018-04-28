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
    private static final int DATABASE_VERSION = 4;
    public DatabaseHelper (Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb1 = "create table customer(id integer primary key autoincrement,nama text null,nohp text null,alamat text null,password text null);";
        String tb2 = "create table seller(id_toko integer primary key autoincrement,nama_toko text null,nohp_toko text null,alamat_toko text null,password_toko text null);";
        String tb3 = "create table item(id_item integer primary key autoincrement, name text null, harga integer null, desc text null, image blob, id_seller integer, foreign key(id_seller) references seller(id_toko))";
        String tb4 = "create table cart(id_cart integer primary key autoincrement, name text null, harga integer null, desc text null,image blob, qty integer null,total integer null,keterangan text null,alamat text null, nohp text null, id_customer integer, id_seller integer, foreign key(alamat) references customer(alamat), foreign key(nohp) references customer(nohp), foreign key(id_customer) references customer(id), foreign key(id_seller) references seller(id_toko))";

        db.execSQL(tb1);
        db.execSQL(tb2);
        db.execSQL(tb3);
        db.execSQL(tb4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String tb1 = "drop table if exists customer";
        String tb2 = "drop table if exists seller";
        String tb3 = "drop table if exists item";
        String tb4 = "drop table if exists cart";

        db.execSQL(tb3);
        db.execSQL(tb1);
        db.execSQL(tb2);
        db.execSQL(tb4);

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
