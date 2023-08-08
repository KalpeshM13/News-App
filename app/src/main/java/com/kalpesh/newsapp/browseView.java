package com.kalpesh.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kalpesh.newsapp.Models.NewsHeadlines;

public class browseView extends AppCompatActivity {

    WebView webView;
    FloatingActionButton share;
    Button viewChrome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_view);
        getSupportActionBar().setTitle("Specific News");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        toolbar2 = findViewById(R.id.toolbar2);
        webView = findViewById(R.id.webView);
        share = findViewById(R.id.share);
        viewChrome = findViewById(R.id.viewChrome);
//        backBtn = findViewById(R.id.backBtn);

        Intent intent =getIntent();
        String url= intent.getStringExtra("url");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Today's News:- "+ url);
                Intent.createChooser(intent, "Share News using...");
                startActivity(intent);
            }
        });

        viewChrome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(browseView.this, Uri.parse(url));
            }
        });

    }
}