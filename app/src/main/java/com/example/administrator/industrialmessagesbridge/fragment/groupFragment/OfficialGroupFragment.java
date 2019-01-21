package com.example.administrator.industrialmessagesbridge.fragment.groupFragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.fragment.HomeFragment;
import com.example.administrator.industrialmessagesbridge.fragment.HomeVpFragment;
import com.example.administrator.industrialmessagesbridge.myView.InsideViewPager;
import com.example.administrator.industrialmessagesbridge.myView.StickyScrollView;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.QuickPopupBuilder;
import razerdp.widget.QuickPopup;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficialGroupFragment extends Fragment implements View.OnClickListener{
    private MagicIndicator official_yihui_daohang;
    private InsideViewPager official_yihui_vp;
    private String []mtitle={"最新帖子","精华内容"};
    private List<Fragment>fragmentList;
    private TextView  official_yihui_sort;
    QuickPopup quickPopup;
    private View popuView;
    private TextView sort_huifu,sort_fabu;
    private ImageButton official_yihui_sent_topic;
    private StickyScrollView official_yihui_scrollview;
    int sent_topicX=0,sent_topicY=0;
    public OfficialGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_official_group, container, false);
        official_yihui_daohang=(MagicIndicator)v.findViewById(R.id.official_yihui_daohang);
        official_yihui_vp=(InsideViewPager)v.findViewById(R.id.official_yihui_vp);
        official_yihui_sort=(TextView)v.findViewById(R.id.official_yihui_sort);
        official_yihui_sort.setOnClickListener(this);
        quickPopup = QuickPopupBuilder.with(getContext()).contentView(R.layout.popu_sort).width(DeviceUtil.dp2px(getContext(),140)).height(DeviceUtil.dp2px(getContext(),120)).build();
        popuView=quickPopup.getContentView();
        sort_huifu=(TextView)popuView.findViewById(R.id.sort_huifu);
        sort_fabu=(TextView)popuView.findViewById(R.id.sort_fabu);
        official_yihui_scrollview=(StickyScrollView)v.findViewById(R.id.official_yihui_scrollview);
        sort_huifu.setOnClickListener(this);
        sent_topicX=DeviceUtil.deviceWidth(getContext())-DeviceUtil.dp2px(getContext(),70);
        sent_topicY=(DeviceUtil.deviceHeight(getContext())-DeviceUtil.dp2px(getContext(),100))*3/4;

        official_yihui_sent_topic=(ImageButton) v.findViewById(R.id.official_yihui_sent_topic);
        official_yihui_sent_topic.setTranslationX(sent_topicX);
        official_yihui_sent_topic.setTranslationY(sent_topicY);
        official_yihui_sent_topic.setOnClickListener(this);
        official_yihui_sent_topic.setTranslationZ(DeviceUtil.dp2px(getContext(),20));
        sort_huifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickPopup.dismiss();
                official_yihui_sort.setText("回复时间排序");
            }
        });
        official_yihui_scrollview.setOnScrollListener1(new StickyScrollView.OnScrollListener1() {
            @Override
            public void onScroll(int scrollY) {
                official_yihui_sent_topic.setTranslationY(sent_topicY+scrollY);
            }
        });
        sort_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickPopup.dismiss();
                official_yihui_sort.setText("发布时间排序");
            }
        });
        initMagicIndicator();
                return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.official_yihui_sort:
                if (official_yihui_sort.getText().toString().equals("回复时间排序")){
                    sort_huifu.setTextColor(getResources().getColor(R.color.douyucheng));
                    sort_fabu.setTextColor(getResources().getColor(R.color.blackaa));
                }else{
                    sort_fabu.setTextColor(getResources().getColor(R.color.douyucheng));
                    sort_huifu.setTextColor(getResources().getColor(R.color.blackaa));
                }
                quickPopup.showPopupWindow(view);
                break;
            case R.id.official_yihui_sent_topic://发帖
                break;
        }
    }


    public void initMagicIndicator(){
        CommonNavigator commonNavigator = new CommonNavigator(getContext());

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mtitle.length;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.douyucheng));
                colorTransitionPagerTitleView.setNormalColor(getResources().getColor(R.color.blackaa));
                colorTransitionPagerTitleView.setText(mtitle[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        official_yihui_vp.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.douyucheng));

                return indicator;
            }

        });
        official_yihui_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                official_yihui_daohang.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // Log.e("BaseFragment","onPageScrolled:"+ position);
            }
            @Override
            public void onPageSelected(int position) {
                official_yihui_daohang.onPageSelected(position);
                // Log.e("BaseFragment","onPageSelected:"+ position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                official_yihui_daohang.onPageScrollStateChanged(state);
                // Log.e("BaseFragment","onPageScrollStateChanged:"+ state);
            }
        });
        fragmentList=new ArrayList<>();
        fragmentList.add(new HomeVpFragment());
        fragmentList.add(new HomeVpFragment());
        official_yihui_daohang.setNavigator(commonNavigator);
        official_yihui_vp.setAdapter(new FragmentAdapter(getChildFragmentManager()));
        official_yihui_vp.setOffscreenPageLimit(2);
        official_yihui_vp.setCurrentItem(0);
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
            return fragmentList.size();
        }

    }

}
