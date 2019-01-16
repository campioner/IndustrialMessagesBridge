package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.NeedShow;

import java.util.List;

public class NeedShowAdapter extends  RecyclerView.Adapter <RecyclerView.ViewHolder> {
  private  List<NeedShow>needShowList;

    public NeedShowAdapter(List<NeedShow> needShowList) {
        this.needShowList = needShowList;
    }

    private NeedItemOnclick needItemOnclick;
    public class NeedShowViewHolder extends RecyclerView.ViewHolder{
        public TextView need_title;
        public TextView need_position;
        public TextView need_price;
        public TextView need_background;
        public TextView need_time;
        public TextView require_person;
        public TextView need_person;
        CardView show_need_ll;
        public NeedShowViewHolder(View itemView) {
            super(itemView);
            show_need_ll=(CardView)itemView.findViewById(R.id.show_need_ll);
            need_title=(TextView) itemView.findViewById(R.id.need_title);
            need_position=(TextView) itemView.findViewById(R.id.need_position);
            need_background=(TextView) itemView.findViewById(R.id.need_background);
            require_person=(TextView) itemView.findViewById(R.id.require_person);
            need_person=(TextView) itemView.findViewById(R.id.need_person);
            need_price=(TextView) itemView.findViewById(R.id.need_price);
            need_time=(TextView) itemView.findViewById(R.id.need_time);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NeedShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_need_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        NeedShowViewHolder holder1=(NeedShowViewHolder)holder;
        holder1.show_need_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                needItemOnclick.onlick(needShowList.get(position).getNeedId(),needShowList.get(position));
            }
        });
        holder1.need_title.setText(needShowList.get(position).getNeed_title());
        if (needShowList.get(position).getNeed_background().length()>30)
            holder1.need_background.setText(needShowList.get(position).getNeed_background().substring(0,30)+"......");
        else
        holder1.need_background.setText(needShowList.get(position).getNeed_background());
        holder1.need_person.setText(needShowList.get(position).getNeed_person());
        holder1.need_position.setText(needShowList.get(position).getNeed_position());
        holder1.need_price.setText(needShowList.get(position).getNeed_price());
        holder1.need_time.setText(needShowList.get(position).getNeed_time());
        holder1.require_person.setText(needShowList.get(position).getRequire_person());
    }

    @Override
    public int getItemCount() {
        return needShowList.size();
    }
    public interface NeedItemOnclick{
        public void onlick(int needId,NeedShow needShow);
    }

    public NeedItemOnclick getNeedItemOnclick() {
        return needItemOnclick;
    }

    public void setNeedItemOnclick(NeedItemOnclick needItemOnclick) {
        this.needItemOnclick = needItemOnclick;
    }
}
