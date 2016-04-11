package com.onecode.humam.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class newsdetail extends AppCompatActivity {
    String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        //bar = (ProgressBar) this.findViewById(R.id.marker_progress);
        Intent i=getIntent();


        final String url=i.getExtras().getString("url");
        Log.d("newsdetail", "this" + url);





        new AsyncTask<String, Integer, Document>() {
            @Override
            protected void onPreExecute() {
                //  bar.setVisibility(View.VISIBLE);
                super.onPreExecute();
                Toast.makeText(newsdetail.this, "Start Loading Data", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected Document doInBackground(String... params) {
                Document dooc = null;
                try {
                    dooc = Jsoup.connect(url).get();


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(newsdetail.this, " not work", Toast.LENGTH_SHORT).show();
                }

                return dooc;

            }

            @Override
            protected void onPostExecute(Document document) {
                //bar.setVisibility(View.GONE);

                Intent i=getIntent();
                name=i.getExtras().getString("Name");
                TextView title=(TextView)findViewById(R.id.textView2);
                title.setText(name);
                super.onPostExecute(document);
                Log.d("newsdetail","this "+document);






            }
        }.execute();



        // Log.d("newsdetail","this"+dooc);









    }

}
