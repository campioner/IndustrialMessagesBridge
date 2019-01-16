package com.example.administrator.industrialmessagesbridge.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;

import java.util.ArrayList;
import java.util.List;

public class TopicContentHotRvAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
   private List<String>hotTopicList;
   private List<Integer>colorList;
   private HotTopicOnlickDao hotTopicOnlickDao;

    public TopicContentHotRvAdapter(List<String> hotTopicList) {
        this.hotTopicList = hotTopicList;
        colorList=new ArrayList<>();
        colorList.add(Color.BLUE);
        colorList.add(Color.BLUE);
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.GREEN);
        colorList.add(Color.GREEN);
        colorList.add(Color.RED);
        colorList.add(Color.RED);
        colorList.add(Color.RED);
        colorList.add(Color.YELLOW);
        colorList.add(Color.YELLOW);
        colorList.add(Color.YELLOW);
    }

    public class TopicContentHotRvViewHoder extends RecyclerView.ViewHolder{
        LinearLayout topic_hot_ll;
        TextView topic_hot_content;
        public TopicContentHotRvViewHoder(@NonNull View itemView) {
            super(itemView);
            topic_hot_ll=(LinearLayout) itemView.findViewById(R.id.topic_hot_ll);
            topic_hot_content=(TextView) itemView.findViewById(R.id.topic_hot_content);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setHotTopicOnlickDao(HotTopicOnlickDao hotTopicOnlickDao) {
        this.hotTopicOnlickDao = hotTopicOnlickDao;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopicContentHotRvViewHoder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_hot_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int positon=i;
        TopicContentHotRvViewHoder viewHoder1=(TopicContentHotRvViewHoder)viewHolder;
        viewHoder1.topic_hot_content.setText(hotTopicList.get(i));
        viewHoder1.topic_hot_ll.setBackgroundColor(colorList.get(i));
        viewHoder1.topic_hot_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotTopicOnlickDao.onclick(positon);
            }
        });
    }
    public interface HotTopicOnlickDao{
        public void onclick(int position);
    }
    @Override
    public int getItemCount() {
        return hotTopicList.size();
    }
}
