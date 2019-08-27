package com.example.esraa.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class OkhttpActivity extends AppCompatActivity {
     TextView tvokhttp;
    Button btnClick;
    String url;
    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);


        tvokhttp = findViewById(R.id.okhttpdisplay);
        btnClick=(Button)findViewById(R.id.click);

         client = new OkHttpClient();

         url = "https://api.themoviedb.org/3/movie/550?api_key=cb8effcf3a0b27a05a7daba0064a32e1";

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            final String myResponse = response.body().string();


                            OkhttpActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvokhttp.setText(myResponse);

                                }
                            });


                        }
                    }
                });

            }
        });


    }
}
