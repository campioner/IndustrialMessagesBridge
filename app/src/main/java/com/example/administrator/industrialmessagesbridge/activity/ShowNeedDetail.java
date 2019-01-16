package com.example.administrator.industrialmessagesbridge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.NeedShow;
import com.example.administrator.industrialmessagesbridge.myView.MyTextView;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowNeedDetail extends BaseActivity implements View.OnClickListener{
@BindView(R.id.message_bar)
    ImageView message_bar;
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.need_title)
    TextView need_title;
@BindView(R.id.need_position)
TextView need_position;
@BindView(R.id.need_price)
TextView need_price;
@BindView(R.id.company_name)
TextView company_name;
@BindView(R.id.is_company_confirm)
TextView is_company_confirm;
@BindView(R.id.is_company_confirm_iv)
ImageView is_company_confirm_iv;
@BindView(R.id.send_need_person_name)
TextView send_need_person_name;
@BindView(R.id.is_person_comfirm)
TextView is_person_comfirm;
@BindView(R.id.is_person_comfirm_iv)
ImageView is_person_comfirm_iv;
@BindView(R.id.need_background)
TextView need_background;
@BindView(R.id.business_start_time)
TextView business_start_time;
@BindView(R.id.business_end_time)
TextView business_end_time;
@BindView(R.id.need_time)
TextView need_time;
@BindView(R.id.require_person)
TextView require_person;
@BindView(R.id.need_person)
TextView need_person;
@BindView(R.id.need_deadline)
TextView need_deadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_need_detail);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setSupportActionBar(simple_toolbar);
        simple_toolbar.setSetImageViewBar(new SimpleToolbar.SetImageViewBar() {
            @Override
            public void setImageViewBar() {
                finish();
            }
        });
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            NeedShow needShow=bundle.getParcelable("needShow");
            need_title.setText(needShow.getNeed_title());
            need_position.setText(needShow.getNeed_position());
            need_price.setText(needShow.getNeed_price());
            need_background.setText(needShow.getNeed_background());
            need_time.setText(needShow.getNeed_time());
            need_deadline.setText(needShow.getNeed_deadline());
            require_person.setText(needShow.getRequire_person());
            need_person.setText(needShow.getNeed_person());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //oncreate控件会开始进行mesure会onlayout，控件绘画好才开始调用post
        need_background.post(new Runnable() {
            @Override
            public void run() {
               int height= need_background.getHeight();
                if (height< DeviceUtil.dp2px(ShowNeedDetail.this,290))
                    need_background.setHeight(DeviceUtil.dp2px(ShowNeedDetail.this,290));
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
