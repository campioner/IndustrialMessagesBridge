package com.example.administrator.industrialmessagesbridge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.Dialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingPersonInfo extends BaseActivity {
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.mTxtMiddleTitle)
    TextView mTxtMiddleTitle;
@BindView(R.id.person_content)
TextView person_content;
private int code=0;
private boolean canShowDialog=true;
        Dialog dialog;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_person_info);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setSupportActionBar(simple_toolbar);
        simple_toolbar.setSetImageViewBar(new SimpleToolbar.SetImageViewBar() {
            @Override
            public void setImageViewBar() {
                dialog.show();
            }
        });
    dialog = new Dialog(this,title, "是否保存");
        // Set accept click listenner
        dialog.setOnAcceptButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baocun();
                dialog.dismiss();
                finish();
            }
        });
// Set cancel click listenner
        dialog.setOnCancelButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
finish();
            }
        });
         dialog.addCancelButton("取消");
        dialog.create();
        ButtonFlat buttonFlat=(ButtonFlat)dialog.getButtonAccept();
        buttonFlat.setText("确定");
        code=getIntent().getExtras().getInt("type");
         //0专家介绍 1专家经验
        switch (code){
            case 0:
                title="个人介绍";
                mTxtMiddleTitle.setText("个人介绍");
                break;
            case 1:
                title="工作经历";
                mTxtMiddleTitle.setText("工作经历");
                break;
            case 2:
                mTxtMiddleTitle.setText("签名设置");
                break;
        }
    }
public void baocun(){
    Intent intent=new Intent();
    Bundle bundle=new Bundle();
    bundle.putString("result",person_content.getText().toString());
    intent.putExtras(bundle);
    setResult(code,intent);
}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (canShowDialog) {
                dialog.show();
                canShowDialog=false;
            }else {
                canShowDialog = true;
                dialog.dismiss();
            }

            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
