package com.example.administrator.industrialmessagesbridge.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.AppointMasterDetail;
import com.example.administrator.industrialmessagesbridge.myView.MyScrollView;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointMasterDetailActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
    @BindView(R.id.more_group_bar_back)
    ImageButton more_group_bar_back;

    @BindView(R.id.appoint_master_detail_bottom_ll)
    LinearLayout appoint_master_detail_bottom_ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_master_detail);
        ButterKnife.bind(this);
        setSupportActionBar(simple_toolbar);
        more_group_bar_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_group_bar_back:
                finish();

        }
    }
}
