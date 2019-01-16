package com.example.administrator.industrialmessagesbridge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;

import java.util.List;

public class TwoLableAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{
    private List<LableTwo> lableTwoList;
    private LableTwoClickDao lableTwoClickDao;

    public void setLableTwoClickDao(LableTwoClickDao lableTwoClickDao) {
        this.lableTwoClickDao = lableTwoClickDao;
    }

    public TwoLableAdapter(List<LableTwo> lableTwoList) {
        this.lableTwoList = lableTwoList;

    }

    public class LabelTwoViewHolder extends RecyclerView.ViewHolder{
       TextView tv;
        public LabelTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.twoLable_tv);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LabelTwoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.two_lable_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final  int position=i;
        LabelTwoViewHolder viewHolder1=(LabelTwoViewHolder)viewHolder;
        viewHolder1.tv.setText(lableTwoList.get(i).getName());
        viewHolder1.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            lableTwoClickDao.lableTwoDao(lableTwoList.get(position).getName(),lableTwoList.get(position).getLableOneId(),lableTwoList.get(position).getLableTwoId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lableTwoList.size();
    }
    public interface LableTwoClickDao{
        public void lableTwoDao(String name,String lableOneTwo,String lableTwoId);
    }
}
