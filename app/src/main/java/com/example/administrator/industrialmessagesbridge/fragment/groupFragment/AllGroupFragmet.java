package com.example.administrator.industrialmessagesbridge.fragment.groupFragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.AllGroupLableTwoAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.GroupShowAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.AllGroupLabelOneAdapter;
import com.example.administrator.industrialmessagesbridge.model.AllLable;
import com.example.administrator.industrialmessagesbridge.model.EHui;
import com.example.administrator.industrialmessagesbridge.model.Label;
import com.example.administrator.industrialmessagesbridge.utils.Net;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllGroupFragmet extends Fragment implements View.OnClickListener{
private ImageButton show_label2_bn,all_group_arrow_left;
private Boolean isShow=false;
private RecyclerView all_group_lable_two_rv,all_group_lable_one_rv;
private AllGroupLabelOneAdapter allGroupLabelOneAdapter;
private GroupShowAdapter groupShowAdapter;
private RecyclerView all_group_show_yihui_rv;
    private RelativeLayout all_group_rv;
    private List<EHui> eHuiList;
    public List<AllLable>allLableList;
    int later=0;
    int laterOne=0,laterTwo=0;
    List<AllLable.LabelTwoAll> labelTwoAllListl;
    private AllGroupLableTwoAdapter allGroupLableTwoAdapter;
    public AllGroupFragmet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_all_group_fragmet, container, false);
        show_label2_bn=(ImageButton)v.findViewById(R.id.show_label2_bn);
        all_group_arrow_left=(ImageButton)v.findViewById(R.id.all_group_arrow_left);
        show_label2_bn.setOnClickListener(this);
        all_group_lable_two_rv=(RecyclerView)v.findViewById(R.id.all_group_lable_two_rv);
        all_group_show_yihui_rv=(RecyclerView)v.findViewById(R.id.all_group_show_yihui_rv);
        all_group_lable_one_rv=(RecyclerView)v.findViewById(R.id.all_group_lable_one_rv);
        all_group_rv=(RelativeLayout)v.findViewById(R.id.all_group_rv);
        all_group_lable_two_rv.setVisibility(View.INVISIBLE);
        allLableList=new ArrayList<>();
        getAllLable();
        return v;
    }
    public void getAllLable(){
        OkGo.<String>get(Net.findAllSystemLabel)
                .tag(this).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String l=response.body();
                try {
                    JSONArray jsonArray=new JSONArray(l);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        AllLable allLable=new AllLable(jsonObject.getInt("label_id"),jsonObject.getString("label_name"),jsonObject.getString("label_frequency"),jsonObject.getString("label_time"));
                        JSONArray jsonArray1=jsonObject.getJSONArray("next_label");
                        List<AllLable.LabelTwoAll>labelTwoAllList1=new ArrayList<>();
                        for (int j=0;j<jsonArray1.length();j++){
                            JSONObject jsonObject1=jsonArray1.getJSONObject(j);
                            AllLable.LabelTwoAll labelTwoAll=allLable.getALableToAll(jsonObject1.getInt("second_label_id"),jsonObject1.getString("second_label_name"),jsonObject1.getString("second_label_time"),jsonObject1.getString("second_label_frequency"));
                            if (i==0&&j==0){
                                labelTwoAll.isTwoClick=true;
                            }else
                                labelTwoAll.isTwoClick=false;
                            labelTwoAllList1.add(labelTwoAll);
                        }
                        allLable.setLabelTwoAllList(labelTwoAllList1);
                        allLable.setClick(false);
                        if (i==0)
                            allLable.setClick(true);
                        allLableList.add(allLable);
                    }
                    initLabelOneRecycleView();
                    int r=1;
                    int q=r;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }
public void initLabelOneRecycleView(){
    allGroupLabelOneAdapter=new AllGroupLabelOneAdapter(allLableList);
    allGroupLabelOneAdapter.setOnclick(new AllGroupLabelOneAdapter.Onclick() {
        @Override
        public void onclick(int position) {
            allLableList.get(later).setClick(false);
            allLableList.get(position).setClick(true);
            later=position;
            initLableTwoRecycleView(allLableList.get(position).getLabelTwoAllList());
        }
    });
    all_group_lable_one_rv.setAdapter(allGroupLabelOneAdapter);
    all_group_lable_one_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    labelTwoAllListl=new ArrayList<>();
    labelTwoAllListl.addAll(allLableList.get(0).getLabelTwoAllList());
    allGroupLableTwoAdapter=new AllGroupLableTwoAdapter(labelTwoAllListl);
    allGroupLableTwoAdapter.setOnclick(new AllGroupLableTwoAdapter.Onclick() {
        @Override
        public void onclick(int position) {
            allLableList.get(laterOne).getLabelTwoAllList().get(laterTwo).isTwoClick=false;
            allLableList.get(later).getLabelTwoAllList().get(position).isTwoClick=true;
            laterOne=later;
            laterTwo=position;
            groupShowAdapter.notifyDataSetChanged();
        }
    });
    all_group_lable_two_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    all_group_lable_two_rv.setAdapter(allGroupLableTwoAdapter);
    //初始化议会
    initData1();
    groupShowAdapter =new GroupShowAdapter(eHuiList);
    groupShowAdapter.setShow(false);
    all_group_show_yihui_rv.setAdapter(groupShowAdapter);
    all_group_show_yihui_rv.setLayoutManager(new LinearLayoutManager(getContext()));
}
public void initLableTwoRecycleView(final List<AllLable.LabelTwoAll> labelListtwo){
    labelTwoAllListl.clear();
    labelTwoAllListl.addAll(labelListtwo);
    allGroupLableTwoAdapter.notifyDataSetChanged();
}
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.show_label2_bn:
                if (!isShow){
                    isShow=true;
                    all_group_lable_two_rv.setVisibility(View.VISIBLE);
                    all_group_lable_two_rv.setTranslationX(all_group_lable_two_rv.getWidth());
                    all_group_rv.setTranslationX(all_group_lable_two_rv.getWidth());
                    AnimatorSet set=new AnimatorSet();
                    ObjectAnimator o2 = ObjectAnimator.ofFloat(all_group_arrow_left, "rotation", 0,180);
                    o2.setDuration(500);
                    set.play(o2);
                    set.start();
                }else{
                    isShow=false;
                    all_group_lable_two_rv.setVisibility(View.INVISIBLE);
                    all_group_lable_two_rv.setTranslationX(0);
                    all_group_rv.setTranslationX(0);
                    AnimatorSet set=new AnimatorSet();
                    ObjectAnimator o2 = ObjectAnimator.ofFloat(all_group_arrow_left, "rotation", 180,0);
                    o2.setDuration(500);
                    set.play(o2);
                    set.start();
                }
                break;
        }
    }
    public void initData1(){
        //获取e汇数据
        eHuiList=new ArrayList<>();
        eHuiList.add(new EHui(0,0, "标签1", "话题1", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(1,0, "标签2", "话题2", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 1, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(2,0, "标签3", "话题3", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(3,1, "标签4", "话题4", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 1, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(4,2, "标签5", "话题5", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
    }
}
