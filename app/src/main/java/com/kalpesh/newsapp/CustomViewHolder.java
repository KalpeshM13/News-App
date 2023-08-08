package com.kalpesh.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title, text_source,main_time,main_content;
    ImageView img_headline;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        text_title = itemView.findViewById(R.id.main_heading);
        text_source = itemView.findViewById(R.id.main_author);
        img_headline = itemView.findViewById(R.id.main_image);
        main_content = itemView.findViewById(R.id.main_content);
        cardView = itemView.findViewById(R.id.cardView);
        main_time = itemView.findViewById(R.id.main_time);
    }
}
