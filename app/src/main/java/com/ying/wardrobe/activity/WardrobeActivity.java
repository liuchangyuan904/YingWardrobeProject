package com.ying.wardrobe.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.entity.ClothesEntity;
import com.ying.wardrobe.fragment.WardrobeFragment;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;
import com.ying.wardrobe.view.CommonHead;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.activity
 * @ClassName: WardrobeActivity
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/14 0014 上午 10:38
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/14 0014 上午 10:38
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class WardrobeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "WardrobeActivity";
    private CommonHead title_bar;
    private ListView listView;
    private WardrobeListAdapter listAdapter;
    private TextView duanxiuTextView;
    private TextView changkuTextView;
    private TextView qunziTextView;
    private TextView waitaoTextView;
    private TextView maoziTextView;
    private String selectType = "duanxiu";

    private ClothesEntity clothesEntity;

    @Override
    protected int initLayout() {
        return R.layout.activity_wardrobe;
    }

    @Override
    protected void initView() {
        title_bar = findViewById(R.id.title_bar);
        title_bar.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                WardrobeActivity.this.finish();
            }
        });
        duanxiuTextView = findViewById(R.id.duanxiuTextView);
        waitaoTextView = findViewById(R.id.waitaoTextView);
        changkuTextView = findViewById(R.id.changkuTextView);
        qunziTextView = findViewById(R.id.qunziTextView);
        maoziTextView = findViewById(R.id.maoziTextView);
        listView = findViewById(R.id.listView);
        duanxiuTextView.setOnClickListener(this);
        waitaoTextView.setOnClickListener(this);
        changkuTextView.setOnClickListener(this);
        qunziTextView.setOnClickListener(this);
        maoziTextView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        listAdapter = new WardrobeListAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getClothes(selectType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.duanxiuTextView:
                selectType = "duanxiu";
                getClothes(selectType);
                duanxiuTextView.setBackgroundResource(R.drawable.common_white_btn_bg);
                waitaoTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                changkuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                qunziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                maoziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                duanxiuTextView.setTextColor(getResources().getColor(R.color.title_bg_color));
                waitaoTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                changkuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                qunziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                maoziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.waitaoTextView:
                selectType = "waitao";
                getClothes(selectType);
                duanxiuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                waitaoTextView.setBackgroundResource(R.drawable.common_white_btn_bg);
                changkuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                qunziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                maoziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                duanxiuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                waitaoTextView.setTextColor(getResources().getColor(R.color.title_bg_color));
                changkuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                qunziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                maoziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.changkuTextView:
                selectType = "changku";
                getClothes(selectType);
                duanxiuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                waitaoTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                changkuTextView.setBackgroundResource(R.drawable.common_white_btn_bg);
                qunziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                maoziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                duanxiuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                waitaoTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                changkuTextView.setTextColor(getResources().getColor(R.color.title_bg_color));
                qunziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                maoziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.qunziTextView:
                selectType = "qunzi";
                getClothes(selectType);
                duanxiuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                waitaoTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                changkuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                qunziTextView.setBackgroundResource(R.drawable.common_white_btn_bg);
                maoziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                duanxiuTextView.setTextColor(getResources().getColor(R.color.color_white));
                waitaoTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                changkuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                qunziTextView.setTextColor(getResources().getColor(R.color.title_bg_color));
                maoziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case R.id.maoziTextView:
                selectType = "maozi";
                getClothes(selectType);
                duanxiuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                waitaoTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                changkuTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                qunziTextView.setBackgroundResource(R.drawable.common_blue_btn_bg);
                maoziTextView.setBackgroundResource(R.drawable.common_white_btn_bg);
                duanxiuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                waitaoTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                changkuTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                qunziTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                maoziTextView.setTextColor(getResources().getColor(R.color.title_bg_color));
                break;
        }
    }
    private void getClothes(String type) {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_LIST + "/" + selectType, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("type", type);
        request.add("userId", Constant.currentUserEntity.getData().getId());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: 获取衣服列表：  " + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                        Gson gson = new Gson();
                        clothesEntity = gson.fromJson(response.get().toString(), ClothesEntity.class);
                        listView.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(WardrobeActivity.this, "获取衣橱列表失败，请稍后再试！", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onFailed: ");
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
    class WardrobeListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return clothesEntity.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return clothesEntity.getData().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(WardrobeActivity.this).inflate(R.layout.list_item_wardrobe, null, false);
                view.setTag(viewHolder);
                viewHolder.yifuImageView = view.findViewById(R.id.yifuImageView);
                viewHolder.priceTextView = view.findViewById(R.id.priceTextView);
                viewHolder.styleTextView = view.findViewById(R.id.styleTextView);
                viewHolder.seasonTextView = view.findViewById(R.id.seasonTextView);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Glide.with(WardrobeActivity.this).load(clothesEntity.getData().get(i).getPhoto()).into(viewHolder.yifuImageView);
            viewHolder.priceTextView.setText("价格：￥" + clothesEntity.getData().get(i).getPrice());
            viewHolder.styleTextView.setText("风格：" + clothesEntity.getData().get(i).getStyle());
            viewHolder.seasonTextView.setText("季节：" + clothesEntity.getData().get(i).getJijie());
            viewHolder.yifuImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("yifuId",clothesEntity.getData().get(i).getId());
                    intent.putExtra("yifuUrl",clothesEntity.getData().get(i).getPhoto());
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            });
            return view;
        }

        class ViewHolder {
            TextView priceTextView;
            TextView styleTextView;
            TextView seasonTextView;
            ImageView yifuImageView;
        }
    }
}
