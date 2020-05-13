package com.ying.wardrobe.activity;

import android.widget.TextView;

import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.view.CommonHead;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class AddDiaryActivity extends BaseActivity {
    private CommonHead title_bar;
    private TextView dateTextView;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月DD");
    @Override
    protected int initLayout() {
        return R.layout.activity_add_diary;
    }

    @Override
    protected void initView() {
        title_bar=findViewById(R.id.title_bar);
        title_bar.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                AddDiaryActivity.this.finish();
            }
        });
        dateTextView=findViewById(R.id.dateTextView);
        Date date=new Date();
        dateTextView.setText(simpleDateFormat.format(date));
    }

    @Override
    protected void initData() {

    }
}
