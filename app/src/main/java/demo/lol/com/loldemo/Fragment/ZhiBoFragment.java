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

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.lol.com.loldemo.Adapter.ZhiBoAdapter;
import demo.lol.com.loldemo.Bean.ZhiBoBean;
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
public class ZhiBoFragment extends Fragment implements interfaces {


    @Bind(R.id.zixun_list)
    ListView zixunList;
    ZhiBoBean bean;

    public ZhiBoFragment() {
        // Required empty public constructor
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    //设置Adapter
                    ZhiBoAdapter adapter = new ZhiBoAdapter();
                    adapter.SetDate(bean);
                    zixunList.setAdapter(adapter);
                    break;
                default:

                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zixun, container, false);
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
        //实力化bean
        bean = new ZhiBoBean();
        //列表单击事件
        zixunList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), videoActivity.class);
                intent.putExtra("paths", bean.getList().get(i).getQuality().get(0).getUrl());
                io.vov.vitamio.utils.Log.i("TAG","视屏地址："+bean.getList().get(i).getQuality().get(0).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initdata() {
        //接口
        String path = "http://www.demaxiya.com/app/index.php?m=live&showcount=0&__ii=867660022750031&__aa=80f79555ac504f4&_width=720&height=1280&version=10";
        try {
            OkHttpUtils.run(path).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(0);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //json字符串
                    String json = response.body().string();
                    //解析
                    String obj = JSONObject.parseObject(json).getString("list");
                    bean.setList(JSONArray.parseArray(obj.toString(), ZhiBoBean.ListBean.class));
                    //删除第一条和最后一条（此处有坑，因为接口会经常变不建议添加这两条）
                    bean.getList().remove(0);
                    bean.getList().remove(bean.getList().size()-1);
                    //执行Handler
                    handler.sendEmptyMessage(1);

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
