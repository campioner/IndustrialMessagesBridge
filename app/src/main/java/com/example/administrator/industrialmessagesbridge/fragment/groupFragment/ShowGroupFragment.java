package com.example.administrator.industrialmessagesbridge.fragment.groupFragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.GroupShowAdapter;
import com.example.administrator.industrialmessagesbridge.model.EHui;
import com.example.administrator.industrialmessagesbridge.utils.Net;
import com.google.gson.Gson;
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
public class ShowGroupFragment extends Fragment {
private RecyclerView show_group_rv;
private GroupShowAdapter groupShowAdapter;
private List<EHui>eHuiList;
private int type;

    public ShowGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_show_group, container, false);
        show_group_rv=(RecyclerView)v.findViewById(R.id.show_group_rv);
        Bundle bundle=getArguments();
        type=bundle.getInt("group_fragment_type");
        if (type==0)
            initData1();
        else
            initData2();

        return v;
    }
   public void initData1(){
        //获取e汇数据
       OkGo.<String>get(Net.findAllYiHui)
               .tag(this).params("userId","1").execute(new StringCallback() {
           @Override
           public void onSuccess(Response<String> response) {
               Type type = new TypeToken<List<EHui>>() {}.getType();
               eHuiList=new Gson().fromJson(response.body(), type);
               groupShowAdapter=new GroupShowAdapter(eHuiList);
               show_group_rv.setAdapter(groupShowAdapter);
               show_group_rv.setLayoutManager(new LinearLayoutManager(getContext()));
           }

           @Override
           public void onError(Response<String> response) {
               super.onError(response);
           }
       });

   }
    public void initData2(){
        //获取e汇数据
        eHuiList=new ArrayList<>();
        eHuiList.add(null);
        eHuiList.add(new EHui(0,0, "标签1", "话题1", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(1,0, "标签2", "话题2", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 1, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(2,0, "标签3", "话题3", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(null);
        eHuiList.add(new EHui(3,1, "标签4", "话题4", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 1, new Integer(R.drawable.defaulthhhh).toString()));
        eHuiList.add(new EHui(4,2, "标签5", "话题5", "11", "11", "2019,1,1", "介绍1", "1万", "100万", 0, new Integer(R.drawable.defaulthhhh).toString()));
        groupShowAdapter=new GroupShowAdapter(eHuiList);
        show_group_rv.setAdapter(groupShowAdapter);
        show_group_rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
