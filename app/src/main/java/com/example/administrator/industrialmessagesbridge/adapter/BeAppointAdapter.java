package com.example.administrator.industrialmessagesbridge.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.AppointMaster;
import com.example.administrator.industrialmessagesbridge.model.BeAppoint;

import java.util.List;

public class BeAppointAdapter extends RecyclerView.Adapter {
    private List<BeAppoint> apponitMasterList;

    public BeAppointAdapter(List<BeAppoint> apponitMasterList) {
        this.apponitMasterList = apponitMasterList;
    }
    public class BeAppointAdapterViewHolder extends RecyclerView.ViewHolder{
        public ImageView appoint_master_image;
        public TextView appoint_master_name;
        public TextView appoint_master_statue;
        public TextView appoint_master_time;
        private Button appoint_master_detail_bn;
        public BeAppointAdapterViewHolder(View itemView) {
            super(itemView);
            appoint_master_image=(ImageView)itemView.findViewById(R.id.appoint_master_image);
            appoint_master_name=(TextView)itemView.findViewById(R.id.appoint_master_name);
            appoint_master_statue=(TextView)itemView.findViewById(R.id.appoint_master_statue);
            appoint_master_time=(TextView)itemView.findViewById(R.id.appoint_master_time);
            appoint_master_detail_bn=(Button)itemView.findViewById(R.id.appoint_master_detail_bn);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeAppointAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.appoint_master_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BeAppointAdapterViewHolder appointMasterViewHolder=(BeAppointAdapterViewHolder)holder;
        appointMasterViewHolder.appoint_master_detail_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclick.onclick(position);
            }
        });
        appointMasterViewHolder.appoint_master_name.setText(apponitMasterList.get(position).getUserName());
        appointMasterViewHolder.appoint_master_time.setText(apponitMasterList.get(position).getOrderTime());
        if (apponitMasterList.get(position).getOrderStatue()==0) {
            appointMasterViewHolder.appoint_master_statue.setText("预约中");
            appointMasterViewHolder.appoint_master_statue.setTextColor(Color.parseColor("#FFE4674A"));
        }
        else  if (apponitMasterList.get(position).getOrderStatue()==1) {
            appointMasterViewHolder.appoint_master_statue.setText("已完成");
            appointMasterViewHolder.appoint_master_statue.setTextColor(Color.parseColor("#FF000000"));
        }
    }
    public interface ItemOnclick{
        public void onclick(int postion);
    }
    private ItemOnclick itemOnclick;

    public void setItemOnclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    @Override
    public int getItemCount() {
        return apponitMasterList.size();
    }
}
