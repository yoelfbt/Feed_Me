package com.example.yoelfebryan.feedme.CRUD;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.Model.Item;
import com.example.yoelfebryan.feedme.Model.ItemListAdapter;
import com.example.yoelfebryan.feedme.R;

import java.util.ArrayList;


/**
 * Created by Yoel Febryan on 29/03/2018.
 */

public class ItemListSellerActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper databaseHelper;
    ListView gridView;
    ArrayList<Item> list;
    ItemListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity);

        databaseHelper = new DatabaseHelper(this);
        gridView = (ListView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        adapter = new ItemListAdapter(this, R.layout.list_item, list);
        gridView.setAdapter(adapter);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM item WHERE id_seller = '" + getIntent().getStringExtra("id") + "'",null);
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            final String name = cursor.getString(1);
            String harga = cursor.getString(2);
            String desc = cursor.getString(3);
            final byte[] image = cursor.getBlob(4);

            list.add(new Item(id, name, harga, desc, image));

            adapter.notifyDataSetChanged();
        }
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ItemListSellerActivity.this, UpdateItemActivity.class);
                    intent.putExtra("id",String.valueOf(id));
                    startActivity(intent);
                }
            });
    }
}
