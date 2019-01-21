package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.Messages;

import java.util.List;

public class RecommandBottomTopicAdapter extends RecyclerView.Adapter {
    private List<Messages> messagesList;
    private Onclick onclick;
    public class RecommandBottomViewHolder extends RecyclerView.ViewHolder{
        public TextView recommand_bottom_topic_describe;
        public TextView recommand_bottom_topic_send_name;
        public TextView recommand_bottom_topic_sendtime;
        public ImageView recommand_bottom_topic_image;
        public LinearLayout recommand_bottom_topic_ll;
        public RecommandBottomViewHolder(View itemView) {
            super(itemView);
            recommand_bottom_topic_describe=(TextView)itemView.findViewById(R.id.recommand_bottom_topic_describe);
            recommand_bottom_topic_send_name=(TextView)itemView.findViewById(R.id.recommand_bottom_topic_send_name);
            recommand_bottom_topic_sendtime=(TextView)itemView.findViewById(R.id.recommand_bottom_topic_sendtime);
            recommand_bottom_topic_image=(ImageView)itemView.findViewById(R.id.recommand_bottom_topic_image);
            recommand_bottom_topic_ll=(LinearLayout)itemView.findViewById(R.id.recommand_bottom_topic_ll);
        }
    }
    public RecommandBottomTopicAdapter(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommandBottomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommand_bottom_topic_item,parent,false));

    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RecommandBottomViewHolder recommandBottomViewHolder=(RecommandBottomViewHolder)holder;
        recommandBottomViewHolder.recommand_bottom_topic_send_name.setText(messagesList.get(position).getUser_name());
        recommandBottomViewHolder.recommand_bottom_topic_describe.setText(messagesList.get(position).getMessages_info());
        recommandBottomViewHolder.recommand_bottom_topic_sendtime.setText(messagesList.get(position).getMessages_time());
        recommandBottomViewHolder.recommand_bottom_topic_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           onclick.onclick(position);
            }
        });
    }
    public interface Onclick{
        public void onclick(int position);
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

}
