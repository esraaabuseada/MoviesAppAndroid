package com.example.esraa.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ButtonsActivity extends AppCompatActivity {
    Button btnUrlConnection;
    Button btnOkhttp;
    Button btnSerializeUrlConnection;
    Button btnSerializeOkhttp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);


        btnUrlConnection=(Button)findViewById(R.id.urlConnection);
        btnOkhttp=(Button)findViewById(R.id.okhttp);
        btnSerializeUrlConnection=(Button)findViewById(R.id.serializeUrlConnection);
        btnSerializeOkhttp=(Button)findViewById(R.id.serializeOkhttp);



        btnUrlConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonsActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        btnOkhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonsActivity.this, OkhttpActivity.class);

                startActivity(intent);
            }
        });
        btnSerializeUrlConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonsActivity.this, SerializejasonObjectByUrlconnectionActivity.class);

                startActivity(intent);
            }
        });

        btnSerializeOkhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonsActivity.this, SerializeJasonOkhttpActivity.class);

                startActivity(intent);
            }
        });
    }
}
