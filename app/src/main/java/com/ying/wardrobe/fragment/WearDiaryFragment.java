package com.ying.wardrobe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

import java.util.ArrayList;
import java.util.List;

public class WearDiaryFragment extends BaseFragment implements View.OnClickListener {
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
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_History, RequestMethod.POST);
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
        switch (v.getId()) {
            case R.id.addDiary:
                Intent intent = new Intent();
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
                viewHolder.gridView = view.findViewById(R.id.gridView);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.dateTextView.setText(wearDiaryEntity.getData().get(i).get(0).getDate());
            viewHolder.leibieTextView.setText(getLeibie(wearDiaryEntity.getData().get(i)));
            GridViewAdapter gridViewAdapter = new GridViewAdapter(getPhotoUrls(wearDiaryEntity.getData().get(i)));
            viewHolder.gridView.setAdapter(gridViewAdapter);
            setListViewHeightBasedOnChildren(viewHolder.gridView);
            gridViewAdapter.notifyDataSetChanged();
            return view;
        }

        class ViewHolder {
            TextView dateTextView;
            TextView leibieTextView;
            ImageView yifuImageView;
            GridView gridView;
        }
    }

    private List<String> getPhotoUrls(List<WearDiaryEntity.DataBean> dataBeans) {
        List<String> urls = new ArrayList<>();
        for (WearDiaryEntity.DataBean dataBean : dataBeans) {
            urls.add(dataBean.getYifu().getPhoto());
        }
        Log.d(TAG, "getPhotoUrls: " + urls.toString());
        return urls;
    }

    private String getLeibie(List<WearDiaryEntity.DataBean> dataBeans) {
        String leibie = "类比：";
        for (WearDiaryEntity.DataBean dataBean :
                dataBeans) {
            if (dataBean.getYifu().getType().equals("duanxiu")) {
                if (!leibie.contains("短袖")) {
                    leibie = leibie + "  短袖";
                }
            } else if (dataBean.getYifu().getType().equals("changku")) {
                if (!leibie.contains("长裤")) {
                    leibie = leibie + "  长裤";
                }
            } else if (dataBean.getYifu().getType().equals("waitao")) {
                if (!leibie.contains("外套")) {
                    leibie = leibie + "  外套";
                }
            } else if (dataBean.getYifu().getType().equals("qunzi")) {
                if (!leibie.contains("裙子")) {
                    leibie = leibie + "  裙子";
                }
            } else if (dataBean.getYifu().getType().equals("maozi")) {
                if (!leibie.contains("帽子")) {
                    leibie = leibie + "  帽子";
                }
            }
        }
        return leibie;
    }

    class GridViewAdapter extends BaseAdapter {
        List<String> photoUrls = new ArrayList<>();

        public GridViewAdapter(List<String> photoUrls) {
            this.photoUrls.clear();
            this.photoUrls.addAll(photoUrls);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return photoUrls.size();
        }

        @Override
        public Object getItem(int position) {
            return photoUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "getView: ");
            GridViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new GridViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_wear_diary_grid, null, false);
                viewHolder.image = convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (GridViewHolder) convertView.getTag();
            }
            Glide.with(getActivity()).load(photoUrls.get(position)).into(viewHolder.image);
            return convertView;
        }

        class GridViewHolder {
            ImageView image;
        }
    }
    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 4;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }
}
