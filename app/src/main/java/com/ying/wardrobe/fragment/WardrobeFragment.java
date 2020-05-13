package com.ying.wardrobe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wildma.pictureselector.PictureSelector;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;
import com.ying.wardrobe.activity.EditUserInfoActivity;
import com.ying.wardrobe.entity.ClothesEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WardrobeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "WardrobeFragment";
    private TextView addYifu;
    private ListView listView;
    private List<ClothesEntity> clothesEntities = new ArrayList<>();
    private WardrobeListAdapter listAdapter;
    private TextView duanxiuTextView;
    private TextView changkuTextView;
    private TextView qunziTextView;
    private TextView waitaoTextView;
    private TextView maoziTextView;

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
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    private void getClothes(int type) {
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.UpdateUser, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据

        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: " + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                    } else {
                        Toast.makeText(getActivity(), "用户信息更新失败，请稍后再试！", Toast.LENGTH_SHORT).show();
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

    /**
     * 初始化View
     *
     * @param view
     */
    private void initView(View view) {
        listView = view.findViewById(R.id.listView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addYifu:
                PictureSelector
                        .create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false, 200, 200, 1, 1);
                break;
            case R.id.duanxiuTextView:

                break;
            case R.id.waitaoTextView:

                break;
            case R.id.changkuTextView:

                break;
            case R.id.qunziTextView:

                break;
            case R.id.maoziTextView:

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            }
        }
    }

    class WardrobeListAdapter extends BaseAdapter

    {

        @Override
        public int getCount() {
            return clothesEntities.size();
        }

        @Override
        public Object getItem(int i) {
            return clothesEntities.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view.setTag(viewHolder);
                view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_wardrobe, null, false);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            return view;
        }

        class ViewHolder {

        }
    }
}
