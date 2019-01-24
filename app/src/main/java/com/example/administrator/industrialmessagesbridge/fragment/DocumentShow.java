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
import com.example.administrator.industrialmessagesbridge.adapter.DocumentShowAdapter;
import com.example.administrator.industrialmessagesbridge.model.FileInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentShow extends Fragment {
private RecyclerView document_show_rv;
private List<FileInfo>fileInfoList;
private DocumentShowAdapter documentShowAdapter;
    private SmartRefreshLayout refreshLayout;
    public DocumentShow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_document_show, container, false);
        document_show_rv=(RecyclerView)v.findViewById(R.id.document_show_rv);
        document_show_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout=(SmartRefreshLayout)v.findViewById(R.id.refreshLayout);
        fileInfoList=new ArrayList<>();
        initData();
        documentShowAdapter=new DocumentShowAdapter(fileInfoList);
        document_show_rv.setAdapter(documentShowAdapter);
        loadingData();
        return v;
    }
public void initData(){
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","防盗八法","1000000"));
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","天书奇谈","1000000"));
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","独孤九剑","1000000"));
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","北冥神功","1000000"));
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","降龙十八掌","1000000"));
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","九阴真经","1000000"));
    fileInfoList.add(new FileInfo(1,"2019-1-21 7:23","乾坤大挪移","1000000"));
}
public void loadingData(){
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
}

}
