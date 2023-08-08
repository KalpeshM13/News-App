package com.kalpesh.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.kalpesh.newsapp.Models.NewsApiResponse;
import com.kalpesh.newsapp.Models.NewsHeadlines;
import com.example.newsapp.R;
import com.google.android.filament.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener, SelectListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b8;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Daily News");

//        toolbar = findViewById(R.id.toolbar);

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_6);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_7);
        b8 = findViewById(R.id.btn_8);
        b6.setOnClickListener(this);
        b8.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent3 = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent3);
            }
        });


        dialog = new ProgressDialog(this);
//        dialog.setContentView(R.layout.progressDialog);
        dialog.setTitle("Getting News Articles");
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            }
            else
            {
                showNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"Check your internet connection!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this , list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        String url = new NewsHeadlines().getUrl();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @Override
    public void onLongClick(NewsHeadlines headlines, CardView cardView) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

//    private void shareNews() {
//        String newsUrl = new NewsHeadlines().getUrl();
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT, "News from Kalpesh's App :) " + newsUrl + " 🙌");
//        Intent.createChooser(intent, "Share News using...");
//        startActivity(intent);
//    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setMessage("Do you really want to Exit?")
                .setCancelable(false)
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(android.view.View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        dialog.setTitle("Getting News Articles of " + category);
        dialog.setMessage("This depends on your internet connection");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category, null);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
