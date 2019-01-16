package com.example.administrator.industrialmessagesbridge.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.example.administrator.industrialmessagesbridge.sign.Base64Util;
import com.example.administrator.industrialmessagesbridge.sign.HMACSHA1;
import com.example.administrator.industrialmessagesbridge.utils.Config;
import com.example.administrator.industrialmessagesbridge.utils.Youtu;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.gson.JsonObject;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmName extends TakePhotoActivity {
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.select_user_photo)
    ImageButton select_user_photo;
@BindView(R.id.select_user_photo2)
        ImageButton select_user_photo2;
TakePhoto takePhoto;
private String r1,r2;
    private String APP_ID = "";
    private String SECRET_ID = "";
    private String SECRET_KEY = "";
    private Bitmap bitmap1,bitmap2;
    private boolean trueOrFalse=false;
    String r;
    @BindView(R.id.confirm_name_bn)
    ButtonRectangle confirm_name_bn;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            /*if (msg.what==1) {
                try {
                    bitmap1 = BitmapFactory.decodeFile(r);
                    r1 = bitmapToBase64(bitmap1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    bitmap2 = BitmapFactory.decodeFile(r);
                    r2 = bitmapToBase64(bitmap2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_name);
        ButterKnife.bind(this);
        takePhoto=getTakePhoto();
        APP_ID = Config.APP_ID;
        SECRET_ID = Config.SECRET_ID;
        SECRET_KEY = Config.SECRET_KEY;
        select_user_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueOrFalse=true;
                takePhoto.onPickFromGallery();
            }
        });
        select_user_photo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trueOrFalse=false;
                takePhoto.onPickFromGallery();
            }
        });
        confirm_name_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    r1=bitmapToBase64(bitmap1);
                    r2=bitmapToBase64(bitmap2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new Thread(){
                    @Override
                    public void run() {
                        String plain_text="u="+"1076589754"+"&a="+APP_ID+"&k="+SECRET_ID+"&e="+System.currentTimeMillis() / 1000 + 2592000+"&t="+System.currentTimeMillis() / 1000+"&r"+ Math.abs(new Random().nextInt())+ "&f=";

                        byte[] bin = hashHmac(plain_text, SECRET_KEY);
                        byte[] all = new byte[bin.length + plain_text.getBytes().length];
                        System.arraycopy(bin, 0, all, 0, bin.length);
                        System.arraycopy(plain_text.getBytes(), 0, all, bin.length, plain_text.getBytes().length);
                       OkGo.<String>post("http://api.youtu.qq.com/youtu/api/facecompare").headers("Authorization", Base64Util.encode(all))
                                .tag(this).params("app_id",APP_ID).params("imageA",r1).params("imageB",r2).execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String re=response.body();
                                Log.d("msg1",re);
                            }
                            @Override
                            public void onError(Response<String> response) {
                                response.code();
                                int r=response.code();
                                int t=r;
                                super.onError(response);
                            }
                        });
                    }
                }.start();;

            }
        });
    }
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        TImage timage=result.getImage();
        r=timage.getOriginalPath();
        if (trueOrFalse) {
            Glide.with(getApplicationContext()).load(r).into(select_user_photo);
            handler.sendEmptyMessage(1);
        }else{
            Glide.with(getApplicationContext()).load(r).into(select_user_photo2);
            handler.sendEmptyMessage(2);
        }
    }
        /**
         * bitmap转为base64
         * @param bitmap
         * @return
         */
    public static String bitmapToBase64(Bitmap bitmap) throws IOException {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return result;
    }
    private static byte[] hashHmac(String plain_text, String accessKey) {

        try {

            return HMACSHA1.getSignature(plain_text, accessKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }
}
