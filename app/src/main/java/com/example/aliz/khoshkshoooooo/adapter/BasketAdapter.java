package com.example.aliz.khoshkshoooooo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.controller.BasketList;

import java.util.List;

/**
 * Created by Al!Z on 9/16/2017.
 */

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.MyViewHolder> {
    private List<BasketList> basketLists;
    public BasketAdapter(List<BasketList> basketLists) {
        this.basketLists = basketLists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Type,Detail,Price;
        public ImageView Wash,Dry,Iron,Coloring,StainCleaning;
        public TextView Quantity;
        public MyViewHolder(View itemView) {
            super(itemView);
            Type= (TextView) itemView.findViewById(R.id.BasketCard_tvType);
            Detail=(TextView)itemView.findViewById(R.id.BasketCard_tvDetail);
            Price=(TextView)itemView.findViewById(R.id.BasketCard_tvPrice);
            Wash=(ImageView)itemView.findViewById(R.id.BasketCard_ivWash);
            Dry=(ImageView)itemView.findViewById(R.id.BasketCard_ivDryCleaning);
            Iron=(ImageView)itemView.findViewById(R.id.BasketCard_ivIron);
            Coloring=(ImageView)itemView.findViewById(R.id.BasketCard_ivColoring);
            StainCleaning=(ImageView)itemView.findViewById(R.id.BasketCard_ivStainCleaning);
            Quantity = (TextView)itemView.findViewById(R.id.BasketCard_etQuantity);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_card_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BasketList list = basketLists.get(position);
        holder.Type.setText(list.getType());
        holder.Detail.setText(list.getDetail());
        holder.Price.setText(list.getPrice());
        holder.Quantity.setText(list.getAmount());
    }

    @Override
    public int getItemCount() {
        return basketLists.size();
    }

}
