package com.example.esraa.movieapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.esraa.movieapp.Models.Movies;
import com.example.esraa.movieapp.Models.Results;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SerializejasonObjectByUrlconnectionActivity extends AppCompatActivity {
    TextView tvDisplay;
    Button btnClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializejason_object_by_urlconnection);
        btnClick=(Button)findViewById(R.id.clickobj);
        tvDisplay=(TextView) findViewById(R.id.displayobj);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new JSONTask().execute("https://api.themoviedb.org/3/movie/popular?api_key=cb8effcf3a0b27a05a7daba0064a32e1&language=en-US&page=1");

            }

        });
    }
    public class JSONTask extends AsyncTask<String,String,String>
    {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                //native java

                String finalJson = buffer.toString();
                JSONObject parentObject=new JSONObject(finalJson);
                JSONArray parentArray=parentObject.getJSONArray("results");
               StringBuffer finalDataBuffer= new StringBuffer();

                for(int i=0 ;i<parentArray.length();i++){
                    JSONObject finalObject= parentArray.getJSONObject(i);
                    Movies m=new Movies();

                    Results results=new Results();
                    results.setTitle(finalObject.getString("title"));



                    finalDataBuffer.append(results.getTitle()+"\n");


                }




                return finalDataBuffer.toString() ;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;


        }

        @Override
        protected void onPostExecute(final String result) {
            super.onPostExecute(result);
            tvDisplay.setText(result);


        }


    }
}
