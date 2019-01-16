package com.example.administrator.industrialmessagesbridge.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.MainActivity;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.BusinessNeed;
import com.example.administrator.industrialmessagesbridge.activity.MasterIntroduce;
import com.example.administrator.industrialmessagesbridge.activity.TaskBigImgActivity;
import com.example.administrator.industrialmessagesbridge.adapter.HomeFragmentFunctionAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.RecommadnItemDecroration;
import com.example.administrator.industrialmessagesbridge.adapter.RecommandAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.HomeFunction;
import com.example.administrator.industrialmessagesbridge.model.DocumentModel;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.example.administrator.industrialmessagesbridge.myView.FullyLinearLayoutManager;
import com.example.administrator.industrialmessagesbridge.myView.MyImageButton;
import com.example.administrator.industrialmessagesbridge.myView.MyScrollView;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;
import com.example.administrator.industrialmessagesbridge.utils.GlideImageLoader;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private com.youth.banner.Banner maser_banner;
private List<Integer>bannerList;

private List<HomeFunction>homeFunctionList;
private HomeFragmentFunctionAdapter homeFragmentFunctionAdapter;
private RecyclerView homeFragment_founction;
private RecommandAdapter recommandAdapter;
private List<DocumentModel>documentModelList;
private RecyclerView homeFragment_recommend;
private SimpleToolbar simple_toolbar;
private MyImageButton home_send_bn;
    List<TopicContent> topicContentList;
    private MyScrollView scroll_view;
    private int scrollViewY=0;
    private int h;

    private int beforeX,afterX;
    private int moveX,moveY;
    RelativeLayout move_rl;
    private boolean isreturn=false;
    private int direction=2;
    private boolean fling=false;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        homeFragment_recommend=(RecyclerView)view.findViewById(R.id.homeFragment_recommend);
        maser_banner=(com.youth.banner.Banner)view.findViewById(R.id.maser_banner);
        homeFragment_founction=(RecyclerView)view.findViewById(R.id.homeFragment_founction);
        home_send_bn=(MyImageButton) view.findViewById(R.id.home_send_bn);
        scroll_view=(MyScrollView)view.findViewById(R.id.scroll_view);
        move_rl=(RelativeLayout) view.findViewById(R.id.move_rl);
        scroll_view.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
              //  Log.i("msg1",scrollY+"endscrollY");
              // if (home_send_bn.getY()<DeviceUtil.deviceHeight(getContext())+scrollY)
                    home_send_bn.setTranslationY(h + scrollY);
                Log.i("msg1",h + scrollY+"ACTION_UP home_send_bn.getY()");
              /*  else
                    home_send_bn.setY(home_send_bn.getY()+DeviceUtil.deviceHeight(getContext())-DeviceUtil.dp2px(getContext(),240)+scrollY);*/
               if (home_send_bn.getX()<(DeviceUtil.deviceWidth(getContext())/2))
                      home_send_bn.setX(-(home_send_bn.getWidth()/2-DeviceUtil.dp2px(getContext(),2)));
               else
               home_send_bn.setX(afterX);
                scrollViewY=scrollY;
               // Log.i("msg1",home_send_bn.getY()+"scroll_view home_send_bn.getY()");
            }
        });
       ;
        home_send_bn.post(new Runnable() {
            @Override
            public void run() {
                beforeX=(int)home_send_bn.getX();
                 home_send_bn.setTranslationX(DeviceUtil.dp2px(getContext(),22));
                home_send_bn.setTranslationY(home_send_bn.getY()+DeviceUtil.deviceHeight(getContext())-DeviceUtil.dp2px(getContext(),240));
                 afterX=(int)home_send_bn.getX();
                home_send_bn.setX(beforeX);
                  h=(int)home_send_bn.getY();

            }
        });home_send_bn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (direction == 2) {
                            if (DeviceUtil.deviceWidth(getContext()) - DeviceUtil.dp2px(getContext(), 40) < home_send_bn.getX()) {
                                home_send_bn.setX(beforeX);
                            } else
                                home_send_bn.setX(afterX);
                        }else{
                            if (home_send_bn.getX()>0){
                                home_send_bn.setX(-(home_send_bn.getWidth()/2-DeviceUtil.dp2px(getContext(),2)));
                            }else{
                                home_send_bn.setX(0);
                            }
                        }
                    }
                });
        scroll_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (isreturn) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            moveX = (int) event.getX();
                            moveY = (int) event.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:

                            home_send_bn.setX( event.getX());
                            home_send_bn.setY( event.getY()+scrollViewY);
                          //  Log.i("msg1",home_send_bn.getY()+"ACTION_MOVE home_send_bn.getY()");
                            break;
                        case MotionEvent.ACTION_UP:
                            if (home_send_bn.getX()>=DeviceUtil.deviceWidth(getContext())/2){
                                home_send_bn.setX(afterX);
                                direction=2;
                            }else{
                                direction=4;
                                home_send_bn.setX(0);
                            }
                           // Log.i("msg1",home_send_bn.getY()+"ACTION_UP home_send_bn.getY()");
                            fling=true;
                           h=(int)event.getY();
                            isreturn=false;
                            break;
                    }
                }
                return isreturn;
            }
        });

        home_send_bn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        isreturn=true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        isreturn=false;
                        break;
                }
                return false;
            }
        });
        //初始化轮播图
        initbanner();
        //下方功能键
        initFunctionBn();
        //下方推荐
        initRecommand();
        return view;
    }
    public void initRecommand(){

        initData();
        recommandAdapter=new RecommandAdapter(topicContentList);
        recommandAdapter.setItemOnclick(new RecommandAdapter.ItemOnclick() {
            @Override
            public void onlick(int id) {

            }

            @Override
            public void photoIntent(List<Integer> list,int index) {
            Intent intent=new Intent(getContext(), TaskBigImgActivity.class);
            Bundle bundle=new Bundle();
            bundle.putIntegerArrayList("photoList",(ArrayList<Integer>) list);
                bundle.putInt("index",index);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        homeFragment_recommend.setAdapter(recommandAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeFragment_recommend.setLayoutManager(linearLayoutManager);
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
    public void initFunctionBn(){
        homeFunctionList=new ArrayList<>();
        homeFunctionList.add(new HomeFunction(R.drawable.hellofour,"需求"));
        homeFunctionList.add(new HomeFunction(R.drawable.helloone,"专家"));
        homeFunctionList.add(new HomeFunction(R.drawable.renwu,"任务"));
        homeFunctionList.add(new HomeFunction(R.drawable.wendang,"文档"));
        homeFunctionList.add(new HomeFunction(R.drawable.huati,"话题"));
        homeFragmentFunctionAdapter=new HomeFragmentFunctionAdapter(homeFunctionList);
        homeFragment_founction.setLayoutManager(new GridLayoutManager(getContext(),4));
        homeFragment_founction.setAdapter(homeFragmentFunctionAdapter);
        homeFragmentFunctionAdapter.setFunctionClick(new HomeFragmentFunctionAdapter.FunctionClick() {
            @Override
            public void functionClick(int position) {
              switch (position){
                  case 0:
                      Intent intent=new Intent(getContext(), BusinessNeed.class);
                      Bundle bundle=new Bundle();
                      bundle.putInt("home_intent",0);
                      intent.putExtras(bundle);
                      startActivity(intent);
                      break;
                  case 1:
                      Intent intent2=new Intent(getContext(), BusinessNeed.class);
                      Bundle bundle2=new Bundle();
                      bundle2.putInt("home_intent",1);
                      intent2.putExtras(bundle2);
                      startActivity(intent2);
                      break;
                  case 2:
                      break;
                  case 3:
                      break;
                  case 4:
                      break;
              }
            }
        });
    }
public void initbanner(){
    // 设置数据
    bannerList=new ArrayList<>();
    bannerList.add(R.drawable.banner_test);
    bannerList.add(R.drawable.banner_test);
    bannerList.add(R.drawable.banner_test);
    bannerList.add(R.drawable.banner_test);
    bannerList.add(R.drawable.banner_test);
    //设置banner样式
  //  maser_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
    //设置图片加载器
    maser_banner.setImageLoader(new GlideImageLoader());
    //设置banner动画效果
    //设置标题集合（当banner样式有显示title时）
  //  maser_banner.setBannerTitles(titles);
    //设置自动轮播，默认为true
    maser_banner.isAutoPlay(true);
    //设置轮播时间
   maser_banner.setDelayTime(2000);
    //设置指示器位置（当banner模式中有指示器时）
    maser_banner.setIndicatorGravity(BannerConfig.CENTER);
    //banner设置方法全部调用完毕时最后调用
    //设置图片加载器
    //设置图片集合
    maser_banner.setImages(bannerList);
    //banner设置方法全部调用完毕时最后调用
    maser_banner.start();

}

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        maser_banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        maser_banner.stopAutoPlay();
    }

}
