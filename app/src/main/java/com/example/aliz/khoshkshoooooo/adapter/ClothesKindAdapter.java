package com.example.aliz.khoshkshoooooo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.controller.ClothesKindList;

import java.util.List;

/**
 * Created by Al!Z on 10/16/2017.
 */

public class ClothesKindAdapter extends RecyclerView.Adapter<ClothesKindAdapter.MyViewHolder> {
    private List<ClothesKindList> clothesKinds;
    private OnItemClickListener itemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position,String KindName);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public ClothesKindAdapter(List<ClothesKindList> clothesKindLists) {
        this.clothesKinds = clothesKindLists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView ClothesKind;
        public MyViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            ClothesKind = itemView.findViewById(R.id.ClothesKind_tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        int position = getAdapterPosition();
                        String Kind = ClothesKind.getText().toString();
                        onItemClickListener.onItemClick(position,Kind);
                }
            });
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice_clothes_kind_card_row,parent,false);
        return new  ClothesKindAdapter.MyViewHolder(view,itemClickListener);
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
