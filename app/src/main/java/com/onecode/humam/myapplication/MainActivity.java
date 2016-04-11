package com.onecode.humam.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    list1 adapter;

    List<item> n=new ArrayList<>();

    private RecyclerView rv;
    public item news_object;
    public ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        this.rv = (RecyclerView) findViewById(R.id.rv);
        adapter=new list1(this,n);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);



        new AsyncTask<String, Integer, Document>() {
            @Override
            protected void onPreExecute() {
                //  bar.setVisibility(View.VISIBLE);
                super.onPreExecute();
                Toast.makeText(MainActivity.this, "Start Loading Data", Toast.LENGTH_SHORT).show();

            }

            @Override
            protected Document doInBackground(String... params) {
                Document doc = null;
                try {
                    doc = Jsoup.connect("http://www.non14.net/ملف-الأخبار/").get();


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, " not work", Toast.LENGTH_SHORT).show();
                }

                return doc;

            }

            @Override
            protected void onPostExecute(Document document) {
                //  bar.setVisibility(View.GONE);

                super.onPostExecute(document);
                Toast.makeText(MainActivity.this, "finish Loading Data", Toast.LENGTH_SHORT).show();
                Elements news = document.select("div.contentcolumn");
                Elements title = news.select("div.catitems");
                Log.d("MainActivity", "This is news = " + title);

                for (Element el : title) {
                    news_object = new item();
                    news_object.setTitle(el.select("a").text());
                    news_object.setauther(el.select("a").attr("abs:href"));
                    //  news_object.setDate(el.select("time").text());
                    // news_object.setDesc(el.select("div.cb-excerpt").select("p").text());
                    news_object.setimg(el.select("img").attr("abs:src"));
                    //  news_object.setNp_views(el.select("div.cb-post-views.cb-byline-element").text());
                    //news_object.setAuther(el.select("div.cb-author.cb-byline-element").text());
                    Log.d("MainActivity", "This is news = " + news_object.getauther());


                    //Intent i=new Intent(getApplicationContext(),list1.class);

                    //ADD DATA TO OUR INTENT
                    //i.putExtra("Name", news_object.gettitle());


                    n.add(news_object);
                    adapter.notifyDataSetChanged();



//                    text.append("\n " + " " + el.select("h2").text());
//                    text.append("\n  " + el.select("img").attr("src"));
//                    text.append("\n " + el.select("time").text());
//                    text.append("\n " + el.select("div.cb-author.cb-byline-element").text());
//                    text.append("\n " + el.select("div.cb-post-views.cb-byline-element").text());
//                    text.append("\n " + el.select("div.cb-excerpt").select("p").text());
                }

            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
