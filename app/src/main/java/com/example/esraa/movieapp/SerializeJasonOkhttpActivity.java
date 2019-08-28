package com.example.esraa.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.esraa.movieapp.Models.Movies;
import com.example.esraa.movieapp.Models.Results;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SerializeJasonOkhttpActivity extends AppCompatActivity {
    TextView tvOkhttpObj;
    Button btnClickOkhttpObj;
    String url;
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialize_jason_okhttp);

        tvOkhttpObj = findViewById(R.id.displayobjokhttp);
        btnClickOkhttpObj=(Button)findViewById(R.id.clickobjokhttp);

        client = new OkHttpClient();

        url = "https://api.themoviedb.org/3/movie/popular?api_key=cb8effcf3a0b27a05a7daba0064a32e1&language=en-US&page=1";
        btnClickOkhttpObj.setOnClickListener(new View.OnClickListener() {
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
                            final StringBuffer finalDataBuffer= new StringBuffer();
                             String myResponse = response.body().string();
                             //Gason
                            Log.i("resp",myResponse);
                             final Movies movies = new Gson().fromJson(myResponse, Movies.class);

                            Log.i("mmmmm",movies.toString());
                            for(int i=0;i<movies.getResults().length;i++) {
                                finalDataBuffer.append(movies.getResults()[i].getTitle());
                            }
                            SerializeJasonOkhttpActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvOkhttpObj.setText(finalDataBuffer.toString()+"\n");

                                }
                            });


                        }
                    }
                });

            }
        });




    }
}
