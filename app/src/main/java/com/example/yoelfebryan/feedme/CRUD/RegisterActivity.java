package com.example.yoelfebryan.feedme.CRUD;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.R;
import com.example.yoelfebryan.feedme.UI.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    Button btnregis, btnlogin,btnregistoko,btnlogintoko;
    EditText nama, nohp, alamat, id, password, cpassword, namatoko, idtoko,nohptoko,alamattoko, passwordtoko, cpasstoko;
    TabHost tab;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        tab = (TabHost)findViewById(R.id.tabhost);
        btnregis =(Button) findViewById(R.id.btnregis);
        btnregistoko = (Button) findViewById(R.id.btnregisseller);
        btnlogin =(Button) findViewById(R.id.btnlogin);
        btnlogintoko = (Button) findViewById(R.id.btnloginseller);
        alamat = (EditText) findViewById(R.id.alamat);
        alamattoko = (EditText) findViewById(R.id.alamatseller);
        nama =(EditText) findViewById(R.id.nama);
        namatoko = (EditText) findViewById(R.id.namaseller);
        nohp =(EditText) findViewById(R.id.nohp);
        nohptoko = (EditText)findViewById(R.id.nohpseller);
        password =(EditText) findViewById(R.id.password);
        passwordtoko = (EditText) findViewById(R.id.passwordseller);
        cpassword =(EditText) findViewById(R.id.cpassword);
        cpasstoko = (EditText)findViewById(R.id.cpasswordseller);

        tab.setup();
        TabHost.TabSpec spec1 = tab.newTabSpec("Tab 1");
        spec1.setIndicator("Customer");
        spec1.setContent(R.id.tab1);
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("Tab 2");
        spec2.setIndicator("Seller");
        spec2.setContent(R.id.tab2);
        tab.addTab(spec2);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnlogintoko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(cpassword.getText().toString())) {
                    Boolean checkid = databaseHelper.checknama(nama.getText().toString());
                    if (checkid==true){
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    db.execSQL("insert into customer(nama,nohp,alamat,password) values('" +
                            nama.getText().toString() + "','" +
                            nohp.getText().toString() + "','" +
                            alamat.getText().toString() + "','" +
                            password.getText().toString() + "')");
                            cpassword.getText().toString();
                    Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
                }else {
                        Toast.makeText(getApplicationContext(), "Nama Sudah Dipakai, ganti dengan Nama Lain", Toast.LENGTH_SHORT).show();
                    }
                }Toast.makeText(getApplicationContext(),"Password tidak sama",Toast.LENGTH_SHORT).show();
            }
        });
        btnregistoko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordtoko.getText().toString().equals(cpasstoko.getText().toString())) {
                    Boolean checkid = databaseHelper.checknama2(namatoko.getText().toString());
                    if (checkid==true){
                    SQLiteDatabase db = databaseHelper.getWritableDatabase();
                    db.execSQL("insert into seller(nama_toko,nohp_toko,alamat_toko,password_toko) values('" +
                            namatoko.getText().toString() + "','" +
                            nohptoko.getText().toString() + "','" +
                            alamattoko.getText().toString() + "','" +
                            passwordtoko.getText().toString() + "')" );
                            cpasstoko.getText().toString();
                    Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
                }else {
                        Toast.makeText(getApplicationContext(), "Nama Sudah Dipakai, ganti dengan Nama Lain", Toast.LENGTH_SHORT).show();
                    }
                }Toast.makeText(getApplicationContext(),"Password tidak sama",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
