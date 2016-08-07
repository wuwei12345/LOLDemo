package demo.lol.com.loldemo.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.lol.com.loldemo.Adapter.OneAdapter;
import demo.lol.com.loldemo.Bean.OneBean;
import demo.lol.com.loldemo.Interface.interfaces;
import demo.lol.com.loldemo.R;
import demo.lol.com.loldemo.Utils.OkHttpUtils;
import demo.lol.com.loldemo.videoActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements interfaces {
    int count = 10;
    String path;


    List<OneBean.ListBean> monebean;
    OneAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //设置Adapter
                    adapter = new OneAdapter();
                    adapter.setDate(monebean);
                    oneGridview.setAdapter(adapter);
                    break;
                case 1:
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    //刷新Adapter
                    adapter.setDate(monebean);
                    adapter.notifyDataSetChanged();
                    break;
                default:

                    break;
            }
        }
    };
    @Bind(R.id.one_gridview)
    PullToRefreshGridView oneGridview;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        initdata();
        initviewoper();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initview() {
        //设置刷新样式
        oneGridview.setMode(PullToRefreshBase.Mode.BOTH);
        //刷新监听
       oneGridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
           @Override
           public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
               count += 20;
               if (count<=100) {
                   run(path);
               }else{
                   Toast.makeText(getActivity(),"没有更多",Toast.LENGTH_SHORT).show();
               }
               oneGridview.onRefreshComplete();
           }

           @Override
           public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
               count += 20;
               if (count<=100) {
                   run(path);
               }else{
                   Toast.makeText(getActivity(),"没有更多",Toast.LENGTH_SHORT).show();
               }
               oneGridview.onRefreshComplete();
           }
       });
        //item点击事件
        oneGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),videoActivity.class);
                Toast.makeText(getActivity(),i+"",Toast.LENGTH_SHORT).show();
                intent.putExtra("paths",monebean.get(i).getQuality().get(0).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initdata() {
        run(path);
    }

    private void run(String path) {
        path = "http://www.demaxiya.com/app/index.php?m=index&showcount=" + count + "&__ii=867660022750031&__aa=80f79555ac504f4&_width=720&height=1280&version=10";
        Log.i("TAG", path);
        try {
            OkHttpUtils.run(path).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(1);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //json解析
                    String json = response.body().string();
                    Log.i("TAG", json);
                    String obj = JSONObject.parseObject(json).getString("list");
                    if (monebean == null) {
                        monebean = JSONArray.parseArray(obj.toString(), OneBean.ListBean.class);
                        handler.sendEmptyMessage(0);
                    } else {
                        monebean.addAll(JSONArray.parseArray(obj.toString(), OneBean.ListBean.class));
                        handler.sendEmptyMessage(2);
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initviewoper() {

    }
}
