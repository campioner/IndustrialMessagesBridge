package com.example.administrator.industrialmessagesbridge.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.MainActivity;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.BusinessNeed;
import com.example.administrator.industrialmessagesbridge.activity.MasterIntroduce;
import com.example.administrator.industrialmessagesbridge.activity.TaskBigImgActivity;
import com.example.administrator.industrialmessagesbridge.adapter.HomeFragmentFunctionAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.HotLabelAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.RecommadnItemDecroration;
import com.example.administrator.industrialmessagesbridge.adapter.RecommandAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.HomeFunction;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.HomeRecommandAdapter;
import com.example.administrator.industrialmessagesbridge.model.DocumentModel;
import com.example.administrator.industrialmessagesbridge.model.LabelTwo;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.example.administrator.industrialmessagesbridge.model.UserInfo;
import com.example.administrator.industrialmessagesbridge.model.UserTop;
import com.example.administrator.industrialmessagesbridge.myView.FullyLinearLayoutManager;
import com.example.administrator.industrialmessagesbridge.myView.InsideViewPager;
import com.example.administrator.industrialmessagesbridge.myView.MyImageButton;
import com.example.administrator.industrialmessagesbridge.myView.MyScrollView;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.myView.StickyScrollView;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;
import com.example.administrator.industrialmessagesbridge.utils.GlideImageLoader;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.views.ScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;


import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

private List<Integer>bannerList;

private List<HomeFunction>homeFunctionList;
private HomeFragmentFunctionAdapter homeFragmentFunctionAdapter;
private RecyclerView homeFragment_founction;
private RecommandAdapter recommandAdapter;

private SimpleToolbar simple_toolbar;
    List<TopicContent> topicContentList;
    private StickyScrollView home_scroll_view;
private List<LabelTwo>labelTwoList;
    RelativeLayout move_rl;
    private RecyclerView hot_label_rv;
    private HotLabelAdapter hotLabelAdapter;
    private MagicIndicator magicIndicatorhh;
    //爱好标签
  List<HomeVpFragment>fragmentList;
   private  List<LabelTwo>labelTwoListLike;
   private InsideViewPager home_vp;
   private com.example.administrator.industrialmessagesbridge.myView.Banner1 banner_vp;
   private List<Fragment>bannerFragmentList;
   private Handler bannerHandler=new Handler();
   private int page=0;

   private List<UserTop>knownUserList;
   private RecyclerView recommand_konwn_user_rv;
   private HomeRecommandAdapter homeRecommandKnowUserAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        homeFragment_founction=(RecyclerView)view.findViewById(R.id.homeFragment_founction);
        magicIndicatorhh=(MagicIndicator)view.findViewById(R.id.magic_indicatorhh);
        home_scroll_view=(StickyScrollView)view.findViewById(R.id.home_scroll_view);
        home_vp=(InsideViewPager)view.findViewById(R.id.home_vp1);
        banner_vp=(com.example.administrator.industrialmessagesbridge.myView.Banner1)view.findViewById(R.id.banner_vp);
        recommand_konwn_user_rv=(RecyclerView)view.findViewById(R.id.recommand_konwn_user_rv);
        move_rl=(RelativeLayout) view.findViewById(R.id.move_rl);
        hot_label_rv=(RecyclerView)view.findViewById(R.id.hot_label_rv);
        //初始化轮播图
        initbanner();
        //下方功能键
        initFunctionBn();
        //推荐热门专家
        initHotMaster();
        //下方推荐
        initRecommand();
        //初始化热点标签
        initRecommandHotLabel();
        //初始化导航
        initMagicIndicator();
        return view;
    }
    public void initHotMaster(){
        //推荐名人
        LinearLayoutManager linearLayoutManager3=new LinearLayoutManager(getContext());
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommand_konwn_user_rv.setLayoutManager(linearLayoutManager3);
        knownUserList=new ArrayList<>();
        knownUserList.add(new UserTop(1,"用户","",1));
        knownUserList.add(new UserTop(1,"用户","",1));
        knownUserList.add(new UserTop(1,"用户","",1));
        knownUserList.add(new UserTop(1,"用户","",1));
        homeRecommandKnowUserAdapter=new HomeRecommandAdapter(knownUserList);
        recommand_konwn_user_rv.setAdapter(homeRecommandKnowUserAdapter);
    }
    public void initMagicIndicator(){
        CommonNavigator commonNavigator = new CommonNavigator(getContext());

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return labelTwoListLike.size();
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(labelTwoListLike.get(index).getLabelName());
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        home_vp.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(R.color.barcolor);

                return indicator;
            }

        });

        home_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicatorhh.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // Log.e("BaseFragment","onPageScrolled:"+ position);
            }
            @Override
            public void onPageSelected(int position) {
                magicIndicatorhh.onPageSelected(position);
                // Log.e("BaseFragment","onPageSelected:"+ position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicatorhh.onPageScrollStateChanged(state);
                // Log.e("BaseFragment","onPageScrollStateChanged:"+ state);
            }
        });
        fragmentList=new ArrayList<>();
        for(int i=0;i<labelTwoListLike.size();i++){

            fragmentList.add(new HomeVpFragment());
        }
        magicIndicatorhh.setNavigator(commonNavigator);
        home_vp.setAdapter(new FragmentAdapter(getChildFragmentManager()));
        home_vp.setOffscreenPageLimit(2);
        home_vp.setCurrentItem(0);
    }
    public class FragmentAdapter extends  FragmentPagerAdapter{
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }
    public void initRecommandHotLabel(){
        hotLabelAdapter=new HotLabelAdapter(labelTwoList);
        hotLabelAdapter.setOnClick(new HotLabelAdapter.OnclickItem() {
            @Override
            public void onlick(int position) {

            }
        });
        LinearLayoutManager hotLinerlayoutManager=new LinearLayoutManager(getContext());
        hotLinerlayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hot_label_rv.setLayoutManager(hotLinerlayoutManager);

        hot_label_rv.setAdapter(hotLabelAdapter);
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

    }
    public void initData() {

        labelTwoList=new ArrayList<>();

        labelTwoList.add(new LabelTwo(1,"标签1","2018,2,20","10"));
        labelTwoList.add(new LabelTwo(2,"标签2","2018,2,20","10"));
        labelTwoList.add(new LabelTwo(3,"标签3","2018,2,20","10"));
        labelTwoList.add(new LabelTwo(4,"标签4","2018,2,20","10"));
        labelTwoList.add(new LabelTwo(5,"标签5","2018,2,20","10"));


        labelTwoListLike=labelTwoList;
    }
    public void initFunctionBn(){
        homeFunctionList=new ArrayList<>();
        homeFunctionList.add(new HomeFunction(R.drawable.xuqiu_xuqiu,"需求"));
        homeFunctionList.add(new HomeFunction(R.drawable.zhuanjia,"专家"));
        homeFunctionList.add(new HomeFunction(R.drawable.renwu_renwu,"任务"));
        homeFunctionList.add(new HomeFunction(R.drawable.wendang_w,"文档"));
        homeFunctionList.add(new HomeFunction(R.drawable.huati_h,"话题"));
        homeFunctionList.add(new HomeFunction(R.drawable.wenzhang_w,"文章"));
        homeFunctionList.add(new HomeFunction(R.drawable.guanzhu_g,"关注"));
        homeFunctionList.add(new HomeFunction(R.drawable.tuijian_t,"用户推荐"));
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
    bannerList.add(R.drawable.basa);
    bannerList.add(R.drawable.basa);
    bannerList.add(R.drawable.basa);
    bannerList.add(R.drawable.basa);

    banner_vp.setImageLoader(new GlideImageLoader());
    banner_vp.isAutoPlay(true);
    //设置轮播时间
    banner_vp.setDelayTime(2000);
    //设置指示器位置（当banner模式中有指示器时）
    banner_vp.setIndicatorGravity(BannerConfig.CENTER);
    //banner设置方法全部调用完毕时最后调用
    //设置图片加载器
    //设置图片集合
    banner_vp.setImages(bannerList);
    //banner设置方法全部调用完毕时最后调用
    banner_vp.start();
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
        banner_vp.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner_vp.stopAutoPlay();
    }

}
