package com.example.aliz.khoshkshoooooo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;

import java.util.List;

/**
 * Created by Al!Z on 9/16/2017.
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {
private List<BillList> BillLists;
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView Type,Detail,Price;
    public ImageView Wash,Dry,Iron,Coloring,StainCleaning;
    public MyViewHolder(View itemView) {
        super(itemView);
        Type= (TextView) itemView.findViewById(R.id.BillCard_tvType);
        Detail=(TextView)itemView.findViewById(R.id.BillCard_tvDetail);
        Price=(TextView)itemView.findViewById(R.id.BillCard_tvPrice);
        Wash=(ImageView)itemView.findViewById(R.id.BillCard_ivWash);
        Dry=(ImageView)itemView.findViewById(R.id.BillCard_ivDryCleaning);
        Iron=(ImageView)itemView.findViewById(R.id.BillCard_ivIron);
        Coloring=(ImageView)itemView.findViewById(R.id.BillCard_ivColoring);
        StainCleaning=(ImageView)itemView.findViewById(R.id.BillCard_ivStainCleaning);
    }
}
    @Override
    public BillAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_card_row,parent,false);
        return new BillAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BillAdapter.MyViewHolder holder, int position) {
        BillList list = BillLists.get(position);
        holder.Type.setText(list.getType());
        holder.Detail.setText(list.getDetail());
        holder.Price.setText(list.getPrice());
    }

    @Override
    public int getItemCount() {
        return BillLists.size();
    }
}
