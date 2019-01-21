package com.example.administrator.industrialmessagesbridge.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.TaskBigImgActivity;
import com.example.administrator.industrialmessagesbridge.adapter.RecommandAdapter;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeVpFragment extends Fragment {
private RecyclerView homeFragment_recommend;
    private RecommandAdapter recommandAdapter;
    List<TopicContent> topicContentList;
    public HomeVpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home_vp, container, false);
        homeFragment_recommend=(RecyclerView)v.findViewById(R.id.homeFragment_recommend);
        initData();
        homeFragment_recommend.setLayoutManager(new LinearLayoutManager(getContext()));
        recommandAdapter=new RecommandAdapter(topicContentList);
        recommandAdapter.setItemOnclick(new RecommandAdapter.ItemOnclick() {
            @Override
            public void onlick(int id) {

            }

            @Override
            public void photoIntent(List<Integer> list, int index) {
              Intent intent=new Intent(getContext(), TaskBigImgActivity.class);
                Bundle bundle=new Bundle();
                bundle.putIntegerArrayList("photoList",(ArrayList<Integer>)list);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        homeFragment_recommend.setAdapter(recommandAdapter);
        return v;
    }
    public void initData(){
        topicContentList = new ArrayList<>();
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh, 0, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh,  R.drawable.defaulthhhh, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh, 0, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh,  R.drawable.defaulthhhh, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh,  R.drawable.defaulthhhh,  R.drawable.defaulthhhh));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh, 0, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh, 0, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh, 0, 0));
        topicContentList.add(new TopicContent("10.24.53", "messi", "这是一个没有话题的地方", R.drawable.qq, R.drawable.defaulthhhh, 0, 0));
    }

}
