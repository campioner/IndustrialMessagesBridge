package com.example.administrator.industrialmessagesbridge.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoMaster;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LabelOne;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment {
    private DaoSession daoSession;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    final String[] mTitles = {"关注", "话题"};
    private ViewPager mAdapter;
    private  MagicIndicator magicIndicator;
    List<View> viewList = new ArrayList<View>();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    List<Fragment>fragmentList;
    public TopicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_topic, container, false);
        magicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        mAdapter=(android.support.v4.view.ViewPager) view.findViewById(R.id.view_pager);
        //获得daoSession实例
        getDaoSession();
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return 2;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(mTitles[index]);

                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAdapter.setCurrentItem(index);
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
        magicIndicator.setNavigator(commonNavigator);

        mAdapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // Log.e("BaseFragment","onPageScrolled:"+ position);
            }
            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
                // Log.e("BaseFragment","onPageSelected:"+ position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
                // Log.e("BaseFragment","onPageScrollStateChanged:"+ state);
            }
        });
        fragmentList=new ArrayList<>();
        fragmentList.add(new AttentionFragment());
        fragmentList.add(new TopicContentFragment());
        mAdapter.setAdapter(new FragmentAdapter(getChildFragmentManager()));
        ViewPagerHelper.bind(magicIndicator, mAdapter);
        return view;
    }
    public void getDaoSession(){

        //输入数据
        pref= PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        editor=pref.edit();
        daoSession = ((MyApplication)MyApplication.getContext()).getDaoSession();

        if (!pref.getBoolean("cantInsert",false)) {
            editor.putBoolean("cantInsert",true).commit();
            //一级标签
            LabelOne labelOne;
            labelOne = new LabelOne();
            labelOne.setLableId("1");
            labelOne.setName(" 农、林、牧、渔业");
            daoSession.insert(labelOne);

            LableTwo lableTwo;
             lableTwo=new LableTwo();
            lableTwo.setLableOneId("1");
            lableTwo.setLableTwoId("1");
            lableTwo.setName("养殖");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("1");
            lableTwo.setLableTwoId("2");
            lableTwo.setName("砍树");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("1");
            lableTwo.setLableTwoId("3");
            lableTwo.setName("放羊");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("1");
            lableTwo.setLableTwoId("4");
            lableTwo.setName("抓电鳗");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("1");
            lableTwo.setLableTwoId("5");
            lableTwo.setName("其他");
            daoSession.insert(lableTwo);

            labelOne = new LabelOne();
            labelOne.setLableId("2");
            labelOne.setName("采矿业");
            daoSession.insert(labelOne);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("2");
            lableTwo.setLableTwoId("6");
            lableTwo.setName("黄金矿工");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("2");
            lableTwo.setLableTwoId("7");
            lableTwo.setName("白银矿工");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("2");
            lableTwo.setLableTwoId("8");
            lableTwo.setName("青铜矿工");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("2");
            lableTwo.setLableTwoId("9");
            lableTwo.setName("星耀矿工");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("2");
            lableTwo.setLableTwoId("10");
            lableTwo.setName("王者矿工");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("2");
            lableTwo.setLableTwoId("11");
            lableTwo.setName("其他");
            daoSession.insert(lableTwo);

            labelOne = new LabelOne();
            labelOne.setLableId("3");
            labelOne.setName("制造业");
            daoSession.insert(labelOne);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("3");
            lableTwo.setLableTwoId("12");
            lableTwo.setName("造人");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("3");
            lableTwo.setLableTwoId("13");
            lableTwo.setName("造房子");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("3");
            lableTwo.setLableTwoId("14");
            lableTwo.setName("造卫星");
            daoSession.insert(lableTwo);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("3");
            lableTwo.setLableTwoId("15");
            lableTwo.setName("其他");
            daoSession.insert(lableTwo);

            labelOne = new LabelOne();
            labelOne.setLableId("4");
            labelOne.setName("电力、热力、燃气及水生产和供应业");
            daoSession.insert(labelOne);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("4");
            lableTwo.setLableTwoId("16");
            lableTwo.setName("其他");
            daoSession.insert(lableTwo);

            labelOne = new LabelOne();
            labelOne.setLableId("5");
            labelOne.setName("建筑业");
            daoSession.insert(labelOne);

            lableTwo=new LableTwo();
            lableTwo.setLableOneId("5");
            lableTwo.setLableTwoId("17");
            lableTwo.setName("其他");
            daoSession.insert(lableTwo);
        }
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
               return 2;
           }

       }
}
