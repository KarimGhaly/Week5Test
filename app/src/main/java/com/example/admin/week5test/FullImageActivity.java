package com.example.admin.week5test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);


        Intent intent = getIntent();
        ImageView imgView = (ImageView) findViewById(R.id.ImageView);
        String URL = intent.getStringExtra("ImgURL");
        if(URL!=null)
        {
            Glide.with(this).load(URL).into(imgView);
        }

    }
}
