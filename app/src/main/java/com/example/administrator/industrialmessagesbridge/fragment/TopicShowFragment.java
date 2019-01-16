package com.example.administrator.industrialmessagesbridge.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.SendPostsActivity;
import com.example.administrator.industrialmessagesbridge.activity.TopicContentDetailActivity;
import com.example.administrator.industrialmessagesbridge.adapter.TopicDetailAdapter;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.gc.materialdesign.views.ButtonFloat;
import com.vondear.rxui.view.dialog.RxDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicShowFragment extends Fragment {

    RecyclerView topic_detail_rv;
    List<TopicContent> topicContentList;
    TopicDetailAdapter topicDetailAdapter;
    private int position;
    private ButtonFloat send_posts_bn;
  private LinearLayout topic_show_headll;
    public TopicShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topic_show, container, false);
        topic_show_headll=(LinearLayout)v.findViewById(R.id.topic_show_headll);
        topic_detail_rv = (RecyclerView) v.findViewById(R.id.topic_detail_rv);
        send_posts_bn=(ButtonFloat)v.findViewById(R.id.send_posts_bn);
        Bundle bundle=getArguments();
        if (bundle!=null) {
            initData();
            position= bundle.getInt("position");

            topicDetailAdapter = new TopicDetailAdapter(topicContentList);
            topicDetailAdapter.setTopicItemOnclick(new TopicDetailAdapter.TopicItemOnclick() {
                @Override
                public void itemOnlclick(int i) {
                    Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
                }
            });
            topic_detail_rv.setLayoutManager(new LinearLayoutManager(getContext()));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
            Drawable drawable = getContext().getDrawable(R.drawable.divider_color2);
            dividerItemDecoration.setDrawable(drawable);
            topic_detail_rv.addItemDecoration(dividerItemDecoration);
            topic_detail_rv.setAdapter(topicDetailAdapter);
            send_posts_bn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getActivity(), SendPostsActivity.class);
                    startActivityForResult(intent,0);
                }
            });
        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0){
            if (data!=null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    topicContentList.add((TopicContent) bundle.getParcelable("topic"));
                    topicDetailAdapter.notifyDataSetChanged();
                }
            }

        }
    }

    public void initData() {
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
