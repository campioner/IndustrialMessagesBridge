package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.LabelTwo;

import java.util.List;

import butterknife.OnClick;

public class HotLabelAdapter extends RecyclerView.Adapter {
    private List<LabelTwo>labelTwoList;
    private OnclickItem onClick;

    public void setOnClick(OnclickItem onClick) {
        this.onClick = onClick;
    }

    public HotLabelAdapter(List<LabelTwo> labelTwoList) {
        this.labelTwoList = labelTwoList;
    }

    public class HotLabelViewHolder extends RecyclerView.ViewHolder{
        public TextView hot_tv;
        public HotLabelViewHolder(View itemView) {
            super(itemView);
            hot_tv=(TextView)itemView.findViewById(R.id.hot_tv);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotLabelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_tv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HotLabelViewHolder hotLabelViewHolder=(HotLabelViewHolder)holder;
        if (position==0){
            hotLabelViewHolder.hot_tv.setBackground(null);
            hotLabelViewHolder.hot_tv.setText("热搜:");
        }else {

            hotLabelViewHolder.hot_tv.setText(labelTwoList.get(position-1).getLabelName());
            hotLabelViewHolder.hot_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onlick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return labelTwoList.size()+1;
    }
    public interface OnclickItem{
        void onlick(int position);
    }
}
