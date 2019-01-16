package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.DocumentModel;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;

import java.util.List;

public class HistoryPostsAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private List <TopicContent>topicContents;

    public HistoryPostsAdapter(List<TopicContent> topicContents) {
        this.topicContents = topicContents;
    }

    public class HistoryPostsViewHoder extends RecyclerView.ViewHolder{
        TextView topic_list_time;
        TextView topic_list_content;
        public HistoryPostsViewHoder(View itemView) {
            super(itemView);
            topic_list_time=(TextView)itemView.findViewById(R.id.topic_list_time);
            topic_list_content=(TextView)itemView.findViewById(R.id.topic_list_content);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryPostsViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.histroy_posts_list,parent,false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryPostsViewHoder viewHoder1=(HistoryPostsViewHoder)holder;
        viewHoder1.topic_list_time.setText(topicContents.get(position).getTime());
        viewHoder1.topic_list_content.setText(topicContents.get(position).getContent());
    }
    @Override
    public int getItemCount() {
        return topicContents.size();
    }
}
