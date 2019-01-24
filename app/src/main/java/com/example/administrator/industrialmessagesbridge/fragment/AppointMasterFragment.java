package com.example.administrator.industrialmessagesbridge.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.AppointMasterDetailActivity;
import com.example.administrator.industrialmessagesbridge.adapter.AppointMasterAdapter;
import com.example.administrator.industrialmessagesbridge.model.AppointMaster;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointMasterFragment extends Fragment {
    private RecyclerView apponit_master_rv;
   private AppointMasterAdapter appointMasterAdapter;
   private List<AppointMaster>appointMasterList;
   private int type=0;
    private SmartRefreshLayout refreshLayout;
    public AppointMasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_appoint_master, container, false);
        apponit_master_rv=(RecyclerView)v.findViewById(R.id.apponit_master_rv);
        refreshLayout = (SmartRefreshLayout) v.findViewById(R.id.refreshLayout);
        type=getArguments().getInt("appoint_type");
        appointMasterList=new ArrayList<>();
        appointMasterAdapter=new AppointMasterAdapter(appointMasterList);
       switch (type){
           case 0://正在预约
               initData();
               break;
           case 1://预约完成
               initData2();
               break;
       }
        appointMasterAdapter.setItemOnclick(new AppointMasterAdapter.ItemOnclick() {
            @Override
            public void onclick(int postion) {
                Intent intent=new Intent(getActivity(), AppointMasterDetailActivity.class);
                startActivity(intent);
            }
        });
        /*
         * 加载数据
         * **/
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(),"加载更多",Toast.LENGTH_SHORT).show();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加再失败
            }
        });
        return v;
    }
    public void initData(){
        appointMasterList.add(new AppointMaster(1,"沈晨捷","","2018-1-23 12:59",0));
        appointMasterList.add(new AppointMaster(1,"应之类","","2018-1-23 12:59",0));
        appointMasterList.add(new AppointMaster(1,"csgo专家","","2018-1-23 12:59",0));
        appointMasterList.add(new AppointMaster(1,"king","","2018-1-23 12:59",0));

        apponit_master_rv.setAdapter(appointMasterAdapter);
        apponit_master_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    public void initData2(){
        appointMasterList.add(new AppointMaster(1,"沈晨捷","","2018-1-23 12:59",1));
        appointMasterList.add(new AppointMaster(1,"应之类","","2018-1-23 12:59",1));
        appointMasterList.add(new AppointMaster(1,"csgo专家","","2018-1-23 12:59",1));
        appointMasterList.add(new AppointMaster(1,"king","","2018-1-23 12:59",1));
        apponit_master_rv.setAdapter(appointMasterAdapter);
        apponit_master_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
