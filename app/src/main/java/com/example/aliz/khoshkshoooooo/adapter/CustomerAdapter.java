package com.example.aliz.khoshkshoooooo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.controller.CustomerList;

import java.util.List;

/**
 * Created by AliZ on 1/11/2018.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    private List<CustomerList> customerLists;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public CustomerAdapter(List<CustomerList> CustomerList){
        this.customerLists = CustomerList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_row,null);
        MyViewHolder viewHolder = new MyViewHolder(view,onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CustomerList customerList = customerLists.get(position);
        holder.CustomerName.setText(customerList.getCustomerName());
        holder.CustomerPhoneNumber.setText(customerList.getCustomerPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return customerLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView CustomerName,CustomerPhoneNumber;
        public MyViewHolder(View itemView,final OnItemClickListener onItemClickListener) {
            super(itemView);
            CustomerName = (TextView) itemView.findViewById(R.id.Customer_tvName);
            CustomerPhoneNumber = (TextView) itemView.findViewById(R.id.Customer_tvPhoneNumber);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }
}
