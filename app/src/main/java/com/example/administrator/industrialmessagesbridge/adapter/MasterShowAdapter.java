package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.DocumentModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MasterShowAdapter extends  RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private List<DocumentModel> documentModelList;

    public MasterShowAdapter(List<DocumentModel> documentModelList) {
        this.documentModelList = documentModelList;
    }

    public class MasterShowViewHoler extends RecyclerView.ViewHolder{
        public CircleImageView master_photo;
        public TextView master_position;
        public TextView maser_lable;
        public TextView master_workTime;
        public TextView master_name;
        public CardView master_ll;
        public MasterShowViewHoler(View itemView) {
            super(itemView);
            master_photo=(CircleImageView)itemView.findViewById(R.id.master_photo);
            master_position=(TextView)itemView.findViewById(R.id.master_position);
            maser_lable=(TextView)itemView.findViewById(R.id.maser_lable);
            master_workTime=(TextView)itemView.findViewById(R.id.master_workTime);
            master_name=(TextView)itemView.findViewById(R.id.master_name);
            master_ll=(CardView)itemView.findViewById(R.id.master_ll);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MasterShowViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommand_master_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MasterShowViewHoler masterShowViewHoler=(MasterShowViewHoler)holder;
        masterShowViewHoler.master_name.setText("令狐冲");
        masterShowViewHoler.master_position.setText("恒山派掌门");
        masterShowViewHoler.maser_lable.setText("独孤九剑");
        masterShowViewHoler.master_workTime.setText("20年");
        masterShowViewHoler.master_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemOnclick.onclick(1);
            }
        });
    }
    private ItemOnclick itemOnclick;
  public interface ItemOnclick{
        public void onclick(int id);
    }

    public void setItemOnclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
