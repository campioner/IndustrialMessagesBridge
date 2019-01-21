package com.example.administrator.industrialmessagesbridge.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.EHui;
import com.jph.takephoto.model.TException;

import java.util.List;

public class GroupShowAdapter extends RecyclerView.Adapter {
    private List<EHui>eHuiList;
    private int showData=1;
    private int showHeader=0;
    private boolean isShow=true;
    public GroupShowAdapter(List<EHui> eHuiList) {
        this.eHuiList = eHuiList;
    }
    public class GroupShowViewDataHolder extends RecyclerView.ViewHolder{
        public ImageView group_image;
        public TextView group_name;
        public TextView group_fensi_count;
        public TextView group_bref;
        public Button group_bn;
        public TextView group_count;
        public GroupShowViewDataHolder(View itemView) {
            super(itemView);
            group_image=(ImageView)itemView.findViewById(R.id.group_image);
            group_name=(TextView)itemView.findViewById(R.id.group_name);
            group_fensi_count=(TextView)itemView.findViewById(R.id.group_fensi_count);
            group_bref=(TextView)itemView.findViewById(R.id.group_bref);
            group_bn=(Button) itemView.findViewById(R.id.group_bn);
            group_count=(TextView)itemView.findViewById(R.id.group_count);
        }
    }
    public class GroupShowViewHeaderHolder extends RecyclerView.ViewHolder{
        public ImageView group_header_image;
        public TextView group_header_brief;
        public GroupShowViewHeaderHolder(View itemView) {
            super(itemView);
            group_header_image=(ImageView)itemView.findViewById(R.id.group_header_image);
            group_header_brief=(TextView)itemView.findViewById(R.id.group_header_brief);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==showData){
            return new GroupShowViewDataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_show_item,parent,false));
        }else{
            return new GroupShowViewHeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_header,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
     if (holder instanceof GroupShowViewDataHolder ){
         GroupShowViewDataHolder groupShowViewDataHolder=(GroupShowViewDataHolder)holder;

         //改动

         Glide.with(MyApplication.getContext()).load(new Integer(eHuiList.get(position).getTopicImage())).into(groupShowViewDataHolder.group_image);
         groupShowViewDataHolder.group_name.setText(eHuiList.get(position).getTopicName());
         groupShowViewDataHolder.group_count.setText(eHuiList.get(position).getTieziCount());
         groupShowViewDataHolder.group_fensi_count.setText(eHuiList.get(position).getFensiCount());
         groupShowViewDataHolder.group_bref.setText(eHuiList.get(position).getTopicBrief());
         if (!isShow)
             groupShowViewDataHolder.group_bn.setVisibility(View.GONE);
         if (eHuiList.get(position).getStatue().intValue()==1){
             groupShowViewDataHolder.group_bn.setText("已关注");
             groupShowViewDataHolder.group_bn.setTextColor(Color.parseColor("#FFE4674A"));
         }else{
             groupShowViewDataHolder.group_bn.setText("未关注");
             groupShowViewDataHolder.group_bn.setTextColor(Color.parseColor("#c3c3c3"));
         }
     }
     else{
         GroupShowViewHeaderHolder groupShowViewHeaderHolder=(GroupShowViewHeaderHolder)holder;
         if (position==0) {
             Glide.with(MyApplication.getContext()).load(R.drawable.hot).into(groupShowViewHeaderHolder.group_header_image);
             groupShowViewHeaderHolder.group_header_brief.setText("热门");
             return ;
         }
         groupShowViewHeaderHolder.group_header_brief.setText(eHuiList.get(position+1).getLableName());
     }
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    @Override
    public int getItemViewType(int position) {
        if (eHuiList.get(position)!=null)
            return showData;
        else
            return showHeader;
    }

    @Override
    public int getItemCount() {
        return eHuiList.size();
    }
    public interface Onclick{
        void onlick(int position);
    }
}
