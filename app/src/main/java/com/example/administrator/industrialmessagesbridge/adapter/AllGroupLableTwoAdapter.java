package com.example.administrator.industrialmessagesbridge.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.AllGroupLabelOneAdapter;
import com.example.administrator.industrialmessagesbridge.model.AllLable;

import java.util.List;

public class AllGroupLableTwoAdapter extends RecyclerView.Adapter {
    private List<AllLable.LabelTwoAll> labelList;
    private Onclick onclick;

    public AllGroupLableTwoAdapter(List<AllLable.LabelTwoAll> labelList) {
        this.labelList = labelList;
    }

    public class AllGroupLableTwoViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout group_label_item_ll;
        public TextView group_label_item_tv;
        public AllGroupLableTwoViewHolder(View itemView) {
            super(itemView);
            group_label_item_ll=(LinearLayout)itemView.findViewById(R.id.group_label_item_ll);
            group_label_item_tv=(TextView)itemView.findViewById(R.id.group_label_item_tv);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllGroupLableTwoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_label_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        final AllGroupLableTwoViewHolder allGroupLabelViewHolder =(AllGroupLableTwoViewHolder)holder;
        allGroupLabelViewHolder.group_label_item_tv.setText(labelList.get(position).second_label_name);
        if (labelList.get(position).isTwoClick){
            allGroupLabelViewHolder.group_label_item_ll.setBackgroundResource(R.color.baise);
            allGroupLabelViewHolder.group_label_item_tv.setTextColor(Color.parseColor("#FFE4674A"));
        }else{
            allGroupLabelViewHolder.group_label_item_ll.setBackgroundResource(R.color.defaultcolor);
            allGroupLabelViewHolder.group_label_item_tv.setTextColor(Color.parseColor("#FF000000"));
        }
        allGroupLabelViewHolder.group_label_item_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onclick(position);
                notifyDataSetChanged();
            }
        });
    }
public interface Onclick{
  void  onclick(int position);
}

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    @Override
    public int getItemCount() {
        return labelList.size();
    }
}

