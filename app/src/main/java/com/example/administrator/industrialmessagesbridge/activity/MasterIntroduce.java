package com.example.administrator.industrialmessagesbridge.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.myView.MyScrollView;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MasterIntroduce extends BaseActivity implements View.OnClickListener{
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.expand_more)
    TextView expand_more;//展开更多
    @BindView(R.id.introduce_more)
    TextView introduce_more;
    @BindView(R.id.back_expand_more)
        TextView back_expand_more;//收起
    @BindView(R.id.master_introduce)
    MyScrollView master_introduce;
    @BindView(R.id.master_background)
    LinearLayout master_background;
    private float y0=0,y1=0,deiveWeight;
    @BindView(R.id.bottom_flote_ll)
    LinearLayout bottom_flote_ll;
    private ViewGroup.LayoutParams layoutParams;
    float heigth,weight;
    @BindView(R.id.bottom_flote_ll2)
    LinearLayout bottom_flote_ll2;
    @BindView(R.id.detail_introduce)
    LinearLayout detail_introduce;
    Scroller scroller;
    ObjectAnimator objectAnimator;
    @BindView(R.id.master_photo)
    CircleImageView master_photo;
    int h,hightTop,hightBottom;//悬浮top和底部坐标
    private boolean isGet=false;
    int[] location1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_introduce);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        simple_toolbar.setLeftTitleDrawable(R.drawable.arrow_right);
        simple_toolbar.setRightTitleDrawable(R.drawable.toorbar_add);
        expand_more.setOnClickListener(this);
        back_expand_more.setOnClickListener(this);
        introduce_more.setVisibility(View.GONE);
        back_expand_more.setVisibility(View.GONE);
        deiveWeight=DeviceUtil.deviceWidth(this);
        layoutParams=master_background.getLayoutParams();
        heigth= layoutParams.height;
        weight=layoutParams.width;
        //底部悬浮
        int deviceHeight=DeviceUtil.deviceHeight(this);
        hightTop= h=DeviceUtil.deviceHeight(this)-DeviceUtil.dp2px(this,100);
        int[] location = new  int[2] ;
        bottom_flote_ll.setTranslationY(h);
        bottom_flote_ll2.getLocationInWindow(location);
        master_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        scroller=new Scroller(this);
        initScrollListener();
    }
    public void initScrollListener(){
    master_introduce.setOnScrollListener(new MyScrollView.OnScrollListener() {
        @Override
        public void onScroll(int scrollY) {
         //   Log.i("msg1",  scrollY+"scrollY");
            if (h+scrollY>hightBottom){
                h=hightBottom;
            }
           else  if (h+scrollY<hightTop){
                h=hightTop;
            }
          //  Log.i("msg1",  h+scrollY+"h+scrollY");
            bottom_flote_ll.setTranslationY(h+scrollY);
        }
    });
        findViewById(R.id.master_introduce_rv).setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    y0=event.getRawY();
                    y1=y0;
                //    Log.i("msg1",y1+"y0");
                    break;
                case MotionEvent.ACTION_MOVE:
                    y1=event.getRawY();
                    int l=master_introduce.getScrollY();
                    float distancex=y1 - y0;
                    float distancey = y1 - y0;
                    if ( (y1 - y0)>0) {
                        l=-l;
                        layoutParams.height = (int) (heigth + distancey);
                        layoutParams.width = (int) (deiveWeight + distancex);
                        master_background.setLayoutParams(layoutParams);
                        Log.i("msg1",distancey+"");
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y1=y0=0;
                    layoutParams.height=(int)heigth;
                    layoutParams.width=(int)weight;
                    master_background.setLayoutParams(layoutParams);
                    break;
            }
            return true;
        }
    });
}
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //切换出去view坐标会改变
        if (!isGet) {
            location1 = new int[2];
            bottom_flote_ll2.getLocationInWindow(location1);
            //获取在当前窗口内的绝对坐标
            hightBottom = location1[1] + DeviceUtil.dp2px(this, 50);
            isGet=true;
        }
        Log.i("msg1",""+location1[1]);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expand_more:
               expand_more.setVisibility(View.GONE);
               introduce_more.setVisibility(View.VISIBLE);
               back_expand_more.setVisibility(View.VISIBLE);
               break;
            case R.id.back_expand_more:
                back_expand_more.setVisibility(View.GONE);
                expand_more.setVisibility(View.VISIBLE);
                introduce_more.setVisibility(View.GONE);
                break;
        }
    }
}
