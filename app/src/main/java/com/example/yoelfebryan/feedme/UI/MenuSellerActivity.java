package com.example.yoelfebryan.feedme.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.yoelfebryan.feedme.CRUD.AddItemAcitivity;
import com.example.yoelfebryan.feedme.CRUD.LihatPSellerActivity;
import com.example.yoelfebryan.feedme.CRUD.LihatProfileActivity;
import com.example.yoelfebryan.feedme.CRUD.UpdatePSellerActivity;
import com.example.yoelfebryan.feedme.CRUD.UpdateProfileActivity;
import com.example.yoelfebryan.feedme.R;

public class MenuSellerActivity extends AppCompatActivity {
    CardView Profile,Item;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_seller);

        Profile = (CardView)findViewById(R.id.profile);
        Item = (CardView) findViewById(R.id.item);

        user = getIntent().getStringExtra("nama");

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] dialogitem = {"Lihat Profile", "Update Profile"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuSellerActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), LihatPSellerActivity.class);
                                intent.putExtra("user",user);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getApplicationContext(), UpdatePSellerActivity.class);
                                intent1.putExtra("user",user);
                                startActivity(intent1);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSellerActivity.this, AddItemAcitivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
    }
}
