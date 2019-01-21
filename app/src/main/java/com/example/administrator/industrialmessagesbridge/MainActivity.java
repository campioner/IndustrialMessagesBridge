package com.example.administrator.industrialmessagesbridge;



import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.industrialmessagesbridge.activity.BaseActivity;
import com.example.administrator.industrialmessagesbridge.activity.BecomeMaster;
import com.example.administrator.industrialmessagesbridge.activity.ConfirmName;
import com.example.administrator.industrialmessagesbridge.activity.MasterIntroduce;

import com.example.administrator.industrialmessagesbridge.activity.SendArticleActivity;
import com.example.administrator.industrialmessagesbridge.activity.SendNeedActivity;
import com.example.administrator.industrialmessagesbridge.activity.histroy.HistroyInPosts;
import com.example.administrator.industrialmessagesbridge.fragment.HomeFragment;
import com.example.administrator.industrialmessagesbridge.fragment.MessageFragment;
import com.example.administrator.industrialmessagesbridge.fragment.SettingFragment;
import com.example.administrator.industrialmessagesbridge.fragment.TopicFragment;
import com.example.administrator.industrialmessagesbridge.myView.MoreWindow;
import com.example.administrator.industrialmessagesbridge.myView.MyPopup;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;

import com.mikepenz.materialdrawer.model.BaseSecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.basepopup.QuickPopupConfig;
import razerdp.widget.QuickPopup;

public class MainActivity extends BaseActivity implements View.OnClickListener{
   // final String[] mTitles = {"主页", "通讯录","","话题","设置"};
    List<Integer>bottomIcon;
    List<Integer>bottomIconBlack;
   private FragmentTransaction fragmentTransaction;
   private FragmentManager fragmentManager;
   private MoreWindow mMoreWindow;
   private HomeFragment homeFragment;
   private MessageFragment messageFragment;
   private SettingFragment settingFragment;
   private TopicFragment topicFragment;
   @BindView(R.id.home_bn)
    ImageButton home_bn;
    @BindView(R.id.mine_bn)
    ImageButton mine_bn;
    @BindView(R.id.send_bn)
    ImageButton send_bn;
    @BindView(R.id.topic_bn)
    ImageButton topic_bn;
    @BindView(R.id.setting_bn)
    ImageButton setting_bn;
    @BindView(R.id.home_tv)
    TextView home_tv;
    @BindView(R.id.mine_tv)
    TextView mine_tv;
    @BindView(R.id.topic_tv)
    TextView topic_tv;
    @BindView(R.id.seting_tv)
    TextView seting_tv;
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
    private boolean canRotation=false;
    LinearLayout send_need_ll;
    LinearLayout check_need_ll;
    LinearLayout one_ll;
    LinearLayout two_ll;
    @BindView(R.id.tv_researchhh)
    TextView tv_researchhh;
    private ImageButton popuib;

    QuickPopup quickPopup;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View v=null;
            switch (msg.what){
                case 0:
                    v=send_need_ll;
                    transtY(v);
                    break;
                case 1:
                    v=one_ll;
                    transtY(v);
                    break;
                case 2:
                    v=two_ll;
                    transtY(v);
                    break;
                case 3:
                    v=check_need_ll;
                    transtY(v);
                    break;
                case 4:
                    AnimatorSet set=new AnimatorSet();
                    ObjectAnimator o2 = ObjectAnimator.ofFloat(popuib, "rotation", 0,-45);
                    ObjectAnimator o3 = ObjectAnimator.ofFloat(popuib, "rotation", 0);
                    o2.setDuration(0);
                    o3.setDuration(1000);
                    set.playSequentially(o2,o3);
                    set.start();

            }

        }
    };
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ButterKnife.bind(this);
         setSupportActionBar(simple_toolbar);
         getSupportActionBar().setTitle("");
         //搜索框内容设置
        initResearch();
        //初始化底部菜单数据
        initData();
        //初始化侧滑抽屉
         initChouti();
         //初始化fragment
         simple_toolbar.setSetSearchBar(new SimpleToolbar.SetSearchBar() {
             @Override
             public void setSearchBar() {

             }
         });
         simple_toolbar.setSetImageViewBar(new SimpleToolbar.SetImageViewBar() {
             @Override
             public void setImageViewBar() {

             }
         });
         findViewById(R.id.home_ll).setOnClickListener(this);
         findViewById(R.id.mine_ll).setOnClickListener(this);
         findViewById(R.id.send_ll).setOnClickListener(this);
         findViewById(R.id.topic_ll).setOnClickListener(this);
         findViewById(R.id.setting_ll).setOnClickListener(this);
         initPopuWindow();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        homeFragment=new HomeFragment();
        fragmentTransaction.replace(R.id.mian_ll,homeFragment);
        fragmentTransaction.commit();
    }
public void initResearch(){
    Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.search);
    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    // 计算缩放比例
    float scaleWidth = ((float)DeviceUtil.dp2px(this,17)) /width;
    float scaleHeight = ((float) DeviceUtil.dp2px(this,17)) / height;
    // 取得想要缩放的matrix参数
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);
    bitmap=Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    //根据bitmap对象创建imageSpan对象
    ImageSpan imageSpan=new ImageSpan(this, bitmap);
    SpannableString spannableString=new SpannableString("icon搜索");
    spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    tv_researchhh.setText(spannableString);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
       if (R.id.send_ll!=v.getId())
        reset();
        switch (v.getId()){
            case R.id.home_ll:
                select(0);
                break;
            case R.id.mine_ll:
                select(1);
                break;
            case R.id.send_ll:
                select(2);
                break;
            case R.id.topic_ll:
                select(3);
                break;
            case R.id.setting_ll:
                select(4);
                break;

        }
        fragmentTransaction.commit();
    }
public void reset(){
    home_bn.setImageResource(bottomIconBlack.get(0));
    mine_bn.setImageResource(bottomIconBlack.get(1));
    topic_bn.setImageResource(bottomIconBlack.get(3));
    setting_bn.setImageResource(bottomIconBlack.get(4));
    home_tv.setTextColor(Color.BLACK);
    mine_tv.setTextColor(Color.BLACK);
    topic_tv.setTextColor(Color.BLACK);
    seting_tv.setTextColor(Color.BLACK);

}
    public void initChouti(){

//create the drawer and remember the `Drawer` result
////个人信息，实名认证，企业认证,专家收藏，文档收藏，发帖记录，成为专家，推广，预约记录，文档记录,
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withHeader(LayoutInflater.from(this).inflate(R.layout.drawer_header, null, true)).withFooter(R.layout.drawer_footer)
                .withToolbar(simple_toolbar)
                //启用toolbar的ActionBarDrawerToggle动画
                .withActionBarDrawerToggleAnimated(true)
                .withDisplayBelowStatusBar(false)
                .withTranslucentStatusBar(false)
                .addDrawerItems(

                        new PrimaryDrawerItem().withIcon(R.drawable.person_info).withName("个人信息"),
                        new PrimaryDrawerItem().withIcon(R.drawable.person_comfirm).withName("实名认证"),
                        new PrimaryDrawerItem().withIcon(R.drawable.company_comfirm).withName("企业认证"),
                        new PrimaryDrawerItem().withIcon(R.drawable.store_master).withName("专家收藏"),
                        new PrimaryDrawerItem().withIcon(R.drawable.document_store).withName("文档收藏"),
                        new PrimaryDrawerItem().withIcon(R.drawable.send_tiezi_history).withName("发帖记录"),
                        new PrimaryDrawerItem().withIcon(R.drawable.drawer_become_master).withName("成为专家"),
                        new PrimaryDrawerItem().withIcon(R.drawable.appointment).withName("预约记录"),
                        new PrimaryDrawerItem().withIcon(R.drawable.send_teizi).withName("文档记录"),
                        new PrimaryDrawerItem().withIcon(R.drawable.tuiguang).withName("推广")

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position){
                            case 1://个人信息
                                break;
                            case 2://实名认证
                                Intent intent2=new Intent(MainActivity.this, ConfirmName.class);
                                startActivity(intent2);
                                break;
                            case 3://企业认证
                                break;
                            case 4://专家收藏
                                break;
                            case 5://文档收藏
                                break;
                            case 6://发帖记录
                                Intent intent6=new Intent(MainActivity.this, HistroyInPosts.class);
                                startActivity(intent6);
                                break;
                            case 7://成为专家
                                Intent intent7=new Intent(MainActivity.this,BecomeMaster.class);
                                startActivity(intent7);
                                break;
                            case 8://预约记录
                                break;
                            case 9://文档记录
                                break;
                            case 10://推广
                                break;
                        }
                        return false;
                    }
                })
                .build();

        //boomMenu
    }
    public void initData(){
        bottomIcon=new ArrayList<>();
        bottomIcon.add(R.drawable.home);
        bottomIcon.add(R.drawable.chat);
        bottomIcon.add(R.drawable.boom_menu);
        bottomIcon.add(R.drawable.buluo);
        bottomIcon.add(R.drawable.message_two);
        bottomIconBlack=new ArrayList<>();
        bottomIconBlack.add(R.drawable.home_black);
        bottomIconBlack.add(R.drawable.chat_black);
        bottomIconBlack.add(R.drawable.boom_menu);
        bottomIconBlack.add(R.drawable.buluo_black);
        bottomIconBlack.add(R.drawable.mmessage);
    }

    //选择后
    public void select(int position){
        switch (position){
            case 0:
                fragmentTransaction.replace(R.id.mian_ll,homeFragment);
                home_bn.setImageResource(bottomIcon.get(0));
                home_tv.setTextColor(Color.parseColor("#FF5267D9"));
                simple_toolbar.setMainTitle("");
                tv_researchhh.setVisibility(View.VISIBLE);
               // simple_toolbar.showSerchBar();
                ronate();
                break;
            case 1:
                if (messageFragment==null)
                    messageFragment=new MessageFragment();
                mine_bn.setImageResource(bottomIcon.get(1));
                mine_tv.setTextColor(Color.parseColor("#FF5267D9"));
                fragmentTransaction.replace(R.id.mian_ll,messageFragment);
                simple_toolbar.setMainTitle("通讯录");
                tv_researchhh.setVisibility(View.GONE);
                //simple_toolbar.hideSerchBar();
                ronate();
                break;
            case  2:
                quickPopup.showPopupWindow();
                popuWindow();
                break;
            case 3:
                if(topicFragment==null)
                    topicFragment=new TopicFragment();
                topic_bn.setImageResource(bottomIcon.get(3));
                topic_tv.setTextColor(Color.parseColor("#FF5267D9"));
                simple_toolbar.setMainTitle("讨论");
                tv_researchhh.setVisibility(View.GONE);
                fragmentTransaction.replace(R.id.mian_ll,topicFragment);
                ronate();
                break;
            case 4:
                if(settingFragment==null)
                    settingFragment=new SettingFragment();
                setting_bn.setImageResource(bottomIcon.get(4));
                seting_tv.setTextColor(Color.parseColor("#FF5267D9"));
                fragmentTransaction.replace(R.id.mian_ll,settingFragment);
               // simple_toolbar.hideSerchBar();
                tv_researchhh.setVisibility(View.GONE);
                simple_toolbar.setMainTitle("消息");
                ronate();
                break;
        }
    }
    public void initPopuWindow(){
        quickPopup=QuickPopupBuilder.with(this)
                .contentView(R.layout.activity_popu_window)
                .build();
        View popuView=quickPopup.getContentView();
         send_need_ll=(LinearLayout)popuView.findViewById(R.id.send_need_ll);
         check_need_ll=(LinearLayout) popuView.findViewById(R.id.check_need_ll);
         one_ll=(LinearLayout)popuView.findViewById(R.id.one_ll);
         two_ll=(LinearLayout)popuView.findViewById(R.id.two_ll);
         popuib=(ImageButton)popuView.findViewById(R.id.popuib);
         LinearLayout popu_ll=(LinearLayout)popuView.findViewById(R.id.popu_ll);
        popu_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickPopup.dismiss();
            }
        });
        send_need_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, SendNeedActivity.class);
                startActivity(intent);
            }
        });
        check_need_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        one_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        two_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this, SendArticleActivity.class);
            startActivity(intent);
            }
        });
        popuib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickPopup.dismiss();
            }
        });
     }
    public void popuWindow(){
        handler.sendEmptyMessageDelayed(4,0);
        handler.sendEmptyMessageDelayed(0,0);
        handler.sendEmptyMessageDelayed(1,200);
        handler.sendEmptyMessageDelayed(2,400);
        handler.sendEmptyMessageDelayed(3,500);
    }
    public void ronate(){
      /*  if (canRotation){
            canRotation=false;
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator o1 = ObjectAnimator.ofFloat(send_bn, "rotation", 0, -45F);
            set.play(o1);
            set.setDuration(1000);
            set.start();
        }*/
    }
    public void transtY(View v){
        AnimatorSet set=new AnimatorSet();
        ObjectAnimator o3 = ObjectAnimator.ofFloat(v, "translationY", 0, -DeviceUtil.dp2px(this,180));
        ObjectAnimator o4 = ObjectAnimator.ofFloat(v, "translationY", -DeviceUtil.dp2px(this,180), -DeviceUtil.dp2px(this,165));
        o3.setDuration(500);
        o4.setDuration(400);
        set.playSequentially(o3,o4);
        set.start();
    }
}
