package com.example.administrator.industrialmessagesbridge.myView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.industrialmessagesbridge.R;

public class SimpleToolbar extends Toolbar {
    /**
     * 左侧Title
     */
    private TextView mTxtLeftTitle;
    /**
     * 中间Title
     */
    private TextView mTxtMiddleTitle;
    /**
     * 右侧Title
     */
    private TextView mTxtRightTitle;
    private AutoCompleteTextView search_bar;
    private ImageView message_bar;
    public SimpleToolbar(Context context) {
        this(context,null);
    }

    public SimpleToolbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTxtLeftTitle = (TextView) findViewById(R.id.txt_left_title);
        mTxtMiddleTitle = (TextView) findViewById(R.id.txt_main_title);
        mTxtRightTitle = (TextView) findViewById(R.id.txt_right_title);
        search_bar=(AutoCompleteTextView)findViewById(R.id.search_bar);
        message_bar=(ImageView)findViewById(R.id.message_bar);
        if (search_bar!=null){
            Drawable searchDrawable= getContext().getDrawable(R.drawable.search);
            searchDrawable.setBounds(0, 0, 70, 70);//上面的代码会将drawable绘制在
            search_bar.setCompoundDrawables(searchDrawable,null,null,null);
            search_bar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSearchBar.setSearchBar();
                }
            });
        }
        if (message_bar!=null){
            message_bar.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setImageViewBar.setImageViewBar();
                }
            });
        }

    }

    //设置中间title的内容
    public void setMainTitle(String text) {
        this.setTitle(" ");
        mTxtMiddleTitle.setVisibility(View.VISIBLE);
        mTxtMiddleTitle.setText(text);
    }

    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color) {
        mTxtMiddleTitle.setTextColor(color);
    }

    //设置title左边文字
    public void setLeftTitleText(String text) {
        mTxtLeftTitle.setVisibility(View.VISIBLE);
        mTxtLeftTitle.setText(text);
    }

    //设置title左边文字颜色
    public void setLeftTitleColor(int color) {
        mTxtLeftTitle.setTextColor(color);
    }

    //设置title左边图标
    public void setLeftTitleDrawable(int res) {
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        dwLeft.setBounds(0, 0, 70, 70);
        mTxtLeftTitle.setCompoundDrawables(dwLeft, null, null, null);
    }
    //设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener){
        mTxtLeftTitle.setOnClickListener(onClickListener);
    }

    //设置title右边文字
    public void setRightTitleText(String text) {
        mTxtRightTitle.setVisibility(View.VISIBLE);
        mTxtRightTitle.setText(text);
    }

    //设置title右边文字颜色
    public void setRightTitleColor(int color) {
        mTxtRightTitle.setTextColor(color);
    }

    //设置title右边图标
    public void setRightTitleDrawable(int res) {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, 70,70);
        mTxtRightTitle.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener){
        mTxtRightTitle.setOnClickListener(onClickListener);
    }
    public interface SetSearchBar{
        public void setSearchBar();
    }
    public interface SetImageViewBar{
        public void setImageViewBar();
    }
    SetSearchBar setSearchBar;
    SetImageViewBar setImageViewBar;

    public void setSetImageViewBar(SetImageViewBar setImageViewBar) {
        this.setImageViewBar = setImageViewBar;
    }

    public void hideSerchBar(){
          search_bar.setVisibility(View.GONE);
      }
      public void showSerchBar(){
          search_bar.setVisibility(View.VISIBLE);
      }

    public void setSetSearchBar(SetSearchBar setSearchBar) {
        this.setSearchBar = setSearchBar;
    }

}
