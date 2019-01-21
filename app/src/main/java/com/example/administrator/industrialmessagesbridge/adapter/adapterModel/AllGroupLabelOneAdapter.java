package com.example.administrator.industrialmessagesbridge.adapter.adapterModel;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.AllLable;
import com.example.administrator.industrialmessagesbridge.model.Label;

import java.util.List;

public class AllGroupLabelOneAdapter extends RecyclerView.Adapter {
  private List<AllLable>labelList;
private Onclick onclick;
    public AllGroupLabelOneAdapter(List<AllLable> labelList) {
        this.labelList = labelList;
    }

    public class AllGroupLabelViewHolder extends RecyclerView.ViewHolder{
        public  LinearLayout group_label_item_ll;
        public TextView group_label_item_tv;
        public View label_view;
        public AllGroupLabelViewHolder(View itemView) {
            super(itemView);
            group_label_item_ll=(LinearLayout)itemView.findViewById(R.id.group_label_item_ll);
            group_label_item_tv=(TextView)itemView.findViewById(R.id.group_label_item_tv);
            label_view=(View) itemView.findViewById(R.id.label_view);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllGroupLabelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_label_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
     final    AllGroupLabelViewHolder allGroupLabelViewHolder =(AllGroupLabelViewHolder)holder;
        allGroupLabelViewHolder.group_label_item_tv.setText(labelList.get(position).getLabel_name());
        if (labelList.get(position).getClick()){
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
        allGroupLabelViewHolder.label_view.setVisibility(View.GONE);
    }
public interface Onclick{
        public void onclick(int position);
}

    public Onclick getOnclick() {
        return onclick;
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    @Override
    public int getItemCount() {
        return labelList.size();
    }
}
