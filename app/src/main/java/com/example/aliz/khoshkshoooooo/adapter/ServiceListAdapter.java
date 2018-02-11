package com.example.aliz.khoshkshoooooo.adapter;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.controller.ServiceList;

import java.util.List;

/**
 * Created by Al!Z on 11/7/2017.
 */

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {
    private List<ServiceList> serviceLists;
    private OnItemClickListener itemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int position,String ServiceName);
    }
    public void setItemClickListener(ServiceListAdapter.OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public ServiceListAdapter(List<ServiceList> serviceLists) {
        this.serviceLists = serviceLists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView ServiceName;
        public MyViewHolder(final View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            ServiceName = itemView.findViewById(R.id.Service_tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String Service = ServiceName.getText().toString();
                    onItemClickListener.onItemClick(position,Service);
                }
            });
        }
    }
    @Override
    public ServiceListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice_service_card_row,parent,false);
        return new  ServiceListAdapter.MyViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(final ServiceListAdapter.MyViewHolder holder, int position) {
        final ServiceList serviceList = serviceLists.get(position);
        holder.ServiceName.setText(serviceList.getServiceName());
        holder.ServiceName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                serviceList.setServiceSelected(!serviceList.isServiceSelected());
                holder.itemView.setBackgroundColor(serviceList.isServiceSelected() ? Color.CYAN : Color.WHITE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceLists.size();
    }

}

