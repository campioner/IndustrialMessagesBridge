package com.example.administrator.industrialmessagesbridge.myView;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.administrator.industrialmessagesbridge.R;


public class RoundImageView extends ImageView {
    //ImageView的类型
    private int type;
    //圆形
    private final int TYPE_CICLE=0;
    //圆角
    private final int TYPE_ROUND=1;
    //默认圆角宽度

    private static final int BORDER_RADIUS_DEFAULT = 10;

    // 获取圆角宽度
    private int mBorderRadius;
    //画笔
    private Paint mpaint;
    //半径
    private int mRadius;
    //缩放矩阵
    private Matrix matrix;
    //渲染器，和缩放矩阵配合使用，用于对图片的缩放或者扩大
    private BitmapShader mBitmapShader;
    //宽度
    private int mWidth;
    //圆角范围
    private RectF mRectF;
    public RoundImageView(Context context) {
        this(context ,null);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔等属性
        mpaint=new Paint();
        matrix=new Matrix();
        mpaint.setAntiAlias(true);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.RoundImageView, defStyleAttr, 0);
        int count=typedArray.getIndexCount();
        for(int i=0;i<count;i++){
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.RoundImageView_borderRadius:
                    //获取值，第二个参数将默认值BORDER_RADIUS_DEFAULT转化为像素，DisplayMetrics是一个获取屏幕信息的类
                    // TypedValue.applyDimension第一个参数是你想要得到的单位，第二个参数是你想得到的单位的数值，dp-》px
                    mBorderRadius=typedArray.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BORDER_RADIUS_DEFAULT, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.RoundImageView_imageType:
                    //第二个参数都是默认值
                    type=typedArray.getInt(R.styleable.RoundImageView_imageType,TYPE_CICLE);
                    break;
            }
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getDrawable()==null){
            return;
        }
        setShader();
        if(type==TYPE_ROUND){
            canvas.drawRoundRect(mRectF,mBorderRadius,mBorderRadius,mpaint);
        }else{
            canvas.drawCircle(mRadius,mRadius,mRadius,mpaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(type==TYPE_CICLE){
            //getMeasuredWidth全部长度，getWidth是显示出来的长度不包括隐藏的
            mWidth= Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius=mWidth/2;
            setMeasuredDimension(mWidth,mWidth);
        }
    }
    public void setShader(){
        Drawable drawable=getDrawable();
        if(drawable==null)
            return;
        Bitmap bitmap=drawable2Bitmap(drawable);
        mBitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale=1.0f;
        if(type==TYPE_ROUND){
            scale= Math.max(getWidth()*1.0f/bitmap.getWidth(),getHeight()*1.0f/bitmap.getHeight());
        }else if(type==TYPE_CICLE){
            int bitmapWidth= Math.min(bitmap.getWidth(),bitmap.getHeight());
            scale=mWidth*1.0f/bitmapWidth;
        }
        matrix.setScale(scale,scale);
        mBitmapShader.setLocalMatrix(matrix);
        mpaint.setShader(mBitmapShader);
    }
    public Bitmap drawable2Bitmap(Drawable drawable){
        if(drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int w=drawable.getIntrinsicWidth();
        int h=drawable.getIntrinsicHeight();
        Bitmap bitmap= Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);//其实drawable 它本身有一个 draw 方法, 只要我们调用 setBounds 设置范围, 在调用draw 方法就可以直接画了
        return bitmap;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//在控件大小发生改变时调用。所以这里初始化会被调用一次
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(getLeft(), getRight(),getTop() , getBottom());
        int i=1;
    }

    public void setBorderRadius(int borderRadius) {
        int pxValue = dp2px(borderRadius);
        if (this.mBorderRadius != pxValue) {
            this.mBorderRadius = pxValue;
            // 这时候不需要父布局的onLayout,所以只需要调用onDraw即可
            invalidate();
        }
    }

    /**
     * 对外公布的设置形状的方法
     *
     * @param type
     */
    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_CICLE && this.type != TYPE_ROUND) {
                this.type = TYPE_CICLE;
            }
            // 这个时候改变形状了，就需要调用父布局的onLayout，那么此view的onMeasure方法也会被调用
            requestLayout();//而requestLayout()是当view确定自身已经不再适合现有的区域时，调用该方法要求parent view重新调用它的onMeasure和onLayout来重新设置自己。
        }
    }

    /**
     * dp2px
     */
    public int dp2px(int val) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, getResources().getDisplayMetrics());
    }


}
