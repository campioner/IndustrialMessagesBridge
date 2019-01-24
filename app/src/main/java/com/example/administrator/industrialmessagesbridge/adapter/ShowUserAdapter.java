package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.UserInfo;

import java.util.List;

public class ShowUserAdapter extends RecyclerView.Adapter {
    private List<UserInfo>userInfoList;

    public ShowUserAdapter(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public class ShowUserViewHolder extends RecyclerView.ViewHolder{
        public ImageView user_image;
        public TextView user_name;
        public Button attention_bn;
        public ShowUserViewHolder(View itemView) {
            super(itemView);
            user_image=(ImageView)itemView.findViewById(R.id.user_image);
            user_name=(TextView)itemView.findViewById(R.id.user_name);
            attention_bn=(Button)itemView.findViewById(R.id.attention_bn);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowUserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_show_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ShowUserViewHolder showUserViewHolder=(ShowUserViewHolder)holder;
        showUserViewHolder.user_name.setText(userInfoList.get(position).getUserName());
        showUserViewHolder.attention_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onclick(position);
            }
        });
        if (userInfoList.get(position).getIsAttention()==0){
            showUserViewHolder.attention_bn.setText("+关注");
        }else
            showUserViewHolder.attention_bn.setText("已关注");
    }
public  interface ItemOnclick{
        public void onclick(int position);
}
private ItemOnclick onclick;

    public void setOnclick(ItemOnclick onclick) {
        this.onclick = onclick;
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }
}
