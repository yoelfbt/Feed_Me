package com.example.yoelfebryan.feedme.CRUD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yoelfebryan.feedme.R;
import com.example.yoelfebryan.feedme.UI.MenuActivity;

public class DoneActivity extends AppCompatActivity {
    Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        menu = (Button) findViewById(R.id.button);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
