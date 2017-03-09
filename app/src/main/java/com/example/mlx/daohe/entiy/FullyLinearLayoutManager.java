package com.example.mlx.daohe.entiy;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/21 20:20
 * 用途：自定义layoutmanager，用于recycleview和scrollview嵌套时使用
 */

public class FullyLinearLayoutManager extends LinearLayoutManager {
      
        private static final String TAG = FullyLinearLayoutManager.class.getSimpleName();  
      
        public FullyLinearLayoutManager(Context context) {
            super(context);  
        }  
      
        public FullyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {  
            super(context, orientation, reverseLayout);  
        }  
      
        private int[] mMeasuredDimension = new int[2];  
      
        @Override  
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                              int widthSpec, int heightSpec) {  
      
            final int widthMode = View.MeasureSpec.getMode(widthSpec);
            final int heightMode = View.MeasureSpec.getMode(heightSpec);  
            final int widthSize = View.MeasureSpec.getSize(widthSpec);  
            final int heightSize = View.MeasureSpec.getSize(heightSpec);  

      
            int width = 0;  
            int height = 0;  
            for (int i = 0; i < getItemCount(); i++) {  
                measureScrapChild(recycler, i,  
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),  
                        View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),  
                        mMeasuredDimension);  
      
                if (getOrientation() == HORIZONTAL) {  
                    width = width + mMeasuredDimension[0];  
                    if (i == 0) {  
                        height = mMeasuredDimension[1];  
                    }  
                } else {  
                    height = height + mMeasuredDimension[1];  
                    if (i == 0) {  
                        width = mMeasuredDimension[0];  
                    }  
                }  
            }  
            switch (widthMode) {  
                case View.MeasureSpec.EXACTLY:  
                    width = widthSize;  
                case View.MeasureSpec.AT_MOST:  
                case View.MeasureSpec.UNSPECIFIED:  
            }  
      
            switch (heightMode) {  
                case View.MeasureSpec.EXACTLY:  
                    height = heightSize;  
                case View.MeasureSpec.AT_MOST:  
                case View.MeasureSpec.UNSPECIFIED:  
            }  
      
            setMeasuredDimension(width, height);  
        }  
      
        private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,  
                                       int heightSpec, int[] measuredDimension) {  
            try {  
                View view = recycler.getViewForPosition(0);//fix 动态添加时报IndexOutOfBoundsException  
      
                if (view != null) {  
                    RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();  
      
                    int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                            getPaddingLeft() + getPaddingRight(), p.width);  
      
                    int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,  
                            getPaddingTop() + getPaddingBottom(), p.height);  
      
                    view.measure(childWidthSpec, childHeightSpec);  
                    measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;  
                    measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;  
                    recycler.recycleView(view);  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            } finally {  
            }  
        }  
    }  