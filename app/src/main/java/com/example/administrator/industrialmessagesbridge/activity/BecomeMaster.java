package com.example.administrator.industrialmessagesbridge.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.activityDao.BecomeMasterDao;
import com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment.BecomeMasterFourFragment;
import com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment.BecomeMasterOneFragment;
import com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment.BecomeMasterThreeFragment;
import com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment.BecomeMasterTwoFragment;
import com.example.administrator.industrialmessagesbridge.myView.RoundCircle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BecomeMaster extends BaseActivity implements View.OnClickListener,BecomeMasterDao{
@BindView(R.id.becomeMaster_ll)
    LinearLayout becomeMaster_ll;
@BindView(R.id.becomeMaster_view2)//第二个横线
View view2;
@BindView(R.id.becomeMaster_view3)
View view3;//第三个横线


@BindView(R.id.becomeMaster_roundView2)
    RoundCircle becomeMaster_roundView2;//第二个圆圈
@BindView(R.id.becomeMaster_roundView3)
   RoundCircle becomeMaster_roundView3;//第三个圆圈

    @BindView(R.id.becomeMaster_roundView1)
    RoundCircle becomeMaster_roundView1;
private FragmentManager fragmentManager;
private FragmentTransaction fragmentTransaction;
private BecomeMasterOneFragment becomeMasterOneFragment;
private BecomeMasterTwoFragment becomeMasterTwoFragment;
private BecomeMasterThreeFragment becomeMasterThreeFragment;
private BecomeMasterFourFragment becomeMasterFourFragment;
private int position=0;
@BindView(R.id.becomeMaster_view4)
View becomeMaster_view4;

    @BindView(R.id.becomeMaster_titile)
    TextView becomeMaster_titile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_master);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        //显示第一个fragment
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        becomeMasterOneFragment=new BecomeMasterOneFragment();
        fragmentTransaction.replace(R.id.becomeMaster_ll,becomeMasterOneFragment);
        fragmentTransaction.commit();
        //acvitity与Fragment之间的通信
        becomeMasterOneFragment.setBecomeMasterDao(this);


    }
//初始化选择器

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

//第一个fragment的下一步按钮
    @Override
    public void fragmentOneOnclick(View view) {

     view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //判断数据是否为空
            if (!TextUtils.isEmpty(becomeMasterOneFragment.getInput_componey().getText().toString())&&
                     !TextUtils.isEmpty(becomeMasterOneFragment.getInput_name().getText().toString())&&
                     !TextUtils.isEmpty(becomeMasterOneFragment.getInput_work_position().getText().toString())&&
                     !TextUtils.isEmpty(becomeMasterOneFragment.getInput_work_time().getText().toString())&&
                     !TextUtils.isEmpty(becomeMasterOneFragment.getSelect_address_tv().getText().toString())&&
                     !TextUtils.isEmpty(becomeMasterOneFragment.getSelect_sex_tv().getText().toString())){
             }else{
                 Toast.makeText(BecomeMaster.this,"填写空格",Toast.LENGTH_SHORT).show();
                 return;
             }
             position=1;
             //刷新头部
             initTopView();
             //下一个界面
             fragmentManager=getSupportFragmentManager();
             becomeMasterTwoFragment=new BecomeMasterTwoFragment();
             becomeMasterTwoFragment.setBecomeMasterDao(BecomeMaster.this);
             fragmentTransaction=fragmentManager.beginTransaction();
             fragmentTransaction.replace(R.id.becomeMaster_ll,becomeMasterTwoFragment);
             fragmentTransaction.commit();
             becomeMaster_titile.setText("从事方向");
         }
     });
    }

    @Override
    public void fragmentTwoOnclick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=2;
                //刷新头部
                initTopView();
                //下一个界面
                fragmentManager=getSupportFragmentManager();
                becomeMasterThreeFragment=new BecomeMasterThreeFragment();
                becomeMasterThreeFragment.setBecomeMasterDao(BecomeMaster.this);
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.becomeMaster_ll,becomeMasterThreeFragment);
                fragmentTransaction.commit();
                becomeMaster_titile.setText("个人资料");
            }
        });
    }

    @Override
    public void fragmentThreeOnclick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=3;
                //刷新头部
                initTopView();
                //下一个界面
                fragmentManager=getSupportFragmentManager();
                becomeMasterFourFragment=new BecomeMasterFourFragment();
                becomeMasterFourFragment.setBecomeMasterDao(BecomeMaster.this);
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.becomeMaster_ll,becomeMasterFourFragment);
                fragmentTransaction.commit();
                becomeMaster_titile.setText("个人资料");
            }
        });

    }

    @Override
    public void fragmentFourOnclick(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public void initTopView(){
      switch (position){
            case 1:
                becomeMaster_roundView1.setCanDrawArc(true);
                becomeMaster_roundView1.setBlack(false);
                becomeMaster_roundView1.invalidate();
                view2.setBackgroundResource(R.color.baise);
                break;
            case 2:
                becomeMaster_roundView2.setBlack(false);
                becomeMaster_roundView2.setCanDrawArc(true);
                becomeMaster_roundView2.invalidate();
                view3.setBackgroundResource(R.color.baise);

                break;
            case 3:
                becomeMaster_roundView3.setBlack(false);
                becomeMaster_roundView3.setCanDrawArc(true);
                becomeMaster_view4.setBackgroundResource(R.color.baise);
                becomeMaster_roundView3.invalidate();
                break;
        }
    }
}
