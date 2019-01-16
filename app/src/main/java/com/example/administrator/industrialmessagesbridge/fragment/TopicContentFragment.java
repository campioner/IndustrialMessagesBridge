package com.example.administrator.industrialmessagesbridge.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.TopicContentDetailActivity;
import com.example.administrator.industrialmessagesbridge.adapter.LableOneAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.TopicContentHotRvAdapter;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LabelOne;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicContentFragment extends Fragment {

   private EditText topic_context_at;//搜索框
  private RecyclerView topic_content_lable;
    private TopicContentHotRvAdapter topicContentHotRvAdapter;
    private List<String> hotTopicList;
    private RecyclerView topic_content_hot_rv;
    private List<LabelOne>labelOneList;
    private List<LableTwo>lableTwoList;
    private DaoSession daoSession;
    private  GridLayoutManager linearLayoutManager;
    private LableOneAdapter lableOneAdapter;
    public TopicContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_topic_content, container, false);
        topic_context_at=(EditText)view.findViewById(R.id.topic_context_at);
        topic_content_lable=(RecyclerView)view.findViewById(R.id.topic_content_lable);
        topic_content_hot_rv=(RecyclerView)view.findViewById(R.id.topic_content_hot_rv);
        //热门话题内容到达顶部和底部监听
        topicListener();
        //设置右侧搜索图标
        Drawable searchDrawable= getContext().getDrawable(R.drawable.search);
        searchDrawable.setBounds(0, 0, 70, 70);//上面的代码会将drawable绘制在
        topic_context_at.setCompoundDrawables(searchDrawable,null,null,null);
        initData();
        //初始化标签显示器
        initLableRecycleView();
        topicContentHotRvAdapter=new TopicContentHotRvAdapter(hotTopicList);
        linearLayoutManager=new GridLayoutManager(getActivity(),1);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topic_content_hot_rv.setLayoutManager(linearLayoutManager);
        topic_content_hot_rv.setAdapter(topicContentHotRvAdapter);
        return view;
    }
   public void initLableRecycleView(){
lableOneAdapter=new LableOneAdapter(labelOneList,lableTwoList);
       lableOneAdapter.setLableTwoClickDao(new LableOneAdapter.LableTwoClickDao() {
           @Override
           public void lableTwoDao(String name,String lableOneTwo, String lableTwoId) {
               Intent intent=new Intent(getActivity(), TopicContentDetailActivity.class);
               Bundle bundle=new Bundle();
               bundle.putString("topicName",name);
               bundle.putString("topicLableOne",lableOneTwo);
               bundle.putString("topicLableTwo",lableTwoId);
               intent.putExtras(bundle);
               startActivity(intent);
           }
       });
       topic_content_lable.setLayoutManager(new LinearLayoutManager(getActivity()));
       topic_content_lable.setAdapter(lableOneAdapter);
   }
public void topicListener(){
    topic_content_hot_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            Log.i("msg22","dx"+dx+"dy"+dy);
            if (dx<0&&linearLayoutManager.findFirstCompletelyVisibleItemPosition()==0){
                Toast.makeText(getContext(),"到顶了",Toast.LENGTH_SHORT).show();
            }
            if (recyclerView.computeHorizontalScrollExtent()+recyclerView.computeHorizontalScrollOffset()>=recyclerView.computeHorizontalScrollRange()){
                Toast.makeText(getContext(),"到底部了",Toast.LENGTH_SHORT).show();
            }
        }
    });
}
    public void initData(){
        //获得热门话题
    hotTopicList=new ArrayList<>();
    hotTopicList.add("我是你爸爸的话题就是这种话题");
    hotTopicList.add("你是我儿子的话题就是这种话题");
    hotTopicList.add("我是你父亲的话题就是这种话题");
    hotTopicList.add("你是我的崽的话题就是这种话题");
    hotTopicList.add("我是你爹的话题就是这种话题");
    hotTopicList.add("你是我儿砸的话题就是这种话题");
         //获得一级标签
        labelOneList=new ArrayList<>();
        lableTwoList=new ArrayList<>();
        daoSession = ((MyApplication)MyApplication.getContext()).getDaoSession();
        labelOneList=daoSession.loadAll(LabelOne.class);
        lableTwoList=daoSession.loadAll(LableTwo.class);
   }
}
