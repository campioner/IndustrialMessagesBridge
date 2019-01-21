package com.example.administrator.industrialmessagesbridge.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.MoreGroupActivity;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LabelOne;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;
import com.example.administrator.industrialmessagesbridge.fragment.groupFragment.MyGroupFragment;
import com.example.administrator.industrialmessagesbridge.fragment.groupFragment.OfficialGroupFragment;
import com.example.administrator.industrialmessagesbridge.fragment.groupFragment.RecommandGroupFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment implements View.OnClickListener{
    private DaoSession daoSession;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    final String[] mTitles = {"推荐", "我的","e汇圈"};
    private ViewPager mAdapter;
    private  MagicIndicator magicIndicator;
    List<View> viewList = new ArrayList<View>();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    List<Fragment>fragmentList;
    private List<TextView> textViewList;
    private ImageView topic_fragment_more;
    int r=0;
    public TopicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_topic, container, false);
        textViewList=new ArrayList<>();
        magicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        mAdapter=(android.support.v4.view.ViewPager) view.findViewById(R.id.view_pager);
        topic_fragment_more=(ImageView)view.findViewById(R.id.topic_fragment_more);

        //获得daoSession实例
        getDaoSession();
        CommonNavigator commonNavigator = new CommonNavigator(getContext());

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return 3;
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(getContext());
                commonPagerTitleView.setBackgroundColor(getResources().getColor(R.color.barcolor));
                commonPagerTitleView.setContentView(R.layout.group_pager_title_view_item);
               final TextView textView=(TextView) commonPagerTitleView.findViewById(R.id.commond_select_tv);
                textView.setTextColor(Color.parseColor("#FFF6F6F6"));
                textView.setTextSize(15);
                textView.setText(mTitles[index]);
                textViewList.add(textView);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int i, int i1) {

                    }

                    @Override
                    public void onDeselected(int i, int i1) {


                    }

                    @Override
                    public void onLeave(int i, int i1, float v, boolean b) {

                    }

                    @Override
                    public void onEnter(int i, int i1, float v, boolean b) {

                    }
                });
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.setCurrentItem(index);
                    }
                });


                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        textViewList.get(0).setTextSize(18);
        textViewList.get(0).setTextColor(Color.parseColor("#ffffffff"));
        mAdapter.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // Log.e("BaseFragment","onPageScrolled:"+ position);
            }
            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
                reset();
                textViewList.get(position).setTextSize(18);
                textViewList.get(position).setTextColor(Color.parseColor("#ffffffff"));
                // Log.e("BaseFragment","onPageSelected:"+ position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

                magicIndicator.onPageScrollStateChanged(state);
                // Log.e("BaseFragment","onPageScrollStateChanged:"+ state);
            }
        });
        fragmentList=new ArrayList<>();
        fragmentList.add(new RecommandGroupFragment());
        fragmentList.add(new MyGroupFragment());
        fragmentList.add(new OfficialGroupFragment());
        mAdapter.setAdapter(new FragmentAdapter(getChildFragmentManager()));
        topic_fragment_more.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.topic_fragment_more:
             Intent intent=new Intent(getActivity(), MoreGroupActivity.class);
             startActivity(intent);
                break;
        }
    }

    public void reset(){
        for (int i=0;i<textViewList.size();i++){
            textViewList.get(i).setTextSize(15);
            textViewList.get(i).setTextColor(Color.parseColor("#ffffffff"));
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
               return fragmentList.size();
           }

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
}
