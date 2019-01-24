package com.example.administrator.industrialmessagesbridge.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.DaoSession;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.LableTwoDao;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.industrialmessagesbridge.MainActivity;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LabelOne;
import com.example.administrator.industrialmessagesbridge.dataBaseData.LableTwo;
import com.example.administrator.industrialmessagesbridge.model.LableOneModel;
import com.example.administrator.industrialmessagesbridge.myView.MyTextView;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendNeedActivity extends BaseActivity implements View.OnClickListener{
@BindView(R.id.select_start_business_time)
LinearLayout select_start_business_time;
private  TimePickerView pvTime;
String []calendarTitle={"开标时间","完成时间","发布截止时间"};
@BindView(R.id.input_title)
    EditText input_title;//输入的标题
 @BindView(R.id.select_lable_ll)
    LinearLayout select_lable_ll;//选择分类
 @BindView(R.id.select_result)
    TextView select_result;//选择分类的结果
    @BindView(R.id.get_price_ll)
    LinearLayout get_price_ll;//输入金额
    @BindView(R.id.get_price_result)
    EditText get_price_result;//输入金额结果
  @BindView(R.id.select_start_business_time_result)
  TextView select_start_business_time_result;//选择开标时间的结果
  @BindView(R.id.end_bussiness_time_ll)
  LinearLayout end_bussiness_time_ll;//选择完成时间
    @BindView(R.id.end_bussiness_time_result)
    TextView end_bussiness_time_result;//选择完成时间的结果
    @BindView(R.id.need_person_result)
    EditText need_person_result;//输入需求人数
    @BindView(R.id.end_need_time_ll)
    LinearLayout end_need_time_ll;//选择发布截止时间
    @BindView(R.id.end_need_time_result)
    TextView end_need_time_result;//发布截止时间的结果
   @BindView(R.id.need_content_reuslt)
    MyTextView need_content_reuslt;//业务背景
    private Calendar curorDate=null;
    private Calendar endDate;
@BindView(R.id.submit_bn)
 Button submit_bn;
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.set_need_request_bn)
Button set_need_request_bn;
    private ArrayList<LableOneModel> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<LableTwo>> options2Items = new ArrayList<>();
    private List<LabelOne> labelOneList=new ArrayList<>();
    private List<LableTwo> lableTwoList=new ArrayList<>();
    private DaoSession daoSession;
    private OptionsPickerView optionsPickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_need);
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
        select_start_business_time.setOnClickListener(this);
        select_lable_ll.setOnClickListener(this);
        end_bussiness_time_ll.setOnClickListener(this);
        end_need_time_ll.setOnClickListener(this);
        submit_bn.setOnClickListener(this);
        set_need_request_bn.setOnClickListener(this);
        //设置日期
        Calendar startDate = Calendar.getInstance();
        Date todayDate = Calendar.getInstance().getTime();
        startDate.setTime(todayDate);
         endDate = Calendar.getInstance();
        endDate.set(2023,1,1);
         pvTime = new TimePickerBuilder(SendNeedActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
              switch (v.getId()){
                  case R.id.select_start_business_time:
                      select_start_business_time_result.setText(shortDateFormat.format(date));
                      if (curorDate==null)
                      curorDate=Calendar.getInstance();
                      curorDate.setTime(date);
                      break;
                  case R.id.end_need_time_ll:
                      end_need_time_result.setText(shortDateFormat.format(date));
                      break;
              }
            }
        }).setRangDate(startDate,endDate). isCyclic(true).build();

       initWheelData();
       initWheelView();
    }
public void initWheelData(){
    daoSession = ((MyApplication)MyApplication.getContext()).getDaoSession();
    labelOneList=daoSession.loadAll(LabelOne.class);
    lableTwoList=daoSession.loadAll(LableTwo.class);
    for (LabelOne l:labelOneList){
        options1Items.add(new LableOneModel(l.getLableId(),l.getName(),"hi","hi"));
    }
        for (LabelOne ll:labelOneList){
            ArrayList<LableTwo> lableTwos=new ArrayList<>();
            for (LableTwo l:lableTwoList){
                if (ll.getLableId().equals(l.getLableOneId())){
                    lableTwos.add(l);
                }
            }
            options2Items.add(lableTwos);
    }

}
public void initWheelView(){
    optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            //返回的分别是三个级别的选中位置
            String tx = options1Items.get(options1).getPickerViewText()
                    + options2Items.get(options1).get(options2).getPickerViewText()
                    /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
            select_result.setText(tx);
        }
    })
            .setTitleText("选择标签")
            .setContentTextSize(15)//设置滚轮文字大小
            .setDividerColor(Color.LTGRAY)//设置分割线的颜色
            .setSelectOptions(0, 1)//默认选中项
            .setBgColor(Color.WHITE)
            .setTitleBgColor(Color.parseColor("#FFEEEEF3"))
            .setTitleColor(Color.BLACK)
            .setCancelColor(Color.parseColor("#FF285BE8"))
            .setSubmitColor(Color.parseColor("#FF285BE8"))
            .setTextColorCenter(Color.BLACK)
            .setCyclic(true,true,true)
            .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .setLabels("", "","")
            .setBackgroundId(0x00000000) //设置外部遮罩颜色
            .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                @Override
                public void onOptionsSelectChanged(int options1, int options2, int options3) {

                }
            })
            .build();

//        pvOptions.setSelectOptions(1,1);
    /*pvOptions.setPicker(options1Items);//一级选择器*/
    optionsPickerView.setPicker(options1Items, options2Items);//二级选择器
    /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
}
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_start_business_time:
                pvTime.setTitleText(calendarTitle[0]);
                pvTime.show(select_start_business_time);
                break;
            case R.id.end_bussiness_time_ll:
                if (curorDate!=null) {
                    new TimePickerBuilder(SendNeedActivity.this, new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                    end_bussiness_time_result.setText(shortDateFormat.format(date));
                        }
                    }).setRangDate(curorDate,endDate).setTitleText(calendarTitle[1]).isCyclic(true).build().show();
                }else
                    Toast.makeText(SendNeedActivity.this,"请选择开始时间",Toast.LENGTH_SHORT).show();
                break;
            case R.id.end_need_time_ll:
                    pvTime.setTitleText(calendarTitle[2]);
                    pvTime.show(end_need_time_ll);
                break;
            case R.id.submit_bn:
                if(!TextUtils.isEmpty(input_title.getText().toString())
                        &&!TextUtils.isEmpty(get_price_result.getText().toString())
                        &&!TextUtils.isEmpty(end_need_time_result.getText().toString()) &&!TextUtils.isEmpty(need_content_reuslt.getText().toString())){
                    Toast.makeText(SendNeedActivity.this,"完成",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(SendNeedActivity.this,"输入红色星号的信息",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.select_lable_ll:
                optionsPickerView.show();
                break;
            case R.id.set_need_request_bn://设置调查问卷

                break;
        }
    }
}
