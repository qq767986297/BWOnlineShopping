package com.bawei.bwonlineshopping.customview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //根据孩子的数量进行遍历
        for(int i = 0; i < getChildCount(); i++){
            //根据角标，获取当前孩子
            View child = getChildAt(i);
            //获取标记
            Rect rect = (Rect) child.getTag();
            //根据标记，赋值
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //让系统计算所有的孩子
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //控件总宽度 - 控件右内边距 = 控件实际可以使用宽度
        compare(width - getPaddingRight());
        //测量自己
        setMeasuredDimension(width,height);
    }
    //添加 compare 方法，用来通过孩子的宽度配合控件的宽度以及使用的宽度，计算孩子的摆放位置
    private void compare(int width){
        int usedWidth = getPaddingLeft();
        int usedHeight = getPaddingTop();
        //根据孩子数量进行遍历
        for (int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeigh = child.getMeasuredHeight();
            //获取孩子的布局管理
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) child.getLayoutParams();
            //孩子真正占用的宽度 = 孩子测量的宽度 + 孩子左边外边距 + 孩子右边的外边距
            int childWidthReal = childWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            //孩子真正占用的高度 = 孩子测量的高度 + 孩子上边外边距 + 孩子下边的外边距
            int childHeightReal = childHeigh + layoutParams.topMargin + layoutParams.bottomMargin;
             //如果已经使用的 宽度 + 孩子真正占用的高度 > 屏幕宽度，说明需要换行
            if((usedWidth + childWidthReal) > width){
                //如果需要换行，使用过的宽度，肯定是控件的左侧内边距
                usedWidth = getPaddingLeft();
                //如果需要换行，使用过的高度，肯定以前的高度 + 孩子真正占用的高度
                usedHeight += childHeightReal;
            }
            //不管有没有换行，使用宽度都等于 原来使用的宽度 + 孩子真正的宽度
            usedWidth += childWidthReal;

            Rect rect = new Rect();
            rect.top = usedHeight;
            rect.bottom = usedHeight + childHeightReal;
            rect.left = usedWidth - childWidthReal;
            rect.right = usedWidth;
            child.setTag(rect);
        }
    }
}
