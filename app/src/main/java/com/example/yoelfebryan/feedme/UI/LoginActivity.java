package com.example.yoelfebryan.feedme.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoelfebryan.feedme.CRUD.RegisterActivity;
import com.example.yoelfebryan.feedme.DBHelper.DatabaseHelper;
import com.example.yoelfebryan.feedme.R;

public class LoginActivity extends AppCompatActivity {
    TextView registrasi;
    EditText nama,password;
    Button login;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);
        registrasi = (TextView) findViewById(R.id.regis);
        nama = (EditText)findViewById(R.id.nama);
        password = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.btnlogin);

        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nama = nama.getText().toString();
                String Pass = password.getText().toString();
                Boolean cekdb = databaseHelper.namapass(Nama,Pass);
                Boolean cekdb2 = databaseHelper.namapass2(Nama,Pass);
                if (cekdb==true){
                    Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                    intent.putExtra("nama",Nama);
                    startActivity(intent);
                }else if (cekdb2==true){
                    Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MenuSellerActivity.class);
                    intent.putExtra("nama",Nama);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
