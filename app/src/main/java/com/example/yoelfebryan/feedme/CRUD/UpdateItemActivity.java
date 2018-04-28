package com.example.yoelfebryan.feedme.CRUD;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.Model.Item;
import com.example.yoelfebryan.feedme.Model.ItemListAdapter;
import com.example.yoelfebryan.feedme.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class UpdateItemActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    protected Cursor cursor;
    Button add,update,list,delete;
    ImageView img;
    EditText ed1,ed2,ed3;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        add = (Button) findViewById(R.id.addimage);
        update = (Button) findViewById(R.id.button1);
        delete = (Button)findViewById(R.id.button2);
        list = (Button) findViewById(R.id.list);
        img = (ImageView) findViewById(R.id.image);
        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM item WHERE id_item = '" + getIntent().getStringExtra("id") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            ed1.setText(cursor.getString(1));
            ed2.setText(cursor.getString(2));
            ed3.setText(cursor.getString(3));
            final int id_seller = cursor.getInt(5);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    db.execSQL("update item set name='" +
                            ed1.getText().toString() + "', harga='" +
                            ed2.getText().toString() + "', desc='" +
                            ed3.getText().toString() + "', id_seller='" +
                            cursor.getInt(5) + "' where id_item='" +
                            cursor.getString(0) + "'");
                    Toast.makeText(getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = databaseHelper.getReadableDatabase();
                    cursor = db.rawQuery("DELETE FROM item where id_item = '" + getIntent().getStringExtra("id") + "'", null);
                    cursor.moveToFirst();
                    Toast.makeText(getApplicationContext(), "Deleted Succes", Toast.LENGTH_SHORT).show();
                }
            });


            list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UpdateItemActivity.this, ItemListSellerActivity.class);
                    intent.putExtra("id",String.valueOf(id_seller));
                    startActivity(intent);
                }
            });
        }
    }

    private byte[] imageViewToByte(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else {
                Toast.makeText(getApplicationContext(),"You dont have permission to access file location!",Toast.LENGTH_SHORT).show();
            }return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
