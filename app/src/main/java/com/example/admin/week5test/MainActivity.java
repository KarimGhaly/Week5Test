package com.example.admin.week5test;

import android.app.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.admin.week5test.model.FlickerClass;
import com.example.admin.week5test.model.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DialogFragment.DialogListner,RVAdapter.SendData {
    RecyclerView RVList;
    public static final String TAG = "MainActivityTAG";
    Item itemSelected;
    FlickerClass flickerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RVList = (RecyclerView) findViewById(R.id.RVList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Call<FlickerClass> getFlickerClass = RetrofitHelper.getFlickerClass();
        getFlickerClass.enqueue(new Callback<FlickerClass>() {
            @Override
            public void onResponse(Call<FlickerClass> call, Response<FlickerClass> response) {
                flickerClass = response.body();
                ShowRecycler();
            }

            @Override
            public void onFailure(Call<FlickerClass> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }
        });
    }
    public void ShowRecycler()
    {
        RVAdapter rvAdapter = new RVAdapter(MainActivity.this,flickerClass,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        RVList.setAdapter(rvAdapter);
        RVList.setLayoutManager(layoutManager);
        RVList.setItemAnimator(itemAnimator);

    }

    @Override
    public void onShowFull() {
        Intent intent = new Intent(MainActivity.this,FullImageActivity.class);
        intent.putExtra("ImgURL",itemSelected.getMedia().getM());
        startActivity(intent);
    }

    @Override
    public void onShowSmall() {
        Dialog D = new Dialog(this);
        D.setTitle("Image");
        D.setContentView(R.layout.image_dialog_layout);
        ImageView DilgImageView = (ImageView)  D.findViewById(R.id.dilgImageView);
        Glide.with(this).load(itemSelected.getMedia().getM()).into(DilgImageView);
        D.show();
    }

    @Override
    public void SendSelectedImage(View view, int Position) {
        itemSelected = flickerClass.getItems().get(Position);
        DialogFragment d = new DialogFragment(this);
        d.show(getFragmentManager(),"Tag");
    }
}
