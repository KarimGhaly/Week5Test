package com.example.admin.week5test;

import android.content.ContentResolver;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.week5test.model.FlickerClass;
import com.example.admin.week5test.model.Item;

/**
 * Created by Admin on 9/29/2017.
 */

public class RVAdapter  extends RecyclerView.Adapter<RVAdapter.ViewHolder>{
    Context context;
    FlickerClass flickerClass;
    SendData mListner;
    public static final String TAG = "RVAdapterTAG";

    public RVAdapter(Context context, FlickerClass flickerClass, SendData mListner) {
        this.context = context;
        this.flickerClass = flickerClass;
        this.mListner = mListner;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Item i = flickerClass.getItems().get(position);
        holder.txtTitle.setText("Title: "+i.getTitle());
        holder.txtDate.setText("Date Taken: "+i.getDateTaken());
        Glide.with(context).load(i.getMedia().getM()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return flickerClass.getItems().size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        ImageView imgView;
        TextView txtTitle;
        TextView txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.itemImg);
            txtTitle = (TextView) itemView.findViewById(R.id.itemTxtTitle);
            txtDate = (TextView) itemView.findViewById(R.id.itemTxtDateTaken);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {
            Log.d(TAG, "onLongClick: "+getLayoutPosition());
            mListner.SendSelectedImage(view,getLayoutPosition());
            return true;
        }
    }
    interface SendData
    {
        void SendSelectedImage(View v,int Position);
    }
}
