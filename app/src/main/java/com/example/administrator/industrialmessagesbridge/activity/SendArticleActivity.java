package com.example.administrator.industrialmessagesbridge.activity;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.example.administrator.industrialmessagesbridge.myView.CustomRichEditor;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;
import me.shaohui.advancedluban.Luban;
import razerdp.basepopup.QuickPopupBuilder;
import razerdp.widget.QuickPopup;

public class SendArticleActivity extends TakePhotoActivity implements View.OnClickListener{
private TakePhoto takePhoto;
@BindView(R.id.select_photo)
    ImageButton select_photo;
    EditText article_content;

@BindView(R.id.editor)
CustomRichEditor mEditor;
@BindView(R.id.lianjie)
ImageButton lianjie;
    private boolean mHasAddImg = false;
private   CompressConfig compressConfig;
private QuickPopup quickPopup;
private EditText linkEt;
private EditText linkDescribe;
private Button linkConfrimBn,linkCancleBn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_article);
        ButterKnife.bind(this);
        select_photo.setOnClickListener(this);
        lianjie.setOnClickListener(this);
         takePhoto=getTakePhoto();
        compressConfig=new CompressConfig.Builder()
                .setMaxSize(DeviceUtil.deviceWidth(this)*DeviceUtil.deviceHeight(this))
                .setMaxPixel(800)
                .create();
        //初始化编辑高度
        mEditor.setEditorHeight(200);
        //初始化字体大小
        mEditor.setEditorFontSize(22);
        //初始化字体颜色
        mEditor.setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //初始化内边距
        mEditor.setPadding(10, 10, 10, 10);
        //设置编辑框背景，可以是网络图片
        // mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        // mEditor.setBackgroundColor(Color.BLUE);
        //设置默认显示语句
        mEditor.setPlaceholder("Insert text here...");
        //设置编辑器是否可用
        mEditor.setInputEnabled(true);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
              //这个text就是所输入的所有文件 他是一个html格式的
                if (mHasAddImg) {
                    mHasAddImg = false;
                    mEditor.setNewLine();
                }
            }
        });
        initquickPopup();
    }
    public void initquickPopup(){
        quickPopup= QuickPopupBuilder.with(this)
                .contentView(R.layout.input_link)
                .build();
       View popupView= quickPopup.getContentView();
        linkEt=(EditText)popupView.findViewById(R.id.link_content);
        linkDescribe=(EditText)popupView.findViewById(R.id.link_name);
        linkConfrimBn=(Button)popupView.findViewById(R.id.linkConfrimBn);
        linkCancleBn=(Button)popupView.findViewById(R.id.linkCancleBn);
        linkConfrimBn.setOnClickListener(this);
        linkCancleBn.setOnClickListener(this);
    }
    @Override
    public void takeSuccess(TResult result) {
        String path= result.getImage().getCompressPath();
        mEditor.insertImage(path,"picvision\"style=\"max-width:100%");
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_photo:
                takePhoto.onEnableCompress(compressConfig,true);
                takePhoto.onPickFromGallery();
                break;
            case  R.id.lianjie:
                quickPopup.showPopupWindow();
                break;
            case R.id.linkCancleBn:
                quickPopup.dismiss();
                break;
            case R.id.linkConfrimBn:
                if (!TextUtils.isEmpty(linkDescribe.getText().toString()))
                mEditor.insertLink(linkEt.getText().toString(),linkDescribe.getText().toString());
                else
                mEditor.insertLink(linkEt.getText().toString(),linkEt.getText().toString());
                quickPopup.dismiss();
                break;
        }
    }
}
