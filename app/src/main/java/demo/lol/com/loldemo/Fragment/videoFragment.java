package demo.lol.com.loldemo.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.lol.com.loldemo.Adapter.VideoAdapter;
import demo.lol.com.loldemo.Bean.VideoBean;
import demo.lol.com.loldemo.Interface.interfaces;
import demo.lol.com.loldemo.R;
import demo.lol.com.loldemo.Utils.OkHttpUtils;
import demo.lol.com.loldemo.webActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class videoFragment extends Fragment implements interfaces {
    VideoAdapter adapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    //设置Adapter
                    adapter=new VideoAdapter();
                    adapter.setDate(mvideoBean);
                    videoLsitView.setAdapter(adapter);
                    break;
                case 2:
                    //刷新adapter
                    adapter.setDate(mvideoBean);
                    adapter.notifyDataSetChanged();
                    break;
                default:

                    break;
            }
        }
    };
    @Bind(R.id.video_lsitView)
    PullToRefreshListView videoLsitView;
    int count=0;
    public videoFragment() {
        // Required empty public constructor
    }

    VideoBean mvideoBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
        initdata();
        initviewoper();
    }

    @Override
    public void initview() {
        //实例化bean
        mvideoBean=new VideoBean();
        //设置刷新模式
        videoLsitView.setMode(PullToRefreshBase.Mode.BOTH);
        //刷新监听
        videoLsitView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                count+=12;
                initdata();
                //停止刷新
                videoLsitView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                count+=12;
                initdata();
                videoLsitView.onRefreshComplete();
            }
        });
        //item点击事件
        videoLsitView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), webActivity.class);
                intent.putExtra("id", mvideoBean.getList().get(i - 1).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initdata() {
        String path = "http://www.demaxiya.com/app/index.php?m=news&showcount="+count+"&typeid=0&__ii=867660022750031&__aa=80f79555ac504f4&_width=720&height=1280&version=10";
        try {
            OkHttpUtils.run(path).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(0);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //json解析
                    String json = response.body().string();
                    String obj = JSONObject.parseObject(json).getString("list");
                    //判断bean是否空，是：第一次记载数据，否：第n次加载，表示要刷新数据
                    if (mvideoBean.getList()==null) {
                        mvideoBean.setList(JSONArray.parseArray(obj.toString(), VideoBean.ListBean.class));
                        handler.sendEmptyMessage(1);
                    }else{
                        mvideoBean.getList().addAll(JSONArray.parseArray(obj.toString(), VideoBean.ListBean.class));
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
