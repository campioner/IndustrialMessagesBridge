package com.example.administrator.industrialmessagesbridge.fragment.becomeMasterFragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.activity.activityDao.BecomeMasterDao;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.vondear.rxui.view.dialog.RxDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BecomeMasterOneFragment extends TakePhotoFragment{
private Button nextBn;
private BecomeMasterDao becomeMasterDao;
RxDialog rxDialog;
    private LinearLayout select_address_ll,select_sex_ll;
    private TextView select_address_tv;//地址
    private CityPicker mCP;
    private TextView select_sex_tv;//性别
    private EditText input_name;//姓名
    private EditText input_work_time;//工作年限
    private EditText input_componey;//公司
    private EditText input_work_position;//工作职位
    private TakePhoto takePhoto;
    private ImageView select_photo;
    public BecomeMasterOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_become_master_one, container, false);
        nextBn=(Button)view.findViewById(R.id.nextBn1);
        select_address_ll=(LinearLayout)view.findViewById(R.id.select_address_ll);
        select_address_tv=(TextView)view.findViewById(R.id.select_address_tv);
        select_sex_tv=(TextView)view.findViewById(R.id.select_sex_tv);
        select_sex_ll=(LinearLayout)view.findViewById(R.id.select_sex_ll);
        input_name=(EditText)view.findViewById(R.id.input_name);
        input_work_time=(EditText)view.findViewById(R.id.input_work_time);
        input_componey=(EditText)view.findViewById(R.id.input_componey);
        input_work_position=(EditText)view.findViewById(R.id.input_work_position);
        select_photo=(ImageView)view.findViewById(R.id.select_photo);
        takePhoto=getTakePhoto();
        select_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto.onPickFromGallery();
            }
        });

        becomeMasterDao.fragmentOneOnclick(nextBn);
        mYunCityPicher();
        //性别选择
        initSexSelect();
        select_address_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCP.show();
            }
        });
        select_sex_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.show();
            }
        });
        return view;
    }

    @Override
    public void takeSuccess(TResult result) {
        TImage timage=result.getImage();
        String r=timage.getOriginalPath();
        Glide.with(getActivity()).load(r).into(select_photo);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    public TextView getSelect_address_tv() {
        return select_address_tv;
    }

    public TextView getSelect_sex_tv() {
        return select_sex_tv;
    }

    public EditText getInput_name() {
        return input_name;
    }

    public EditText getInput_work_time() {
        return input_work_time;
    }

    public EditText getInput_componey() {
        return input_componey;
    }

    public EditText getInput_work_position() {
        return input_work_position;
    }

    public void initSexSelect(){
     rxDialog=new RxDialog(getContext());
    View v=LayoutInflater.from(getContext()).inflate(R.layout.sex_select,null,false);
     LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
     TextView select_man=(TextView)v.findViewById(R.id.select_man);
     TextView select_woman=(TextView)v.findViewById(R.id.select_woman);
     select_man.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             select_sex_tv.setText("男");
             rxDialog.dismiss();
         }
     });
     select_woman.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             select_sex_tv.setText("女");
             rxDialog.dismiss();
         }
     });
     rxDialog.addContentView(v,layoutParams);
     rxDialog.create();

 }
    public void setBecomeMasterDao(BecomeMasterDao becomeMasterDao) {
        this.becomeMasterDao = becomeMasterDao;
    }
    public static String getJson(String fileName,Context context) {
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
    public void mYunCityPicher() {
        mCP = new CityPicker.Builder(getContext())
                .textSize(20)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        //监听
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];
                select_address_tv.setText(province + city + district);
            }

            @Override
            public void onCancel() {
            }
        });
    }

}
