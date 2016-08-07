package demo.lol.com.loldemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.lol.com.loldemo.Interface.interfaces;
import demo.lol.com.loldemo.R;
import demo.lol.com.loldemo.Adapter.ShouYeAdapter;


public class ShouyeFragment extends Fragment implements interfaces {
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.mvp)
    ViewPager mvp;
    //两个Fragment
    OneFragment oneFragment;
    videoFragment videoFragment;
    //tab 名
    List<String> listtitle;
    //fragment列表
    List<Fragment> fragments;
    //适配器
    ShouYeAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
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
        //实例化
        oneFragment=new OneFragment();
        videoFragment=new videoFragment();
    }

    @Override
    public void initdata() {
        fragments=new ArrayList<>();
        fragments.add(oneFragment);
        fragments.add(videoFragment);
    listtitle=new ArrayList<>();
        listtitle.add("首页");
        listtitle.add("资讯");
        //设置tablayout模式
        tab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initviewoper() {
        tab.addTab(tab.newTab().setText(listtitle.get(0)));
        tab.addTab(tab.newTab().setText(listtitle.get(0)));
        adapter=new ShouYeAdapter(getActivity().getSupportFragmentManager(),fragments,listtitle);
        mvp.setAdapter(adapter);
        tab.setupWithViewPager(mvp);
    }
}
