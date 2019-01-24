package com.example.administrator.industrialmessagesbridge.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.AppointMasterAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.BeAppointAdapter;
import com.example.administrator.industrialmessagesbridge.model.BeAppoint;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeAppointMentFragment extends Fragment {

    private RecyclerView apponit_master_rv;
    private int type=0;
    private SmartRefreshLayout refreshLayout;
  private BeAppointAdapter beAppointAdapter;
  private List<BeAppoint>beAppointList;
    public BeAppointMentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_be_appoint_ment, container, false);
        apponit_master_rv=(RecyclerView)v.findViewById(R.id.apponit_master_rv);
        refreshLayout=(SmartRefreshLayout)v.findViewById(R.id.refreshLayout);
        beAppointList=new ArrayList<>();
        beAppointAdapter=new BeAppointAdapter(beAppointList);
        /*
         * 加载数据
         * **/
        switch (getArguments().getInt("appoint_type")){
            case 0:
                break;
            case 1:
                break;
        }
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
        initData();
        return v;
    }
    public void initData(){
        beAppointList.add(new BeAppoint(1,"金鸡","","2018-10-21",1));
        apponit_master_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        apponit_master_rv.setAdapter(beAppointAdapter);
    }

}
