package com.example.yoelfebryan.feedme.CRUD;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.Model.Cart;
import com.example.yoelfebryan.feedme.Model.CartListAdapter;
import com.example.yoelfebryan.feedme.R;
import com.example.yoelfebryan.feedme.UI.MenuSellerActivity;

import java.util.ArrayList;

public class StatusItemActivity extends AppCompatActivity {
    protected Cursor cursor;
    Button update;
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    EditText status;
    ImageView phonecall;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_item);

        update = (Button) findViewById(R.id.button1);
        tv4 = (TextView) findViewById(R.id.image);
        tv5 = (TextView) findViewById(R.id.editText);
        tv1 = (TextView) findViewById(R.id.editText1);
        tv2 = (TextView) findViewById(R.id.editText2);
        tv3 = (TextView) findViewById(R.id.editText3);
        tv6 = (TextView)findViewById(R.id.textView11);
        phonecall = (ImageView)findViewById(R.id.imageView);
        status = (EditText) findViewById(R.id.editText4);
        databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM cart WHERE id_cart = '"+ getIntent().getStringExtra("id") +"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            tv1.setText(cursor.getString(1));
            tv2.setText(cursor.getString(2));
            tv3.setText(cursor.getString(3));
            tv4.setText(cursor.getString(5));
            tv5.setText(cursor.getString(6));
            tv6.setText(cursor.getString(9));
        }

        phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tv6.getText()));
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase  db = databaseHelper.getWritableDatabase();
                db.execSQL("update cart set keterangan='" +
                        status.getText().toString() + "' where id_cart='" +
                        cursor.getString(0) + "'");
                Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                makenotifitication();
            }
        });
    }

    public void makenotifitication(){
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setContentTitle("FeedMe")
                .setContentText("Anda memiliki notifikasi updatean Transaksi");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}
