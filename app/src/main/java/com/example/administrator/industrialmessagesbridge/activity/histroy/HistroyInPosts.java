package com.example.administrator.industrialmessagesbridge.activity.histroy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.BaseActivity;
import com.example.administrator.industrialmessagesbridge.adapter.HistoryPostsAdapter;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistroyInPosts extends BaseActivity {
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
private HistoryPostsAdapter historyPostsAdapter;
List<TopicContent>topicContentList;
@BindView(R.id.histroy_posts_rv)
    RecyclerView histroy_posts_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy_in_posts);
        ButterKnife.bind(this);
        setSupportActionBar(simple_toolbar);
        initData();
        historyPostsAdapter=new HistoryPostsAdapter(topicContentList);
        histroy_posts_rv.setLayoutManager(new LinearLayoutManager(this));
        histroy_posts_rv.setAdapter(historyPostsAdapter);
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
