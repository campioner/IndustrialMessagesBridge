package com.example.administrator.industrialmessagesbridge.fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.TopicDetailAdapter;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotPostsFragment extends Fragment {
    RecyclerView topic_detail_rv;
    List<TopicContent> topicContentList;
    TopicDetailAdapter topicDetailAdapter;
private RecyclerView posts_hot_rv;
    public HotPostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hot_posts, container, false);
        posts_hot_rv=(RecyclerView)view.findViewById(R.id.posts_hot_rv);
        return view;
    }

}
