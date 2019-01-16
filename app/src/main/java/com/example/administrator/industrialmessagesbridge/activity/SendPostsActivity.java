package com.example.administrator.industrialmessagesbridge.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.model.TopicContent;
import com.example.administrator.industrialmessagesbridge.myView.SimpleToolbar;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendPostsActivity extends TakePhotoActivity {
@BindView(R.id.simple_toolbar)
    SimpleToolbar simple_toolbar;
@BindView(R.id.posts_add_photo)
ImageButton posts_add_photo;
private TakePhoto takePhoto;
@BindView(R.id.set_posts_title)
    TextView set_posts_title;
@BindView(R.id.posts_title)
EditText posts_title;
@BindView(R.id.posts_photo1)
ImageView posts_photo1;
@BindView(R.id.posts_photo2)
ImageView posts_photo2;
@BindView(R.id.posts_photo3)
ImageView posts_photo3;
@BindView(R.id.txt_right_title)
TextView txt_right_title;
private List<String> photoList;
@BindView(R.id.posts_content)
EditText posts_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_posts);
        ButterKnife.bind(this);
        posts_title.setVisibility(View.GONE);
        takePhoto=getTakePhoto();
        posts_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto.onPickMultiple(3);
            }
        });
        set_posts_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posts_title.getVisibility()==View.GONE)
                    posts_title.setVisibility(View.VISIBLE);
                else
                    posts_title.setVisibility(View.GONE);
            }
        });
        txt_right_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                Date date = new Date(System.currentTimeMillis());
                TopicContent topicContent=new  TopicContent(simpleDateFormat.format(date), "hhh",posts_content.getText().toString() , R.drawable.image_loading_01, R.drawable.image_loading_01, R.drawable.image_loading_01,R.drawable.image_loading_01);
                topicContent.setGoodCounds(1);
                bundle.putParcelable("topic",topicContent);
                intent.putExtras(bundle);
                setResult(0,intent);
                finish();
            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        ArrayList<TImage> tImageArrayList=new ArrayList<>();
          tImageArrayList =result.getImages();
          for (int i=0;i<tImageArrayList.size();i++){
              if (i==0)
                  Glide.with(this).load(tImageArrayList.get(i).getOriginalPath()).into(posts_photo1);
              if (i==1)
                  Glide.with(this).load(tImageArrayList.get(i).getOriginalPath()).into(posts_photo2);
              else
                  Glide.with(this).load(tImageArrayList.get(i).getOriginalPath()).into(posts_photo3);
          }
        super.takeSuccess(result);
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
