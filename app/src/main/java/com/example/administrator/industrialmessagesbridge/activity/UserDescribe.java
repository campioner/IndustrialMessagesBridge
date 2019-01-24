package com.example.administrator.industrialmessagesbridge.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.UserShowInfo;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.vondear.rxui.view.dialog.RxDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDescribe extends BaseActivity implements View.OnClickListener{
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.user_image)
CircleImageView user_image;
@BindView(R.id.user_name)
    TextView user_name;
@BindView(R.id.user_attention)
TextView user_attention;
@BindView(R.id.user_fans)
TextView user_fans;
@BindView(R.id.user_lv)
TextView user_lv;
@BindView(R.id.user_see_count)
TextView user_see_count;
@BindView(R.id.set_user_qianming)
    LinearLayout set_user_qianming;
@BindView(R.id.user_qianming)
TextView user_qianming;
@BindView(R.id.user_edit_name)
EditText user_edit_name;
@BindView(R.id.user_true_name)
EditText user_true_name;
@BindView(R.id.user_work_time)
EditText user_work_time;
@BindView(R.id.user_company)
EditText user_company;
@BindView(R.id.user_work_position)
EditText user_work_position;
@BindView(R.id.set_user_position)
LinearLayout set_user_position;
@BindView(R.id.user_position)
EditText user_position;
@BindView(R.id.set_user_sex)
LinearLayout set_user_sex;
@BindView(R.id.user_sex)
TextView user_sex;
@BindView(R.id.hide_all)
ImageView hide_all;
@BindView(R.id.hide_name)
ImageView hide_name;
@BindView(R.id.hide_company)
    ImageView hide_company;
@BindView(R.id.hide_work_position)
ImageView hide_work_position;
@BindView(R.id.hide_position)
ImageView hide_position;
@BindView(R.id.hine_fatiejilu)
ImageView hine_fatiejilu;
private UserShowInfo userShowInfo;
@BindView(R.id.store_user_info)
TextView store_user_info;
private RxDialog rxDialog;
private boolean isShow=false;
@BindView(R.id.set_user_show)
LinearLayout set_user_show;
@BindView(R.id.show_hide_iv)
ImageView show_hide_iv;
@BindView(R.id.isShowLl)
LinearLayout isShowLl;
@BindView(R.id.more_group_bar_back)
ImageButton more_group_bar_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_describe);
        ButterKnife.bind(this);
        setSupportActionBar(simple_toolbar);
        getSupportActionBar().setTitle("");
        userShowInfo=new UserShowInfo(1,"兔子","",100,100,1,"你的剑就是我的剑","不知道","100","公司","经理",1,0,1,1,0,0,0,1000,"hhhh");
        user_name.setText(userShowInfo.getUserName());
        user_attention.setText(userShowInfo.getUserAttentionCount().toString());
        user_fans.setText(userShowInfo.getUserFansCount().toString());
        user_lv.setText(userShowInfo.getUserLevel().toString());
        user_see_count.setText(userShowInfo.getUserSeeCount().toString());
        user_qianming.setText(userShowInfo.getUserQianMing());
        user_edit_name.setText(userShowInfo.getUserName());
        user_true_name.setText(userShowInfo.getUserTrueName());
        user_work_time.setText(userShowInfo.getUserWorkTime());
        user_company.setText(userShowInfo.getUserCompany());
        user_work_position.setText(userShowInfo.getUserWorkPosition());
        user_position.setText(userShowInfo.getUserPosition());
        if (userShowInfo.getUserSex()==0)
            user_sex.setText("女");
        else
            user_sex.setText("男");
        if (userShowInfo.getIsHideAll()==1)
            hide_all.setImageResource(R.drawable.bn_up);
        if (userShowInfo.getIsHideName()==1)
            hide_name.setImageResource(R.drawable.bn_up);
        if (userShowInfo.getIsHideCompany()==1)
        hide_company.setImageResource(R.drawable.bn_up);
        if (userShowInfo.getIsHideWorkPosition()==1)
        hide_work_position.setImageResource(R.drawable.bn_up);
        if (userShowInfo.getIsHidePosition()==1)
        hide_position.setImageResource(R.drawable.bn_up);
        if (userShowInfo.getUserFansCount()==1)
        hine_fatiejilu.setImageResource(R.drawable.bn_up);

        hide_all.setOnClickListener(this);
        hide_name.setOnClickListener(this);
        hide_company.setOnClickListener(this);
        hide_work_position.setOnClickListener(this);
        hide_position.setOnClickListener(this);
        hine_fatiejilu.setOnClickListener(this);
        store_user_info.setOnClickListener(this);
        set_user_show.setOnClickListener(this);
        set_user_sex.setOnClickListener(this);
        more_group_bar_back.setOnClickListener(this);
        initSexSelect();
        set_user_qianming.setOnClickListener(this);
        isShowLl.setVisibility(View.GONE);
    }
    public void initSexSelect(){
        rxDialog=new RxDialog(this);
        View v= LayoutInflater.from(this).inflate(R.layout.sex_select,null,false);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        TextView select_man=(TextView)v.findViewById(R.id.select_man);
        TextView select_woman=(TextView)v.findViewById(R.id.select_woman);
        select_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_sex.setText("男");
                rxDialog.dismiss();
            }
        });
        select_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_sex.setText("女");
                rxDialog.dismiss();
            }
        });
        rxDialog.addContentView(v,layoutParams);
        rxDialog.create();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2){
            user_qianming.setText(data.getExtras().getString("result"));
        }
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.hide_all:
              if (userShowInfo.getIsHideAll()==1){
                  hide_all.setImageResource(R.drawable.bn_close);
                  userShowInfo.setIsHideAll(0);
              }else{
                  hide_all.setImageResource(R.drawable.bn_up);
                  userShowInfo.setIsHideAll(1);
              }
              break;
          case R.id.hide_name:
              if (userShowInfo.getIsHideName()==1){
                  hide_name.setImageResource(R.drawable.bn_close);
                  userShowInfo.setIsHideName(0);
              }else{
                  hide_name.setImageResource(R.drawable.bn_up);
                  userShowInfo.setIsHideName(1);
              }
              break;
          case R.id.hide_company:
              if (userShowInfo.getIsHideCompany()==1){
                  hide_company.setImageResource(R.drawable.bn_close);
                  userShowInfo.setIsHideCompany(0);
              }else{
                  hide_company.setImageResource(R.drawable.bn_up);
                  userShowInfo.setIsHideCompany(1);
              }
              break;
          case R.id.hide_work_position:
              if (userShowInfo.getIsHideWorkPosition()==1){
                  hide_work_position.setImageResource(R.drawable.bn_close);
                  userShowInfo.setIsHideWorkPosition(0);
              }else{
                  hide_work_position.setImageResource(R.drawable.bn_up);
                  userShowInfo.setIsHideWorkPosition(1);
              }
              break;
          case R.id.hide_position:
              if (userShowInfo.getIsHidePosition()==1){
                  hide_position.setImageResource(R.drawable.bn_close);
                  userShowInfo.setIsHidePosition(0);
              }else{
                  hide_position.setImageResource(R.drawable.bn_up);
                  userShowInfo.setIsHidePosition(1);
              }
              break;
          case R.id.hine_fatiejilu:
              if (userShowInfo.getIsHideFatiejilu()==1){
                  hine_fatiejilu.setImageResource(R.drawable.bn_close);
                  userShowInfo.setIsHideFatiejilu(0);
              }else{
                  hine_fatiejilu.setImageResource(R.drawable.bn_up);
                  userShowInfo.setIsHideFatiejilu(1);
              }
              break;
          case R.id.store_user_info:

              break;
          case R.id.set_user_sex:
              rxDialog.show();
              break;
          case R.id.set_user_qianming:
              Intent intent=new Intent(this,SettingPersonInfo.class);
              Bundle bundle=new Bundle();
              bundle.putInt("type",2);
             intent.putExtras(bundle);
              startActivityForResult(intent,2);
              break;
          case R.id.set_user_show:
              int letter=0,after=45;
              if (!isShow) {
                  isShow=true;
                  letter=0;
                  after=90;
                  isShowLl.setVisibility(View.VISIBLE);
                  hine_fatiejilu.requestFocus();
              }
              else{
                  isShow=false;
                  letter=90;
                  after=0;
                  isShowLl.setVisibility(View.GONE);
              }
              AnimatorSet set = new AnimatorSet();
              ObjectAnimator o2 = ObjectAnimator.ofFloat(show_hide_iv, "rotation", letter, after);
              o2.setDuration(500);
              set.play(o2);
              set.start();
              break;
          case R.id.more_group_bar_back:
              finish();
              break;
      }
    }
}
