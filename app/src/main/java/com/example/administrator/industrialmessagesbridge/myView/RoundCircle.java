package com.example.administrator.industrialmessagesbridge.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import static java.lang.Math.abs;

public class RoundCircle extends View {
    boolean isBlack=false;
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint();
    Paint arcPaint=new Paint();
    String s="0";
    boolean canDrawArc=false;
    public RoundCircle(Context context) {
        this(context,null);
    }

    public RoundCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.roundCircle, defStyleAttr, 0);
       s= typedArray.getString(R.styleable.roundCircle_textContent);
       isBlack=typedArray.getBoolean(R.styleable.roundCircle_isblack,isBlack);
    }

    public void setCanDrawArc(boolean canDrawArc) {
        this.canDrawArc = canDrawArc;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isBlack)
            paint.setColor(Color.parseColor("#FF086BDB"));
        else
        paint.setColor(Color.WHITE);
        paint.setShader(new Shader());
        int r=getWidth()>getHeight()?getHeight():getWidth();
        r=(r)/2;
        canvas.drawCircle(r,r,r-(DeviceUtil.dp2px(getContext(),5))/2,paint);
        if (canDrawArc) {
            arcPaint.setAntiAlias(true);//取消锯齿
            arcPaint.setStyle(Paint.Style.STROKE);//设置画圆弧的画笔的属性为描边(空心)，个人喜欢叫它描边，叫空心有点会引起歧义
            arcPaint.setStrokeWidth(DeviceUtil.dp2px(getContext(), 3));
            arcPaint.setColor(Color.parseColor("#FFD8ECFA"));
            RectF arcRect = new RectF(DeviceUtil.dp2px(getContext(), 3) / 2, DeviceUtil.dp2px(getContext(), 3) / 2,
                    getWidth() - DeviceUtil.dp2px(getContext(), 3) / 2, getHeight() - DeviceUtil.dp2px(getContext(), 3) / 2);
            canvas.drawArc(arcRect, 0, 360, false, arcPaint);
        }
        Rect rect = new Rect(0,0,getWidth(),getHeight());//画一个矩形
        if (isBlack) {
            textPaint.setColor(Color.parseColor("#FF138FEA"));
        }
        else {
            textPaint.setColor(Color.parseColor("#FF138FEA"));
        }
        /*
        * 字体居中
        * **/
        textPaint.setTextSize(DeviceUtil.dp2px(getContext(),16));
        textPaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (rect.centerY() - top/2 - bottom/2);//基线中间点的y轴计算公式
        canvas.drawText(s,rect.centerX(),baseLineY,textPaint);
        super.onDraw(canvas);
    }
}
