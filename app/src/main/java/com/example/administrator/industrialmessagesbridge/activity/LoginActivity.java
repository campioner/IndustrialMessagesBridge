package com.example.administrator.industrialmessagesbridge.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
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

import com.example.administrator.industrialmessagesbridge.MainActivity;
import com.example.administrator.industrialmessagesbridge.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
@BindView(R.id.back)
    ImageButton back;
@BindView(R.id.login_method_tv)
    TextView login_method_tv;
@BindView(R.id.login_verify_ll)
    LinearLayout login_verify_ll;
@BindView(R.id.login_password_ll)
    LinearLayout login_password_ll;
@BindView(R.id.login_text_tv)
   TextView login_text_tv;
@BindView(R.id.login_login_bn)
com.gc.materialdesign.views.ButtonRectangle login_login_bn;
@BindView(R.id.login_phone_number)
EditText login_phone_number;//验证码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);

        login_password_ll.setVisibility(View.GONE);
        login_method_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=login_method_tv.getText().toString();
                if(text.equals("手机号验证")){
                    login_text_tv.setText("手机验证码登录");
                    login_method_tv.setText("账号密码登录");
                    login_verify_ll.setVisibility(View.VISIBLE);
                    login_password_ll.setVisibility(View.GONE);
                }else{
                    login_text_tv.setText("账号密码登录");
                    login_method_tv.setText("手机号验证");
                    login_verify_ll.setVisibility(View.GONE);
                    login_password_ll.setVisibility(View.VISIBLE);
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
               Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
