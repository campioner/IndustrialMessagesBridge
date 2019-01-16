package com.example.administrator.industrialmessagesbridge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.DocumentModel;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.jaeger.ninegridimageview.GridImageView;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecommandAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private List<TopicContent>topicContentList;

    public RecommandAdapter(List<TopicContent> topicContentList) {
        this.topicContentList = topicContentList;
    }

    public class RecommandViewHolder extends RecyclerView.ViewHolder{
        public ImageView topic_list_image;
        public TextView send_topic_name;
        public TextView topic_list_time;
        public TextView two_lable;
        public TextView topic_list_content;
        public NineGridImageView<Integer> nine_grid;
        public RecommandViewHolder(@NonNull View itemView) {
            super(itemView);
            topic_list_image=(ImageView)itemView.findViewById(R.id.topic_list_image);
            send_topic_name=(TextView)itemView.findViewById(R.id.send_topic_name);
            topic_list_time=(TextView)itemView.findViewById(R.id.topic_list_time);
            two_lable=(TextView)itemView.findViewById(R.id.two_lable);
            nine_grid=(NineGridImageView<Integer>)itemView.findViewById(R.id.nine_grid);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecommandViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_recommand,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {
        RecommandViewHolder recommandViewHolder=(RecommandViewHolder)viewHolder;
        Glide.with(viewHolder.itemView.getContext()).load(topicContentList.get(i).getImage()).into(recommandViewHolder.topic_list_image);
        recommandViewHolder.send_topic_name.setText("我");
        recommandViewHolder.topic_list_time.setText(topicContentList.get(i).getTime());
        recommandViewHolder.two_lable.setText("电鳗小分队");
        recommandViewHolder.nine_grid.setAdapter(mAdapter);
        List<Integer>imageList=new ArrayList<>();
        if (topicContentList.get(i).getImageOne()!=null)
            imageList.add(topicContentList.get(i).getImageOne());
            if (topicContentList.get(i).getImageTwo()!=null)
                imageList.add(topicContentList.get(i).getImageTwo());
                if (topicContentList.get(i).getImageThree()!=null)
                    imageList.add(topicContentList.get(i).getImageThree());
                 recommandViewHolder.nine_grid.setImagesData(imageList);
    }


    public interface ItemOnclick{
       public void onlick(int id);
       public void photoIntent(List<Integer> list,int index);
      }
      private ItemOnclick itemOnclick;

    public void setItemOnclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    @Override
    public int getItemCount() {
        return topicContentList.size();
    }
    private NineGridImageViewAdapter<Integer> mAdapter = new NineGridImageViewAdapter<Integer>() {
        @Override
        protected void onItemImageClick(Context context, ImageView imageView, int index, List<Integer> list) {
            itemOnclick.photoIntent(list,index);
        }

        @Override
        protected ImageView generateImageView(Context context) {
            GridImageView imageView = new GridImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        protected void onDisplayImage(Context context, ImageView imageView, Integer integer) {
            Glide.with(context)
                    .load(integer)
                    .into(imageView);
        }
    };


}
