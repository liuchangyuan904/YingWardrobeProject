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
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;
import com.ying.wardrobe.activity.AddDiaryActivity;
import com.ying.wardrobe.entity.ClothesEntity;
import com.ying.wardrobe.entity.WearDiaryEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WearDiaryFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = "WearDiaryFragment";
    private ListView listView;
    private WearDiaryAdapter adapter;
    private TextView addDiary;
    private WearDiaryEntity wearDiaryEntity;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wear_diary;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        listView = view.findViewById(R.id.listView);
        addDiary = view.findViewById(R.id.addDiary);
        adapter = new WearDiaryAdapter();
        addDiary.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        getClothes();
    }

    @Override
    public void onResume() {
        super.onResume();
        getClothes();
    }

    private void getClothes() {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_History , RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("userId", Constant.currentUserEntity.getData().getId());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: 获取穿衣日记列表：  " + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                        Gson gson = new Gson();
                        wearDiaryEntity = gson.fromJson(response.get().toString(), WearDiaryEntity.class);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "日记信息获取失败，请稍后再试！", Toast.LENGTH_SHORT).show();
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
        switch (v.getId()){
            case R.id.addDiary:
                Intent intent=new Intent();
                intent.setClass(getActivity(), AddDiaryActivity.class);
                startActivity(intent);
                break;
        }
    }

    class WearDiaryAdapter extends BaseAdapter {


            @Override
            public int getCount() {
                return wearDiaryEntity.getData().size();
            }

            @Override
            public Object getItem(int i) {
                return wearDiaryEntity.getData().get(i);
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
                    view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_wear_diary, null, false);
                    view.setTag(viewHolder);
                    viewHolder.yifuImageView = view.findViewById(R.id.yifuImageView);
                    viewHolder.dateTextView = view.findViewById(R.id.dateTextView);
                    viewHolder.leibieTextView = view.findViewById(R.id.leibieTextView);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
//                viewHolder.dateTextView.setText(wearDiaryEntity.getData().get(i).getDailyList().g);
                return view;
            }

            class ViewHolder {
                TextView dateTextView;
                TextView leibieTextView;
                ImageView yifuImageView;
            }
        }
}
