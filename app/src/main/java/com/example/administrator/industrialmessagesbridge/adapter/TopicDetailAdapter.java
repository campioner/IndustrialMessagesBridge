package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.example.administrator.industrialmessagesbridge.myView.RoundImageView;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import java.util.List;

public class TopicDetailAdapter extends   RecyclerView.Adapter <RecyclerView.ViewHolder> {
    List<TopicContent> topicList;
  public TopicItemOnclick topicItemOnclick;

    public void setTopicItemOnclick(TopicItemOnclick topicItemOnclick) {
        this.topicItemOnclick = topicItemOnclick;
    }

    public TopicDetailAdapter(List<TopicContent> topicList) {
        this.topicList = topicList;
    }

    public class  TopicDetailViewHoder extends RecyclerView.ViewHolder{
        RoundImageView topic_list_image;
        TextView topic_list_time;
        TextView topic_list_userName;
        TextView topic_list_content;
        ImageView topic_list_image_one;
        ImageView topic_list_image_two;
        ImageView topic_list_image_three;
        LinearLayout topic_list_ll;
        public TopicDetailViewHoder(@NonNull View itemView) {
            super(itemView);
            topic_list_image=(RoundImageView) itemView.findViewById(R.id.topic_list_image);
            topic_list_time=(TextView)itemView.findViewById(R.id.topic_list_time);
            topic_list_userName=(TextView)itemView.findViewById(R.id.topic_list_userName);
            topic_list_content=(TextView)itemView.findViewById(R.id.topic_list_content);
            topic_list_image_one=(ImageView)itemView.findViewById(R.id.topic_list_image_one);
            topic_list_image_two=(ImageView)itemView.findViewById(R.id.topic_list_image_two);
            topic_list_image_three=(ImageView)itemView.findViewById(R.id.topic_list_image_three);
            topic_list_ll=(LinearLayout)itemView.findViewById(R.id.topic_list_ll);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicDetailViewHoder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int position=i;
        TopicDetailViewHoder hoder=(TopicDetailViewHoder)viewHolder;
        hoder.topic_list_image.setImageResource(topicList.get(i).getImage());
        hoder.topic_list_time.setText(topicList.get(i).getTime());
        hoder.topic_list_content.setText(topicList.get(i).getContent());
        hoder.topic_list_userName.setText(topicList.get(i).getName());
        if (topicList.get(i).getImageOne()!=0){
            hoder.topic_list_image_one.setVisibility(View.VISIBLE);
            hoder.topic_list_image_one.setImageResource(topicList.get(i).getImageOne());
            if (topicList.get(i).getImageTwo()!=0){
                hoder.topic_list_image_two.setVisibility(View.VISIBLE);
                hoder.topic_list_image_two.setImageResource(topicList.get(i).getImageTwo());
                if (topicList.get(i).getImageThree()!=0){
                    hoder.topic_list_image_three.setVisibility(View.VISIBLE);
                    hoder.topic_list_image_three.setImageResource(topicList.get(i).getImageThree());
                }else
                    hoder.topic_list_image_three.setVisibility(View.GONE);
            }else{
                hoder.topic_list_image_two.setVisibility(View.GONE);
                hoder.topic_list_image_three.setVisibility(View.GONE);
            }
        }else {
            hoder.topic_list_image_one.setVisibility(View.GONE);
            hoder.topic_list_image_two.setVisibility(View.GONE);
            hoder.topic_list_image_three.setVisibility(View.GONE);
        }
        hoder.topic_list_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topicItemOnclick.itemOnlclick(position);
            }
        });
    }

  public interface TopicItemOnclick{
        public void itemOnlclick(int i);
  }
    @Override
    public int getItemCount() {
        return topicList.size();
    }
}
