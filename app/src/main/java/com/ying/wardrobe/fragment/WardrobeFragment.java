package com.ying.wardrobe.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.wildma.pictureselector.PictureSelector;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;
import com.ying.wardrobe.activity.AddClothesActivity;
import com.ying.wardrobe.activity.EditUserInfoActivity;
import com.ying.wardrobe.entity.ClothesEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;
import com.ying.wardrobe.view.CommonAlterDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WardrobeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "WardrobeFragment";
    private TextView addYifu;
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
    protected int getLayoutId() {
        return R.layout.fragment_wardrobe;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        addYifu = view.findViewById(R.id.addYifu);
        duanxiuTextView = view.findViewById(R.id.duanxiuTextView);
        waitaoTextView = view.findViewById(R.id.waitaoTextView);
        changkuTextView = view.findViewById(R.id.changkuTextView);
        qunziTextView = view.findViewById(R.id.qunziTextView);
        maoziTextView = view.findViewById(R.id.maoziTextView);
        listView = view.findViewById(R.id.listView);
        addYifu.setOnClickListener(this);
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
                        Toast.makeText(getActivity(), "用户信息更新失败，请稍后再试！", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addYifu:
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddClothesActivity.class);
                intent.putExtra("selectType", selectType);
                startActivity(intent);
                break;
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

    @Override
    public void onResume() {
        super.onResume();
        getClothes(selectType);
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_wardrobe, null, false);
                view.setTag(viewHolder);
                viewHolder.yifuImageView = view.findViewById(R.id.yifuImageView);
                viewHolder.priceTextView = view.findViewById(R.id.priceTextView);
                viewHolder.styleTextView = view.findViewById(R.id.styleTextView);
                viewHolder.seasonTextView = view.findViewById(R.id.seasonTextView);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Glide.with(getActivity()).load(clothesEntity.getData().get(i).getPhoto()).into(viewHolder.yifuImageView);
            viewHolder.priceTextView.setText("价格：￥" + clothesEntity.getData().get(i).getPrice());
            viewHolder.styleTextView.setText("风格：" + clothesEntity.getData().get(i).getStyle());
            viewHolder.yifuImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CommonAlterDialog suredialog = new CommonAlterDialog(getActivity());
                    suredialog.setMessage("确认删除该衣物么？")
                            .setTitle("提示")
//                .setTitle("系统提示")
                            .setSingle(false).setOnClickBottomListener(new CommonAlterDialog.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            deleteImage(clothesEntity.getData().get(i).getId());
                            suredialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {
                            suredialog.dismiss();
                        }
                    });
                    suredialog.show();
                }
            });
            viewHolder.seasonTextView.setText("季节：" + clothesEntity.getData().get(i).getJijie());
            return view;
        }

        class ViewHolder {
            TextView priceTextView;
            TextView styleTextView;
            TextView seasonTextView;
            ImageView yifuImageView;
        }
    }

    private void deleteImage(String id) {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_Delete, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("yifuId", id);
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                JSONObject jsonObject=response.get();
                try {
                    if (jsonObject.getInt("status")==0){
                        Toast.makeText(getActivity(), "衣服已成功删除！", Toast.LENGTH_SHORT).show();
                        getClothes(selectType);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
