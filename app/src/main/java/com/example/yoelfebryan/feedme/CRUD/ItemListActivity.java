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
import android.widget.Toast;

import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.Model.Item;
import com.example.yoelfebryan.feedme.Model.ItemListAdapter;
import com.example.yoelfebryan.feedme.R;

import java.util.ArrayList;

/**
 * Created by Yoel Febryan on 29/03/2018.
 */

public class ItemListActivity extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper databaseHelper;
    GridView gridView;
    ArrayList<Item> list;
    ItemListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list_activity);

        databaseHelper = new DatabaseHelper(this);
        gridView = (GridView) findViewById(R.id.gridview);
        list = new ArrayList<>();
        adapter = new ItemListAdapter(this, R.layout.list_item, list);
        gridView.setAdapter(adapter);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM item",null);
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String harga = cursor.getString(2);
            String desc = cursor.getString(3);
            byte[] image = cursor.getBlob(4);

            list.add(new Item(id,name,harga,desc,image));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ItemListActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            SQLiteDatabase db = databaseHelper.getReadableDatabase();
                            Cursor c = db.rawQuery("SELECT id_item from item",null);
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            Intent intent = new Intent(ItemListActivity.this,UpdateItemActivity.class);
                            intent.putExtra("id",arrID);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
}
