package com.example.administrator.industrialmessagesbridge.myView;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.example.administrator.industrialmessagesbridge.R;

import razerdp.basepopup.BasePopupWindow;

public class MyPopup extends BasePopupWindow {
    Animation enterAnimation = null;
    Animation dismissAnimation = null;
    private int animationStyle=0;
    private int viewId;
    public View rootView;

    public MyPopup(Context context) {
        super(context);

    }

    public MyPopup(Context context, int animationStyle) {
        super(context);
        this.animationStyle = animationStyle;
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.lable_filter);
    }

    // 以下为可选代码（非必须实现）
    // 返回作用于PopupWindow的show和dismiss动画，本库提供了默认的几款动画，这里可以自由实现
    @Override
    protected Animation onCreateShowAnimation() {

        return enterAnimation ;
    }
        @Override
        protected Animation onCreateDismissAnimation () {

            return dismissAnimation ;
        }
        private Animation createVerticalAnimation ( float fromY, float toY){
            Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0f,
                    Animation.RELATIVE_TO_SELF,
                    0f,
                    Animation.RELATIVE_TO_SELF,
                    fromY,
                    Animation.RELATIVE_TO_SELF,
                    toY);
            animation.setDuration(500);
            animation.setInterpolator(new DecelerateInterpolator());
            return animation;
        }
        public Animation getShowAnimation2(){
            switch (animationStyle) {//0 左 上 右 下
                case 0:
                    return getDefaultScaleAnimation(true);
                case 1:
                    enterAnimation = createHorizontalAnimation(1f, 0);
                    break;
                case 2:
                    enterAnimation = createVerticalAnimation(1f, 0);
                    break;
                case 3:
                    enterAnimation = createHorizontalAnimation(-1f, 0);
                    break;
                case 4:
                    enterAnimation = createVerticalAnimation(-1f, 0);
                    break;
            }
        return enterAnimation;
        }


    public Animation getDismissAnimation2() {
        switch (animationStyle) {
            case 0:
                return getDefaultScaleAnimation(false);
            case 1:
                dismissAnimation = createHorizontalAnimation(0, 1f);
                break;
            case 2:
                dismissAnimation = createHorizontalAnimation(0, 1f);
                break;
            case 3:
                dismissAnimation = createHorizontalAnimation(0, -1f);
                break;
            case 4:
                dismissAnimation = createVerticalAnimation(0, -1f);
                break;
        }
        return dismissAnimation;
    }

    private Animation createHorizontalAnimation (float fromX, float toX){
            Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    fromX,
                    Animation.RELATIVE_TO_SELF,
                    toX,
                    Animation.RELATIVE_TO_SELF,
                    0f,
                    Animation.RELATIVE_TO_SELF,
                    0f);
            animation.setDuration(500);
            animation.setInterpolator(new DecelerateInterpolator());
            return animation;
        }
    }

