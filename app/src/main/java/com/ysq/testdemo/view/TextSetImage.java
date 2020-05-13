package com.ysq.testdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ysq.testdemo.R;
import com.ysq.testdemo.utils.ScreenUtils;

/**
 * 项目名：MyTestDemo
 * <p>
 * 时 间：2019/11/11
 * <p>
 * 包 名：com.ysq.testdemo.view
 * <p>
 * 类 名：TextSetImage
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：在text中插入图片标牵
 */
public class TextSetImage {


    public static void addTagToTextView(Context pContext , TextView target, String title, String tag) {
        if (TextUtils.isEmpty(title)) {
            title = "";
        }

        String content = title + tag;


        /**
         * 创建TextView对象，设置drawable背景，设置字体样式，设置间距，设置文本等
         * 这里我们为了给TextView设置margin，给其添加了一个父容器LinearLayout。不过他俩都只是new出来的，不会添加进任何布局
         */

        //1. 这里我们为了给TextView设置margin，给其添加了一个父容器LinearLayout。不过他俩都只是new出来的，不会添加进任何布局
        //LinearLayout layout = new LinearLayout(this);
        //2.创建TextView对象，设置drawable背景
        TextView textView = new TextView(pContext);
        textView.setText(tag);
        textView.setBackgroundResource(R.drawable.abc);
        textView.setTextSize(30);
        textView.setTextColor(Color.parseColor("#FDA413"));
        textView.setIncludeFontPadding(false);
        textView.setPadding(ScreenUtils.dip2px(pContext, 6), 0, ScreenUtils.dip2px(pContext, 6), 0);
        textView.setHeight(ScreenUtils.dip2px(pContext, 17));
        textView.setGravity(Gravity.CENTER_VERTICAL);

        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置左间距
        //layoutParams.leftMargin = ScreenUtils.dip2px(this, 6);
        // 设置下间距，简单解决ImageSpan和文本竖直方向对齐的问题
        //layoutParams.bottomMargin = ScreenUtils.dip2px(this, 3);
        //layout.addView(textView, layoutParams);

        /**
         * 第二步，测量，绘制layout，生成对应的bitmap对象
         */
        textView.setDrawingCacheEnabled(true);
        textView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        // 给上方设置的margin留出空间
        textView.layout(0, 0, textView.getMeasuredWidth() + ScreenUtils.dip2px(pContext, (6 + 3)), textView.getMeasuredHeight());
        // 获取bitmap对象
        Bitmap bitmap = Bitmap.createBitmap(textView.getDrawingCache());
        //千万别忘最后一步
        textView.destroyDrawingCache();

        /**
         * 第三步，通过bitmap生成我们需要的ImageSpan对象
         */
        ImageSpan imageSpan = new ImageSpan(pContext, bitmap);


        /**
         * 第四步将ImageSpan对象设置到SpannableStringBuilder的对应位置
         */
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        //将尾部tag字符用ImageSpan替换
        ssb.setSpan(imageSpan, title.length(), content.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        target.setText(ssb);
    }

}
