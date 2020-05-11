package com.ying.wardrobe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import okhttp3.Call;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.fragment.MineFragment;
import com.ying.wardrobe.fragment.StatisticsFragment;
import com.ying.wardrobe.fragment.WardrobeFragment;
import com.ying.wardrobe.fragment.WearDiaryFragment;
import com.ying.wardrobe.okhttp.OkHttpUtils;
import com.ying.wardrobe.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    // 当前fragment的index
    private int currentTabIndex=1;
    private List<TabHolder> tabList;
    public static Fragment currentFragment;
    private WardrobeFragment wardrobeFragment;
    private WearDiaryFragment wearDiaryFragment;
    private StatisticsFragment statisticsFragment;
    private MineFragment mineFragment;
    private Button btn_wardrobe,btn_wear_diary,btn_statistics,btn_mine;
    private int currentPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        tabList = new ArrayList<>();
        wardrobeFragment=new WardrobeFragment();
        addOneTabItem(tabList, R.id.btn_wardrobe, wardrobeFragment);
        setTabSelected(currentPosition);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        btn_wardrobe=findViewById(R.id.btn_wardrobe);
        btn_wear_diary=findViewById(R.id.btn_wear_diary);
        btn_statistics=findViewById(R.id.btn_statistics);
        btn_mine=findViewById(R.id.btn_mine);
        btn_wardrobe.setOnClickListener(this);
        btn_wear_diary.setOnClickListener(this);
        btn_statistics.setOnClickListener(this);
        btn_mine.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    //添加一个TabItem
    private void addOneTabItem(List<TabHolder> list, int tabBtnId, Fragment tabContent) {
        TabHolder th;
        th = new TabHolder();
        th.setTabLayoutId(tabBtnId);
        th.setTabContent(tabContent);
        list.add(th);
        //添加当前fragment,并隐藏
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_fragment_content, th.getTabContent());
        transaction.hide(th.getTabContent());
        transaction.commitAllowingStateLoss();
    }

    //设置某一个tab为选中状态
    private void setTabSelected(int tabIdx) {
        if (isValidTabIdx(tabIdx) && currentTabIndex != tabIdx) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (isValidTabIdx(currentTabIndex)) {
                TabHolder thOld = tabList.get(currentTabIndex);
                thOld.getTabBtn().setSelected(false);
                transaction.hide(thOld.getTabContent());
            }
            TabHolder th = tabList.get(tabIdx);
            th.getTabBtn().setSelected(true);
            transaction.show(th.getTabContent());
            transaction.commitAllowingStateLoss();
            currentTabIndex = tabIdx;
            currentFragment = th.getTabContent();
        }
    }
    //是否为有效的tabIdx
    private boolean isValidTabIdx(int tabIdx) {
        return tabIdx >= 0 && tabIdx < tabList.size();
    }
    //根据tabBtnId获取对应的tabitem的位置
    private int findTabIdxByTabBtnId(int tabBtnId) {
        for (int index = 0; index < tabList.size(); index++) {
            TabHolder th = tabList.get(index);
            if (th.getTabLayoutId() == tabBtnId) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: "+view.getId());
        switch (view.getId()){
            case R.id.btn_wardrobe:
                if (wardrobeFragment==null){
                    wardrobeFragment=new WardrobeFragment();
                    addOneTabItem(tabList, R.id.btn_wardrobe, wardrobeFragment);
                }
                setTabSelected(findTabIdxByTabBtnId(view.getId()));
                break;
            case R.id.btn_wear_diary:
                if (wearDiaryFragment==null){
                    wearDiaryFragment=new WearDiaryFragment();
                    addOneTabItem(tabList, R.id.btn_wear_diary, wearDiaryFragment);
                }
                setTabSelected(findTabIdxByTabBtnId(view.getId()));
                break;
            case R.id.btn_statistics:
                if (statisticsFragment==null){
                    statisticsFragment=new StatisticsFragment();
                    addOneTabItem(tabList, R.id.btn_statistics, statisticsFragment);
                }
                setTabSelected(findTabIdxByTabBtnId(view.getId()));
                break;
            case R.id.btn_mine:
                OkHttpUtils.get()
                        .url("https://www.baidu.com")
                        .build()
                        .execute(new Callback() {
                            @Override
                            public Object parseNetworkResponse(Response response, int id) throws Exception {
                                return null;
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.d(TAG, "onError: "+e.toString());
                            }

                            @Override
                            public void onResponse(Object response, int id) {
                            }
                        });
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                    addOneTabItem(tabList, R.id.btn_mine, mineFragment);
                }
                setTabSelected(findTabIdxByTabBtnId(view.getId()));
                break;
        }
    }

    private class TabHolder {
        private int tabLayoutId;//tabButton对应的id
        private View tabLayout;//会根据tabBtnId获取tabBtn
        private Fragment tabContent;//点击tab对应的fragment

        int getTabLayoutId() {
            return tabLayoutId;
        }

        void setTabLayoutId(int tabLayoutId) {
            this.tabLayoutId = tabLayoutId;
        }

        View getTabBtn() {
            if (tabLayout == null) {
                tabLayout = findViewById(tabLayoutId);
            }
            return tabLayout;
        }

        Fragment getTabContent() {
            return tabContent;
        }

        void setTabContent(Fragment tabContent) {
            this.tabContent = tabContent;
        }

    }
}
