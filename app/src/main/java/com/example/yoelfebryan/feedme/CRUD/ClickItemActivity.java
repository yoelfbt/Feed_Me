package com.example.yoelfebryan.feedme.CRUD;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
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
import com.example.yoelfebryan.feedme.R;
import com.example.yoelfebryan.feedme.UI.MenuActivity;

public class ClickItemActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    protected Cursor cursor;
    Button add;
    ImageView img;
    TextView ed1,ed2,ed3,ed;
    EditText qty;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_item);

        add = (Button) findViewById(R.id.button1);
        img = (ImageView) findViewById(R.id.image);
        ed = (EditText) findViewById(R.id.editText);
        ed1 = (TextView) findViewById(R.id.editText1);
        ed2 = (TextView) findViewById(R.id.editText2);
        ed3 = (TextView) findViewById(R.id.editText3);
        qty = (EditText) findViewById(R.id.editText4);
        databaseHelper = new DatabaseHelper(this);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM item WHERE id_item = '" + getIntent().getStringExtra("id") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            ed1.setText(cursor.getString(1));
            ed2.setText(cursor.getString(2));
            ed3.setText(cursor.getString(3));
            ed.setText(getIntent().getStringExtra("alamat"));

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a1 = qty.getText().toString();
                    String a2 = String.valueOf(ed2.getText());
                    int b1 = Integer.parseInt(a1);
                    int b2 = Integer.parseInt(a2);
                    final int total = b2 * b1;

                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    db.execSQL("insert into cart (name,harga,desc,qty,total,alamat,nohp,id_customer,id_seller) values('" +
                            cursor.getString(1) + "','" +
                            cursor.getString(2) + "','" +
                            cursor.getString(3) + "','" +
                            qty.getText().toString() + "','" +
                            total +"','" +
                            ed.getText().toString() + "','" +
                            getIntent().getStringExtra("nohp") + "','" +
                            getIntent().getStringExtra("id_cust") + "','" +
                            cursor.getInt(5) + "')");
                    Toast.makeText(getApplicationContext(), "Shop Success", Toast.LENGTH_SHORT).show();
                    makenotifitication();
                    Intent intent = new Intent(ClickItemActivity.this,DoneActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    public void makenotifitication(){
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setContentTitle("FeedMe")
                .setContentText("Anda memiliki pesanan baru");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}
