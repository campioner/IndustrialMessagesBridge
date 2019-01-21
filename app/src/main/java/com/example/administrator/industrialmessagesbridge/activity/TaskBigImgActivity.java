package com.example.administrator.industrialmessagesbridge.activity;

import android.app.usage.UsageEvents;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bm.library.PhotoView;
import com.example.administrator.industrialmessagesbridge.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskBigImgActivity extends AppCompatActivity {
@BindView(R.id.photo_view_pager)
    ViewPager photo_view_pager;
private List<Integer> list=new ArrayList<>();
boolean isMove=false;
List<View>viewList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_big_img);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        Bundle bundle=getIntent().getExtras();
        list=(ArrayList<Integer>)bundle.get("photoList");
        for (int i=0;i<list.size();i++){
            if (list.get(i)==0) {
                list.remove(i);
                i--;
                continue;
            }
            PhotoView photoView=new PhotoView(TaskBigImgActivity.this);
            photoView.enable();
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            photoView.setLayoutParams(layoutParams);
            photoView.setImageResource(list.get(i));
            photoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            isMove=false;
                            break;
                        case MotionEvent.ACTION_UP:
                            if (!isMove){
                                finish();
                            }
                            break;
                        case MotionEvent.ACTION_MOVE:
                            isMove=true;
                            break;
                    }
                    return false;
                }
            });

            viewList.add(photoView);
        }
        photo_view_pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
            // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            @Override
            public void destroyItem(ViewGroup view, int position, Object object) {
                view.removeView(viewList.get(position));//删除页卡
            }

            // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
            @Override
            public Object instantiateItem(ViewGroup view, int position) {
                view.addView(viewList.get(position));//添加页卡
                return viewList.get(position);
            }
        });

    }


    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return super.onKeyLongPress(keyCode, event);
    }
}
