package com.example.administrator.industrialmessagesbridge.adapter.adapterModel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.UserInfo;
import com.example.administrator.industrialmessagesbridge.model.UserTop;

import java.util.List;

public class HomeRecommandAdapter extends RecyclerView.Adapter {
    private List<UserTop>userInfoList;

    public HomeRecommandAdapter(List<UserTop> userInfoList) {
        this.userInfoList = userInfoList;
    }
  public class HomeRecommandViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout recommand_ll;
        public ImageView recommand_iv;
        public TextView recommand_name;
      public HomeRecommandViewHolder(View itemView) {
          super(itemView);
          recommand_ll=(LinearLayout)itemView.findViewById(R.id.recommand_ll);
          recommand_iv=(ImageView)itemView.findViewById(R.id.recommand_iv);
          recommand_name=(TextView)itemView.findViewById(R.id.recommand_name);
      }
  }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeRecommandViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recommand,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HomeRecommandViewHolder homeRecommandViewHolder=(HomeRecommandViewHolder)holder;
        homeRecommandViewHolder.recommand_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onclick(position);
            }
        });
        homeRecommandViewHolder.recommand_name.setText(userInfoList.get(position).getUserName());
    }
public interface ItemOnclick{
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
