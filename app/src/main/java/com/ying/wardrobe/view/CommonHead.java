package com.ying.wardrobe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ying.wardrobe.R;


/**
 * Created by dl on 2016/12/1.
 * 通用头部布局
 */
public class CommonHead extends LinearLayout {

    private RelativeLayout allLin;

    private LinearLayout mHeadCommonBack;

    private TextView midTxt; //中间文字

    private ImageView rightImg; //右边的图片，更多

    private ImageView leftImg;

    private TextView rightTxt;

    private LinearLayout back; //左边的返回键

    public CommonHead(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonHead, defStyle, 0);
        String midtxt = a.getString(R.styleable.CommonHead_mid_txt);
        int midtxtcolor = a.getColor(R.styleable.CommonHead_mid_txt_color, 0);
        int leftimg = a.getResourceId(R.styleable.CommonHead_leftImg, 0);
        int rightimg = a.getResourceId(R.styleable.CommonHead_rightImg, 0);
        boolean showleft = a.getBoolean(R.styleable.CommonHead_show_left, false);
        boolean showright = a.getBoolean(R.styleable.CommonHead_show_right, false);
        String righttxt = a.getString(R.styleable.CommonHead_rightTxt);
        int righttxtcolor = a.getColor(R.styleable.CommonHead_rightTxtColor, Color.parseColor("#ffffff"));
        int bgcolor = a.getColor(R.styleable.CommonHead_bg_color, 0);
        a.recycle();


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.head_common, this);

        mHeadCommonBack = (LinearLayout) findViewById(R.id.head_common_back);

        midTxt = (TextView) findViewById(R.id.head_common_mid_txt);

        allLin = (RelativeLayout) findViewById(R.id.head_common_lin);

        leftImg = (ImageView) findViewById(R.id.bead_common_left_img);

        rightImg = (ImageView) findViewById(R.id.head_common_right_img);

        rightTxt = (TextView) findViewById(R.id.head_common_right_txt);

        if (showleft) {
            leftImg.setVisibility(View.VISIBLE);
            leftImg.setImageResource(leftimg);
            leftImg.setOnClickListener(new LeftClickListener());
            mHeadCommonBack.setOnClickListener(new LeftClickListener());
        }else {
            leftImg.setVisibility(GONE);
        }

        allLin.setBackgroundColor(bgcolor);

        midTxt.setText(midtxt);
        midTxt.setSingleLine(true);
        midTxt.setEllipsize(TextUtils.TruncateAt.END);
        midTxt.setMaxEms(10);
        midTxt.setTextColor(midtxtcolor);

        if (!TextUtils.isEmpty(righttxt)) {
            rightTxt.setText(righttxt);
            rightTxt.setVisibility(View.VISIBLE);
            rightTxt.setTextColor(righttxtcolor);
            rightTxt.setOnClickListener(new RightClickListener());
        } else {
            if (showright) {
                rightImg.setVisibility(View.VISIBLE);
                rightImg.setImageResource(rightimg);
                rightImg.setOnClickListener(new RightClickListener());
            }
        }
    }

    private class LeftClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (lClick != null)
                lClick.LeftClick(); //回调左边的更多点击事件
        }
    }

    private class RightClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (rClick != null)
                rClick.RightClick(); //回调右边的更多点击事件
        }
    }

    public CommonHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonHead(Context context) {
        this(context, null);
    }

    public void setRightImg(Drawable drawable) {
        rightImg.setImageDrawable(drawable);
    }

    public void setMidTxt(String txt) { //设置中间的文字
        midTxt.setText(txt);
    }

    public void setRightTxt(String txt) { //设置中间的文字
        rightTxt.setText(txt);
    }


    private CommonHeadRightClick rClick = null;

    public interface CommonHeadRightClick {
        public void RightClick();
    }

    public void setRightClick(CommonHeadRightClick click) {
        this.rClick = click;
    }

    private CommonHeadLeftClick lClick = null;

    public interface CommonHeadLeftClick {
        public void LeftClick();
    }

    public void setLeftClick(CommonHeadLeftClick click) {
        this.lClick = click;
    }


}
