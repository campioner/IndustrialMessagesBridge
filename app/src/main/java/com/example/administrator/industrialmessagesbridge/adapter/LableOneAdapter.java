package com.example.administrator.industrialmessagesbridge.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LabelOne;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;

import java.util.ArrayList;
import java.util.List;

public class LableOneAdapter extends  RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private List<LabelOne> labelOneList;
    private List<LableTwo> lableTwoList1;
    private ProvideView provideView;
    private TwoLableAdapter twoLableAdapter;
    private Context context;
    DividerItemDecoration dividerItemDecoration;

    public void setLableTwoClickDao(LableTwoClickDao lableTwoClickDao) {
        this.lableTwoClickDao = lableTwoClickDao;
    }

    private LableTwoClickDao lableTwoClickDao;
    public LableOneAdapter(List<LabelOne> labelOneList, List<LableTwo> lableTwoList1) {
        this.labelOneList = labelOneList;
        this.lableTwoList1 = lableTwoList1;
    }

    public class LabelOneViewHolder extends RecyclerView.ViewHolder{
        ImageView oneLable_iv;
        TextView oneLable_tv;
        ImageButton oneLable_ib;
        RecyclerView twoLable_rv;
        LinearLayout  oneLable_ll;
        public LabelOneViewHolder(@NonNull View itemView) {
            super(itemView);
            oneLable_iv=(ImageView) itemView.findViewById(R.id.oneLable_iv);
            oneLable_tv=(TextView)itemView.findViewById(R.id.oneLable_tv);
            oneLable_ib=(ImageButton)itemView.findViewById(R.id.oneLable_ib);
            twoLable_rv=(RecyclerView)itemView.findViewById(R.id.twoLable_rv);
            oneLable_ll=(LinearLayout)itemView.findViewById(R.id.oneLable_ll);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context=viewGroup.getContext();

        return new LabelOneViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.label_list,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       final LabelOneViewHolder labelOneViewHolder=(LabelOneViewHolder)viewHolder;
        labelOneViewHolder.oneLable_tv.setText(labelOneList.get(i).getName());
        List<LableTwo> lableTwoList=new ArrayList<>();
            for(LableTwo la:lableTwoList1){
                if (labelOneList.get(i).getLableId().equals(la.getLableOneId()))
                    lableTwoList.add(la);
            }
        dividerItemDecoration=new DividerItemDecoration(context,1);
        Drawable drawable= ContextCompat.getDrawable(context,R.drawable.divider_color);
        dividerItemDecoration.setDrawable(drawable);
        labelOneViewHolder.twoLable_rv.setLayoutManager(new GridLayoutManager(context,3));
        twoLableAdapter=new TwoLableAdapter(lableTwoList);
        labelOneViewHolder.twoLable_rv.addItemDecoration(dividerItemDecoration);
        labelOneViewHolder.twoLable_rv.setAdapter(twoLableAdapter);
        twoLableAdapter.setLableTwoClickDao(new TwoLableAdapter.LableTwoClickDao() {
            @Override
            public void lableTwoDao(String name,String lableOneTwo, String lableTwoId) {
                lableTwoClickDao. lableTwoDao(name,lableOneTwo,lableTwoId);
            }
        });
        labelOneViewHolder.twoLable_rv.setVisibility(View.GONE);
        labelOneViewHolder.oneLable_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isGone=labelOneViewHolder.twoLable_rv.getVisibility();
                if(isGone==8) {
                    labelOneViewHolder.twoLable_rv.setVisibility(View.VISIBLE);
                    labelOneViewHolder.oneLable_ib.setImageResource(R.drawable.click);
                }
                else {
                    labelOneViewHolder.twoLable_rv.setVisibility(View.GONE);
                    labelOneViewHolder.oneLable_ib.setImageResource(R.drawable.dont_click);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return labelOneList.size();
    }
    public interface LableOneClickDao{
        public void lableOneDao(String lableOne);
    }
    public interface LableTwoClickDao{
        public void lableTwoDao(String lableTwoName,String lableOneTwo,String lableTwoId);
    }
    private interface ProvideView{
        public View getView();
    }

}
