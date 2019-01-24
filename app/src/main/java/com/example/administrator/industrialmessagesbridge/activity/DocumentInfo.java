package com.example.administrator.industrialmessagesbridge.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.fragment.DocumentShow;
import com.example.administrator.industrialmessagesbridge.fragment.groupFragment.AllGroupFragmet;
import com.example.administrator.industrialmessagesbridge.fragment.groupFragment.ShowGroupFragment;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;

import net.lucode.hackware.magicindicator.MagicIndicator;
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

public class DocumentInfo extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
    @BindView(R.id.more_group_bar_back)
    ImageButton more_group_bar_back;
    @BindView(R.id.more_group_magic_indicator)
    MagicIndicator more_group_magic_indicator;
    @BindView(R.id.more_group_magic_vp)
    ViewPager more_group_magic_vp;
    private List<Fragment> fragmentList;
    private String mTitle[]={"我的文档","收藏的文档","购买的文档"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_info);
        ButterKnife.bind(this);
        setSupportActionBar(simple_toolbar);
        getSupportActionBar().setTitle("");
        more_group_bar_back.setOnClickListener(this);
        //初始化头部导航
        initMagicIndicator();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_group_bar_back:
                finish();
                break;
        }
    }
    public void initMagicIndicator(){
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitle.length;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.barcolor));
                colorTransitionPagerTitleView.setText(mTitle[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        more_group_magic_vp.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.barcolor));
                return indicator;
            }

        });
        more_group_magic_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                more_group_magic_indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // Log.e("BaseFragment","onPageScrolled:"+ position);
            }
            @Override
            public void onPageSelected(int position) {
                more_group_magic_indicator.onPageSelected(position);
                // Log.e("BaseFragment","onPageSelected:"+ position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                more_group_magic_indicator.onPageScrollStateChanged(state);
                // Log.e("BaseFragment","onPageScrollStateChanged:"+ state);
            }
        });
        fragmentList=new ArrayList<>();
        DocumentShow documentShow=new DocumentShow();
        Bundle bundle1=new Bundle();
        bundle1.putInt("document_type",0);
        documentShow.setArguments(bundle1);
        fragmentList.add(documentShow);
        DocumentShow documentShow2=new DocumentShow();
        Bundle bundle2=new Bundle();
        bundle2.putInt("document_type",1);
        documentShow2.setArguments(bundle2);
        fragmentList.add(documentShow2);
        DocumentShow documentShow3=new DocumentShow();
        Bundle bundle3=new Bundle();
        bundle3.putInt("document_type",2);
        documentShow3.setArguments(bundle3);
        fragmentList.add(documentShow3);
        more_group_magic_indicator.setNavigator(commonNavigator);
        more_group_magic_vp.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        more_group_magic_vp.setCurrentItem(0);
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
