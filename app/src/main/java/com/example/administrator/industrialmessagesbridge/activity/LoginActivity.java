package com.example.administrator.industrialmessagesbridge.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.industrialmessagesbridge.MainActivity;
import com.example.administrator.industrialmessagesbridge.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
@BindView(R.id.back)
    ImageButton back;
@BindView(R.id.login_method_tv)
    TextView login_method_tv;

@BindView(R.id.login_password_ll)
    LinearLayout login_password_ll;
@BindView(R.id.login_text_tv)
   TextView login_text_tv;
@BindView(R.id.login_login_bn)
com.gc.materialdesign.views.ButtonRectangle login_login_bn;
@BindView(R.id.login_phone_number)
EditText login_phone_number;
@BindView(R.id.login_phone_number_ll)
LinearLayout login_phone_number_ll;
@BindView(R.id.login_divice)
View login_divice;
private boolean flag=false,isLogin=false;
private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        login_phone_number_ll.setVisibility(View.GONE);
        login_password_ll.setVisibility(View.GONE);
        login_password_ll.setVisibility(View.GONE);
        login_divice.setVisibility(View.GONE);
        login_method_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=login_method_tv.getText().toString();
                if(text.equals("手机号验证")){
                    login_text_tv.setText("手机验证码登录");
                    login_method_tv.setText("账号密码登录");
                    login_divice.setVisibility(View.GONE);
                    login_phone_number_ll.setVisibility(View.GONE);
                    login_password_ll.setVisibility(View.GONE);
                    flag=false;
                }else{
                    login_text_tv.setText("账号密码登录");
                    login_method_tv.setText("手机号验证");
                    login_divice.setVisibility(View.VISIBLE);
                    login_password_ll.setVisibility(View.VISIBLE);
                    login_phone_number_ll.setVisibility(View.VISIBLE);
                    flag=true;
                }
            }
        });
        login_login_bn.setOnClickListener(this);
        getAllPermission();
    }
    public void getAllPermission(){//获得权限
        try{

            PackageInfo pack = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissionStrings = pack.requestedPermissions;
            for(String permissionString:permissionStrings){
                int permission = ActivityCompat.checkSelfPermission(this, permissionString);
                int REQUEST_PERMISSION = 1;
                if(permission!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,permissionStrings,REQUEST_PERMISSION);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                    messageRequire(phone);

                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login_bn:
              /*  OkGo.<String>get("http://111.230.71.119:8080/ewisdomsink-customer-mobile-0.0.1-SNAPSHOT/Mlogin/MessageLogin.do")
                        .tag(this).params("user_phone","13856130325").execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    String re=response.body();
                    String q=re;
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });*/
             // sendCode(getApplicationContext());
               Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            //showProgressLoading();
          /*   if (!flag){
                  sendCode(getApplicationContext());
              }*/
                break;
        }
    }
    public void messageRequire(String phone){
        showProgressLoading();
        OkGo.<String>get("http://111.230.71.119:8080/ewisdomsink-customer-mobile-0.0.1-SNAPSHOT/Mlogin/MessageLogin.do")
                .tag(this).params("user_phone",phone).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.body());
                    JSONObject j=jsonObject.getJSONObject("MessageLogin");
                    j.getInt("login");
                } catch (JSONException e) {
                    isLogin=true;
                    e.printStackTrace();
                }
                if (isLogin){
                    hideProcessLoading();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    isLogin=false;
                }else
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            hideProcessLoading();
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    });


            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        hideProcessLoading();
                        Toast.makeText(LoginActivity.this,"请求失败,请检查网络",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
