package com.example.administrator.industrialmessagesbridge.fragment.groupFragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.RecommandYihuiAdapter;
import com.example.administrator.industrialmessagesbridge.model.EHui;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupFragment extends Fragment {
private RecyclerView my_group_recently_see_rv,my_yihui_rv;
private RecommandYihuiAdapter recommandYihuiAdapter;
private List<EHui>eHuiList;
    private RecommandYihuiAdapter recommandYihuiAdapter2;
    public MyGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_group, container, false);
        my_group_recently_see_rv=(RecyclerView)v.findViewById(R.id.my_group_recently_see_rv);
        my_yihui_rv=(RecyclerView)v.findViewById(R.id.my_yihui_rv);
        initData1();
        initRecentlyRecycleView();
        return v;
    }
    public void initRecentlyRecycleView(){
        recommandYihuiAdapter=new RecommandYihuiAdapter(eHuiList);
        my_group_recently_see_rv.setAdapter(recommandYihuiAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        my_group_recently_see_rv.setLayoutManager(linearLayoutManager);
        my_group_recently_see_rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        recommandYihuiAdapter2=new RecommandYihuiAdapter(eHuiList);
        recommandYihuiAdapter2.setHorizon(true);
        my_yihui_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        my_yihui_rv.setAdapter(recommandYihuiAdapter2);
    }
    public void initData1(){
        //获取e汇数据
        eHuiList=new ArrayList<>();
        eHuiList.add(new EHui(0,0, "标签1", "话题1", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(1,0, "标签2", "话题2", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(2,0, "标签3333", "话题333", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 1, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(3,1, "标签4", "话题4", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(4,2, "标签5", "话题5", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
    }
}
