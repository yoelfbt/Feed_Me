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
import android.os.Parcelable;
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
import com.example.yoelfebryan.feedme.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddItemAcitivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    protected Cursor cursor;
    Button add,update,list;
    ImageView img;
    TextView ID;
    EditText ed1,ed2,ed3;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_acitivity);

        add = (Button) findViewById(R.id.addimage);
        update = (Button) findViewById(R.id.button1);
        list = (Button) findViewById(R.id.list);
        img = (ImageView) findViewById(R.id.image);
        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ID = (EditText) findViewById(R.id.id);
        ID.setVisibility(View.INVISIBLE);
        databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM seller where nama_toko = '" + getIntent().getStringExtra("user") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            ID.setText(cursor.getString(0).toString());
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions
                        (AddItemAcitivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemAcitivity.this, ItemListActivity.class);
                intent.putExtra("id", String.valueOf(ID));
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.execSQL("insert into item(name,harga,desc,image,id_seller) values('" +
                        ed1.getText().toString() + "','" +
                        ed2.getText().toString() + "','" +
                        ed3.getText().toString() + "','" +
                        imageViewToByte(img) + "','" +
                        ID.getText().toString() + "')");
                Toast.makeText(getApplicationContext(),"Add item Berhasil",Toast.LENGTH_SHORT).show();
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                img.setImageResource(R.mipmap.ic_launcher);
            }
        });
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
