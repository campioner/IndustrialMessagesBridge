package com.example.administrator.industrialmessagesbridge.fragment.groupFragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.RecommandBottomTopicAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.RecommandYihuiAdapter;
import com.example.administrator.industrialmessagesbridge.model.EHui;
import com.example.administrator.industrialmessagesbridge.model.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommandGroupFragment extends Fragment {
private RecyclerView recommand_yihui_rv,recommand_yihui_bottom_rv;
private List<EHui> eHuiList;
private RecommandYihuiAdapter recommandYihuiAdapter;
private List<Messages>messagesList;
//底部精华帖推荐
    private RecommandBottomTopicAdapter recommandBottomTopicAdapter;
    public RecommandGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_attention, container, false);
        recommand_yihui_rv=(RecyclerView)v.findViewById(R.id.recommand_yihui_rv);
        recommand_yihui_bottom_rv=(RecyclerView)v.findViewById(R.id.recommand_yihui_bottom_rv);
        initData1();
        initData2();
        initTopRecommandRecycleView();
        //底部精华帖
        initBottomRecommandRecycleView();
        return v;
    }
    public void initBottomRecommandRecycleView(){
        recommandBottomTopicAdapter=new RecommandBottomTopicAdapter(messagesList);
        recommand_yihui_bottom_rv.setAdapter(recommandBottomTopicAdapter);
        recommand_yihui_bottom_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    public void initData2(){
        messagesList=new ArrayList<>();
        messagesList.add(new Messages(1, 1, "一点寒芒先到，随后枪出如龙","2018-11-12",20, 20, 20, 20, 20,20,0, "赵信",1));
        messagesList.add(new Messages(1, 1, "一点寒芒先到，随后枪出如龙","2018-11-12",20, 20, 20, 20, 20,20,0, "赵信",1));
        messagesList.add(new Messages(1, 1, "一点寒芒先到，随后枪出如龙","2018-11-12",20, 20, 20, 20, 20,20,0, "赵信",1));
        messagesList.add(new Messages(1, 1, "一点寒芒先到，随后枪出如龙","2018-11-12",20, 20, 20, 20, 20,20,0, "赵信",1));
        messagesList.add(new Messages(1, 1, "一点寒芒先到，随后枪出如龙","2018-11-12",20, 20, 20, 20, 20,20,0, "赵信",1));
    }
    public void initTopRecommandRecycleView(){
        recommandYihuiAdapter=new RecommandYihuiAdapter(eHuiList);
        recommand_yihui_rv.setLayoutManager(new GridLayoutManager(getContext(),4));
        recommand_yihui_rv.setAdapter(recommandYihuiAdapter);
        recommandYihuiAdapter.setOnclick(new RecommandYihuiAdapter.Onclick() {
            @Override
            public void onlick(int position) {

            }
        });
    }
    public void initData1(){
        //获取e汇数据
        eHuiList=new ArrayList<>();
        eHuiList.add(new EHui(0,0, "标签1", "话题1", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(1,0, "标签2", "话题2", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 1, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(2,0, "标签3333", "话题333", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(3,1, "标签4", "话题4", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(4,2, "标签5", "话题5", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
    }

}
