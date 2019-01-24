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
import com.example.administrator.industrialmessagesbridge.adapter.ShowUserAdapter;
import com.example.administrator.industrialmessagesbridge.model.UserInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowUserFragment extends Fragment {
private SmartRefreshLayout refreshLayout;
private RecyclerView show_user;
private List<UserInfo>userInfoList;
private ShowUserAdapter showUserAdapter;
    public ShowUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_user, container, false);
        refreshLayout=(SmartRefreshLayout)v.findViewById(R.id.refreshLayout);
        show_user=(RecyclerView)v.findViewById(R.id.show_user);
        initData();
        show_user.setLayoutManager(new LinearLayoutManager(getContext()));
        show_user.setAdapter(showUserAdapter);
        loadingData() ;
        return v;
    }
public void initData(){
        userInfoList=new ArrayList<>();
        userInfoList.add(new UserInfo(1,"小兔子","",1));
        userInfoList.add(new UserInfo(1,"兔子先生","",1));
        showUserAdapter=new ShowUserAdapter(userInfoList);
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
