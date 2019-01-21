package com.example.administrator.industrialmessagesbridge.myView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.industrialmessagesbridge.MainActivity;
import com.example.administrator.industrialmessagesbridge.MyApplication;
import com.example.administrator.industrialmessagesbridge.R;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MyBanner extends AppCompatImageView {
    private Paint paint;
    private PointF startPoint;
    private PointF endPoint;
    private PointF controlPoint;
    private int width;
    private int height;
    private int ArcHeight = DeviceUtil.dp2px(MyApplication.getContext(),10);
    private Path path;
    private Bitmap bitmap;
    private float mScale=1.0f;
   private int bitmapSrc=-1;

    public MyBanner(Context context) {
        this(context,null,0);
    }

    public MyBanner(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.startPoint = new PointF(0.0F, 0.0F);
        this.endPoint = new PointF(0.0F, 0.0F);
        this.controlPoint = new PointF(0.0F, 0.0F);
        this.path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        this.path.reset();
        this.path.moveTo(0.0F, 0.0F);
        this.path.addRect(0.0F, 0.0F, (float)this.width, (float)(this.height - this.ArcHeight), Path.Direction.CCW);
        this.startPoint.x = 0.0F;
        this.startPoint.y = (float)(this.height - this.ArcHeight);
        this.endPoint.x = (float)this.width;
        this.endPoint.y = (float)(this.height - this.ArcHeight);
        this.controlPoint.x = (float)(this.width / 2);
        this.controlPoint.y = (float)(this.height + this.ArcHeight);
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapSrc!=-1||bitmap!=null) {
            if (bitmap==null)
            bitmap =BitmapFactory.decodeResource(getContext().getResources(), bitmapSrc);
            if (bitmap != null) {
                //计算缩放比例,之前本来是通过宽高计算缩放比例，现在直接提供接口
                Matrix matrix = new Matrix();
                matrix.setScale((float) (width * 1.0 / bitmap.getWidth()), (float) (height * 1.0 / bitmap.getHeight()));
               Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                shader.setLocalMatrix(matrix);
                paint.setShader(shader);
                this.path.moveTo(this.startPoint.x, this.startPoint.y);
                this.path.quadTo(this.controlPoint.x, this.controlPoint.y, this.endPoint.x, this.endPoint.y);
                canvas.drawPath(this.path, this.paint);
            }
        }
        //canvas.clipPath(mpath);
    }

    public void setSrc(int src){
        bitmapSrc=src;
    }
    public void setBitmap(Bitmap bitmap){
        bitmap=bitmap;
    }

}
