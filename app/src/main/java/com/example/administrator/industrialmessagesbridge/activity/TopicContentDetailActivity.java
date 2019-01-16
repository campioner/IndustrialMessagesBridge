package com.example.administrator.industrialmessagesbridge.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.fragment.TopicShowFragment;
import com.vondear.rxui.view.dialog.RxDialog;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicContentDetailActivity extends BaseActivity implements View.OnClickListener{
  private String topicName;
  private String oneLable;
  private String twoLable;
  private  List<Fragment>fragmentList;
 @BindView(R.id.topic_content_mi)
  MagicIndicator topic_content_mi;
  @BindView(R.id.topic_content_vp)
    ViewPager topic_content_vp;
  @BindView(R.id.topic_content_name)
    TextView topic_content_name;
  final String[]topicLable={"帖子","热门"};
  @BindView(R.id.topic_show_sort_method)
  TextView topic_show_sort_method;
  @BindView(R.id.topic_show_bn1)
  TextView topic_show_bn1;
  //弹出对话框
    RxDialog rxDialog;
    @BindView(R.id.back)
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_content_detail);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
       Bundle bundle=getIntent().getExtras();
        topicName=bundle.getString("topicName");
        oneLable=bundle.getString("topicLableOne");
        twoLable=bundle.getString("topicLableTwo");
        topic_content_name.setText(topicName);
        topic_show_bn1.setOnClickListener(this);
        back.setOnClickListener(this);
        initListener();
        initMagicIndicator();
    }
    public void initListener(){
        //设置弹出的布局
         rxDialog=new RxDialog(this);
         View dialogv= LayoutInflater.from(this).inflate(R.layout.dialog_sort_method,null,false);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        rxDialog.addContentView(dialogv,layoutParams);
        rxDialog.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topic_show_bn1:
                rxDialog.show();
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    /**
     * 滑动布局
     */
    public void initMagicIndicator(){
        CommonNavigator commonNavigator=new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public IPagerTitleView getTitleView(Context context,final int i) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(topicLable[i]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        topic_content_vp.setCurrentItem(i);
                    }
                });
                return colorTransitionPagerTitleView;
            }
            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        topic_content_mi.setNavigator(commonNavigator);
        topic_content_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                topic_content_mi.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            @Override
            public void onPageSelected(int position) {
                topic_content_mi.onPageSelected(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                topic_content_mi.onPageScrollStateChanged(state);
            }
        });
        //帖子界面
        Bundle bundle=null;
        fragmentList=new ArrayList<>();
        TopicShowFragment postsFragment= new TopicShowFragment();
         //传值
        bundle=(Bundle) getIntent().getExtras().clone();
        bundle.putInt("position",0);
        postsFragment.setArguments(bundle);
        fragmentList.add(postsFragment);
        //热门界面
        bundle=new Bundle();
        bundle= (Bundle) getIntent().getExtras().clone();
        bundle.putInt("position",1);
        TopicShowFragment hotPostsFragment=new TopicShowFragment();
        hotPostsFragment.setArguments(bundle);
        fragmentList.add(hotPostsFragment);
        //话题界面
        topic_content_vp.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
    }
    public class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }


        @Override
        public int getCount() {
            return 2;
        }

    }
}
