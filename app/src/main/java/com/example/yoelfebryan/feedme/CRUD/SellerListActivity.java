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
import com.example.yoelfebryan.feedme.Model.Item;
import com.example.yoelfebryan.feedme.Model.ItemListAdapter;
import com.example.yoelfebryan.feedme.Model.Seller;
import com.example.yoelfebryan.feedme.Model.SellerListAdapter;
import com.example.yoelfebryan.feedme.R;

import java.util.ArrayList;

/**
 * Created by Yoel Febryan on 29/03/2018.
 */

public class SellerListActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper databaseHelper;
    ListView gridView;
    ArrayList<Seller> list;
    SellerListAdapter adapter = null;
    String id_cust,address,nohp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_list_activity);

        databaseHelper = new DatabaseHelper(this);
        gridView = (ListView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        adapter = new SellerListAdapter(this, R.layout.list_seller, list);
        gridView.setAdapter(adapter);

            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM seller", null);
            list.clear();
            while (cursor.moveToNext()) {
                int idd = cursor.getInt(0);
                String name = cursor.getString(1);
                String nohp = cursor.getString(2);
                String alamat = cursor.getString(3);

                list.add(new Seller(idd, name, nohp, alamat));

                adapter.notifyDataSetChanged();
            }
                SQLiteDatabase database = databaseHelper.getReadableDatabase();
                cursor = database.rawQuery("SELECT * FROM customer Where nama = '" + getIntent().getStringExtra("user") + "'", null);
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    int id = cursor.getInt(0);
                    String addr = cursor.getString(3);
                    String phone = cursor.getString(2);
                    nohp = phone;
                    address = addr;
                    id_cust = String.valueOf(id);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(SellerListActivity.this, ItemListActivity.class);
                        intent.putExtra("id", String.valueOf(id));
                        intent.putExtra("id_cust",id_cust);
                        intent.putExtra("alamat",address);
                        intent.putExtra("nohp",nohp);
                        startActivity(intent);
                    }
                });
            }
        }
    }
