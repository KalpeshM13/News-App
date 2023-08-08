package com.kalpesh.newsapp;

import android.view.MenuItem;

import androidx.cardview.widget.CardView;

import com.kalpesh.newsapp.Models.NewsHeadlines;
import com.google.android.filament.View;

public interface SelectListener {
    void OnNewsClicked(NewsHeadlines headlines);

    void onLongClick(NewsHeadlines headlines, CardView cardView);

    boolean onMenuItemClick(MenuItem item);

    void onClick(View v);
}
