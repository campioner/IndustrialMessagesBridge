package com.example.administrator.industrialmessagesbridge.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.BaseFragmentPagerAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.NeedShowAdapter;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.NeedShow;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LabelOne;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;
import com.example.administrator.industrialmessagesbridge.fragment.SettingFragment;
import com.example.administrator.industrialmessagesbridge.fragment.ShowMasterFragment;
import com.example.administrator.industrialmessagesbridge.fragment.TopicShowFragment;
import com.example.administrator.industrialmessagesbridge.myView.MyPopup;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;


import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import razerdp.basepopup.BasePopupWindow;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.blur.PopupBlurOption;

public class BusinessNeed extends BaseActivity {
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
   private List<LableTwoShow>lableTwoList;//二级标签
    private QuickPopupBuilder quickPopupBuilder;
    private MyPopup myPopup;
    private  View rootView;
    private List<LabelOne>labelOneList;
    private List<LableTwo>lableTwoList2;
    private DaoSession daoSession;
    private ListView lable2_lv;
    @BindView(R.id.mTxtMiddleTitle)
    TextView mTxtMiddleTitle;
    private int lableTwoCount=0;
@BindView(R.id.magic_indicator)
MagicIndicator magic_indicator;
@BindView(R.id.sort_sp)
    Spinner sort_sp;
@BindView(R.id.message_bar)
ImageView message_bar;
private Lable1_lvAdapter lable1_lvAdapter;
private boolean canRefresh=false;
private int vpPosition=0;
    private  List<NeedShow>needShowList;
    private   LinearLayoutManager linearLayoutManager;
    private int oldPosition=0;
    @BindView(R.id.show_business_need_vp)
    ViewPager show_business_need_vp;
    private List<Fragment> fragmentList;
    private   FragmentAdapter fragmentAdapter;
   private   ViewPager.OnPageChangeListener onPageChangeListener;
   private int homeIntent;
private  List<LableTwo> lableTwoListt;
private BaseFragmentPagerAdapter baseFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_need);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Bundle bundle=getIntent().getExtras();
        homeIntent=bundle.getInt("home_intent");
        setSupportActionBar(simple_toolbar);
        simple_toolbar.setSetImageViewBar(new SimpleToolbar.SetImageViewBar() {
            @Override
            public void setImageViewBar() {
                finish();
            }
        });

        lableTwoList=new ArrayList<>();
        lableTwoList.add(new LableTwoShow("抓电鳗",true));
        lableTwoList.add(new LableTwoShow("烫头",false));
        lableTwoList.add(new LableTwoShow("喝酒",false));
        lableTwoList.add(new LableTwoShow("吸烟",false));
        lableTwoList.add(new LableTwoShow("打牌",false));
        mTxtMiddleTitle.setText(lableTwoList.get(0).name);
        initPopuwidow();
        initDaoHang();
        initNeedRecycleView();

    }
    public CommonNavigator buildNavigator(){
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return lableTwoList.size();
            }
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);

                clipPagerTitleView.setText(lableTwoList.get(index).name);
                clipPagerTitleView.setTextColor(Color.WHITE);
                clipPagerTitleView.setClipColor(Color.BLACK);

                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        show_business_need_vp.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.WHITE);
                indicator.setLineWidth(DeviceUtil.dp2px(context,90));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }

        });
        return commonNavigator;
    }
    public void initDaoHang(){
        magic_indicator.setNavigator(buildNavigator());
    }
    public class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Bundle bundle=new Bundle();
            bundle.putString("next",""+i);
            bundle.putInt("master",homeIntent);
            if (fragmentList.get(i)==null){
                ShowMasterFragment showMasterFragmentA = new ShowMasterFragment();
                showMasterFragmentA.setArguments(bundle);
                fragmentList.add(showMasterFragmentA);
            }
            bundle.putString("lableOneId",lableTwoListt.get(i).getLableOneId());
            bundle.putString("lableTwoId",lableTwoListt.get(i).getLableTwoId());
            fragmentList.get(i).setArguments(bundle);
            return fragmentList.get(i);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
    public void inputIntentBundle(Fragment fragment,int position){
        Bundle bundle=new Bundle();
        bundle.putString("next",""+position);
        bundle.putInt("master",homeIntent);
        bundle.putString("lableOneId",lableTwoListt.get(position).getLableOneId());
        bundle.putString("lableTwoId",lableTwoListt.get(position).getLableTwoId());
        fragment.setArguments(bundle);
    }
    public void initNeedRecycleView(){
       fragmentList=new ArrayList<>();
        for (int i=0;i<5;i++) {
            ShowMasterFragment showMasterFragmentA=new ShowMasterFragment();
            WeakReference<ShowMasterFragment> weakMasterFragmentA = new WeakReference<ShowMasterFragment>(showMasterFragmentA);
            inputIntentBundle(weakMasterFragmentA.get(),i);
            fragmentList.add(weakMasterFragmentA.get());
        }
        baseFragmentPagerAdapter=new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        show_business_need_vp.setAdapter(baseFragmentPagerAdapter);
        show_business_need_vp.setCurrentItem(0);
        //缓存
        show_business_need_vp.setOffscreenPageLimit(2);
        addChangeListener();

    }
    public void addChangeListener(){
        onPageChangeListener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magic_indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magic_indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magic_indicator.onPageScrollStateChanged(state);
            }
        };
                show_business_need_vp.addOnPageChangeListener(onPageChangeListener) ;
    }
    public class LableTwoShow{
        public LableTwoShow(String name,boolean statue){
            this.name=name;
            this.statue=statue;
        }
        String name;
        boolean statue;
    }

    public void initPopuwidow(){
        myPopup=new MyPopup(this,4);
        myPopup.setShowAnimation(myPopup.getShowAnimation2());
        myPopup.setDismissAnimation(myPopup.getDismissAnimation2());
        rootView=myPopup.getContentView();
        //放入popuWindow数据
        inputDataInMyPopup();
        ((ImageButton)findViewById(R.id.select_lable_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myPopup.showPopupWindow(view);
            }
        });
    }
    public void inputDataInMyPopup(){
       ListView lable1_lv= (ListView) rootView.findViewById(R.id.lable1_lv);
       lable2_lv= (ListView) rootView.findViewById(R.id.lable2_lv);
       //获得标签数
        daoSession = ((MyApplication)MyApplication.getContext()).getDaoSession();
        daoSession = ((MyApplication)MyApplication.getContext()).getDaoSession();
        labelOneList=daoSession.loadAll(LabelOne.class);
        lableTwoList2=daoSession.loadAll(LableTwo.class);
        lableTwoListt=new ArrayList<>();
        for (LableTwo l:lableTwoList2)
            if (l.getLableOneId().equals("1")) {
                lableTwoCount++;
                lableTwoListt.add(l);
            }
        lable1_lvAdapter=new Lable1_lvAdapter();
        lable1_lv.setAdapter(lable1_lvAdapter);
    }

    public class Lable1_lvAdapter extends BaseAdapter{

        private TextView textViewBefore=null;
        @Override
        public int getCount() {
            return labelOneList.size();
        }

        @Override
        public Object getItem(int i) {
            return labelOneList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
          final   TextView textView=new TextView(BusinessNeed.this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#FF000000"));
            final String labelOneId=labelOneList.get(i).getLableId();
            textView.setHeight(DeviceUtil.dp2px(BusinessNeed.this,50));
            textView.setText(labelOneList.get(i).getName());
            if (i==0){
                lableTwoCount=0;
                lableTwoListt=new ArrayList<>();
                for (LableTwo l:lableTwoList2)
                    if (l.getLableOneId().equals(labelOneId)) {
                        lableTwoCount++;
                        lableTwoListt.add(l);
                    }
                lable2_lv.setAdapter(new Lable2_lvAdapter());
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (textViewBefore!=null)
                        textViewBefore.setBackgroundColor(Color.parseColor("#FFFCFCFB"));
                    textView.setBackgroundColor(Color.parseColor("#ffffffff"));
                    lableTwoListt=null;
                    lableTwoCount=0;
                    lableTwoListt=new ArrayList<>();
                    for (LableTwo l:lableTwoList2)
                        if (l.getLableOneId().equals(labelOneId)) {
                            lableTwoListt.add(l);
                            lableTwoCount++;
                        }
                    lable2_lv.setAdapter(new Lable2_lvAdapter());
                    textViewBefore=textView;
                }
            });
            return textView;
        }
    }
    public class Lable2_lvAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return lableTwoListt.size();
        }

        @Override
        public Object getItem(int i) {
            return lableTwoListt.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            TextView textView=new TextView(BusinessNeed.this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#FF000000"));
            textView.setHeight(DeviceUtil.dp2px(BusinessNeed.this,50));
            textView.setText(lableTwoListt.get(i).getName());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTxtMiddleTitle.setText(lableTwoListt.get(i).getName());
                    lableTwoList.clear();
                    List<Fragment>fragmentListCopy;
                    fragmentListCopy=fragmentList;
                    fragmentList=new ArrayList<>();
                   for (int f=0;f<fragmentListCopy.size();f++){
                       baseFragmentPagerAdapter.removeFragment(fragmentListCopy.get(i));
                    }

                    for(int p=0;p<lableTwoListt.size();p++){
                        if (p==i)
                            lableTwoList.add(new LableTwoShow(lableTwoListt.get(p).getName(),true));
                        else
                            lableTwoList.add(new LableTwoShow(lableTwoListt.get(p).getName(),false));
                        ShowMasterFragment showMasterFragmentA=new ShowMasterFragment();
                        WeakReference<ShowMasterFragment> weakMasterFragmentA = new WeakReference<ShowMasterFragment>(showMasterFragmentA);
                        inputIntentBundle(weakMasterFragmentA.get(),p);
                        baseFragmentPagerAdapter.insertFragment(p,weakMasterFragmentA.get());
                       // fragmentList.add(weakMasterFragmentA.get());
                    };
                    myPopup.dismiss();
                    magic_indicator.setNavigator(buildNavigator());
                    magic_indicator.onPageSelected(i);
                    show_business_need_vp.setCurrentItem(i);
                 //   handler.sendEmptyMessageDelayed(1,0);
                }
            });
            return textView;
        }
    }
    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;

    /**
     * 滑动到指定位置
     */


}
