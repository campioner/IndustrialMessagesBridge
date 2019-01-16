package com.example.administrator.industrialmessagesbridge.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.industrialmessagesbridge.adapter.adapterModel.HomeFunction;
import com.example.administrator.industrialmessagesbridge.model.DocumentModel;
import com.example.administrator.industrialmessagesbridge.utils.DeviceUtil;

import java.util.List;

public class RecommadnItemDecroration extends RecyclerView.ItemDecoration {
    private List<DocumentModel>documentModelList;
    private int titileHight;
    private int titileSize;
    private Paint mPaint;
    private Paint mPaint2;
    private Rect mBounds;
    public RecommadnItemDecroration(Context context, List<DocumentModel>documentModelList) {
        super();
        this.documentModelList=documentModelList;
        titileHight= DeviceUtil.dp2px(context,35);
        titileSize=DeviceUtil.dp2px(context,20);
        mPaint=new Paint();
        mBounds=new Rect();
        mPaint.setTextSize(titileSize);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount=parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View view=parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams=(RecyclerView.LayoutParams)view.getLayoutParams();
            int position=layoutParams.getViewLayoutPosition();
            if (position==0||!(documentModelList.get(position-1).getType()+"").equals(documentModelList.get(position).getType()+""))
            drawText(c,left,right,view,layoutParams,position);
        }
    }
public void drawText(Canvas c, int left, int right,View child, RecyclerView.LayoutParams layoutParams,int position){
    mPaint=new Paint();
    mPaint.setTextSize(titileSize);
    mPaint.setAntiAlias(true);
    mPaint.setColor(Color.parseColor("#FFEEEEF3"));
        c.drawRect(left,child.getTop() - layoutParams.topMargin - titileHight,right,child.getTop()-layoutParams.topMargin,mPaint);
    mPaint2=new Paint();
    mPaint2.setTextSize(titileSize);
    mPaint2.setAntiAlias(true);
    mPaint2.getTextBounds("推荐文档", 0,4, mBounds);
    mPaint2.setColor(Color.parseColor("#FF000000"));
        if (documentModelList.get(position).getType()==0){
            c.drawText("推荐文档",  child.getPaddingLeft(),  child.getTop() - layoutParams.topMargin - (titileHight / 2 - mBounds.height() / 2),mPaint2);
        }else{
            c.drawText("推荐专家",  child.getPaddingLeft(),child.getTop() - layoutParams.topMargin - (titileHight / 2 - mBounds.height() / 2),mPaint2);
        }

}
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
       int positon=((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
       if (positon==0)
           outRect.set(0,titileHight,0,0);
       else if(!(documentModelList.get(positon-1).getType()+"").equals(documentModelList.get(positon).getType()+"")){
           outRect.set(0,titileHight,0,0);
       }
    }
}
