package com.example.yoelfebryan.feedme.CRUD;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.Model.Cart;
import com.example.yoelfebryan.feedme.Model.CartListAdapter;
import com.example.yoelfebryan.feedme.Model.Item;
import com.example.yoelfebryan.feedme.Model.ItemListAdapter;
import com.example.yoelfebryan.feedme.R;

import java.util.ArrayList;


/**
 * Created by Yoel Febryan on 29/03/2018.
 */

public class CartListActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper databaseHelper;
    ListView gridView;
    ArrayList<Cart> list;
    CartListAdapter adapter = null;
    String idd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_activity);

        databaseHelper = new DatabaseHelper(this);
        gridView = (ListView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        adapter = new CartListAdapter(this, R.layout.list_cart, list);
        gridView.setAdapter(adapter);

        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        cursor = database.rawQuery("SELECT * FROM seller WHERE nama_toko = '" + getIntent().getStringExtra("user") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            int id = cursor.getInt(0);
            idd = String.valueOf(id);
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM cart WHERE id_seller = '" + idd + "'",null);
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String harga = cursor.getString(2);
            String desc = cursor.getString(3);
            String qty = cursor.getString(5);
            String total = cursor.getString(6);
            String keterangan = cursor.getString(7);

            list.add(new Cart(id, name, harga, desc, qty, total, keterangan));
            adapter.notifyDataSetChanged();
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CartListActivity.this,StatusItemActivity.class);
                intent.putExtra("id",String.valueOf(id));
                startActivity(intent);
            }
        });
    }
}
