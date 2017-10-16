package com.example.aliz.khoshkshoooooo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;

import java.util.List;

/**
 * Created by Al!Z on 10/16/2017.
 */

public class ClothesKindAdapter extends RecyclerView.Adapter<ClothesKindAdapter.MyViewHolder> {
    private List<ClothesKindList> clothesKinds;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ClothesKind;
        public MyViewHolder(View itemView) {
            super(itemView);
            ClothesKind = (TextView) itemView.findViewById(R.id.ClothesKind_tvName);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice_clothes_kind_card_row,parent,false);
        return new  ClothesKindAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ClothesKindList kindList = clothesKinds.get(position);
        holder.ClothesKind.setText(kindList.getKindName());
    }

    @Override
    public int getItemCount() {
        return clothesKinds.size();
    }

}
