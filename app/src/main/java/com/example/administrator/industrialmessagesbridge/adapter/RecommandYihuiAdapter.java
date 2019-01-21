package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darsh.multipleimageselect.models.Image;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.EHui;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import java.util.List;

public class RecommandYihuiAdapter extends RecyclerView.Adapter {
    private List<EHui> eHuiList;
    private boolean horizon=false;
   public class RecommandYihuiViewHolder extends RecyclerView.ViewHolder{
       public ImageView recommand_yihui_image;
       public TextView recommand_yihui_tv;
       public LinearLayout recommand_yihui_ll;
       public RecommandYihuiViewHolder(View itemView) {
           super(itemView);
           recommand_yihui_image=(ImageView)itemView.findViewById(R.id.recommand_yihui_image);
           recommand_yihui_tv=(TextView)itemView.findViewById(R.id.recommand_yihui_tv);
           recommand_yihui_ll=(LinearLayout)itemView.findViewById(R.id.recommand_yihui_ll);
       }
   }

    public class RecommandYihuiViewHolder2 extends RecyclerView.ViewHolder{
       public TextView my_yihui_name1;
        public TextView my_yihui_name2;
        public RelativeLayout my_yihui_ll1;
        public RelativeLayout my_yihui_ll2;
        public RecommandYihuiViewHolder2(View itemView) {
            super(itemView);
            my_yihui_name1=(TextView)itemView.findViewById(R.id.my_yihui_name1);
            my_yihui_name2=(TextView)itemView.findViewById(R.id.my_yihui_name2);
            my_yihui_ll1=(RelativeLayout)itemView.findViewById(R.id.my_yihui_ll1);
            my_yihui_ll2=(RelativeLayout)itemView.findViewById(R.id.my_yihuiu_ll2);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(DeviceUtil.deviceWidth(MyApplication.getContext())/2-DeviceUtil.dp2px(MyApplication.getContext(),1),DeviceUtil.dp2px(MyApplication.getContext(),40));
            my_yihui_ll1.setLayoutParams(layoutParams);
            my_yihui_ll2.setLayoutParams(layoutParams);
        }
    }
    public RecommandYihuiAdapter(List<EHui> eHuiList) {
        this.eHuiList = eHuiList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if (horizon)
           return new RecommandYihuiViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_group_yihui_item,parent,false));
        return new RecommandYihuiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommand_ehui_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       if (holder instanceof RecommandYihuiViewHolder) {
           RecommandYihuiViewHolder recommandYihuiViewHolder = (RecommandYihuiViewHolder) holder;
           //推荐议会的图片
           Glide.with(MyApplication.getContext()).load(new Integer(eHuiList.get(position).getTopicImage())).into(recommandYihuiViewHolder.recommand_yihui_image);
           String yiHuiName = eHuiList.get(position).getTopicName();
           if (eHuiList.get(position).getTopicName().length() > 4) {
               yiHuiName = eHuiList.get(position).getTopicName().substring(0, 4) + "...";
           }
           recommandYihuiViewHolder.recommand_yihui_tv.setText(yiHuiName);
           recommandYihuiViewHolder.recommand_yihui_ll.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onclick.onlick(position);
               }
           });
       }
       if (holder instanceof  RecommandYihuiViewHolder2){
           RecommandYihuiViewHolder2 recommandYihuiViewHolder2=(RecommandYihuiViewHolder2)holder;
               recommandYihuiViewHolder2.my_yihui_name1.setText(eHuiList.get(position*2).getTopicName());

           RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams )recommandYihuiViewHolder2.my_yihui_ll1.getLayoutParams();
               recommandYihuiViewHolder2.my_yihui_ll1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       onclick.onlick(position);
                   }
               });
               if (position*2+1<eHuiList.size()) {
                   recommandYihuiViewHolder2.my_yihui_name2.setText(eHuiList.get(position * 2 + 1).getTopicName());

                   recommandYihuiViewHolder2.my_yihui_ll2.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           onclick.onlick(position);
                       }
                   });
               }else{
                   recommandYihuiViewHolder2.my_yihui_ll2.setVisibility(View.GONE);
               }

       }
    }
    private Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setHorizon(boolean horizon) {
        this.horizon = horizon;
    }

    public interface Onclick{
       public void onlick(int position);
}
    @Override
    public int getItemCount() {
        if (horizon){
            if(eHuiList.size()%2==0)
                return eHuiList.size()/2;
            else
                return eHuiList.size()/2+1;
        }
        return eHuiList.size();
    }
}
