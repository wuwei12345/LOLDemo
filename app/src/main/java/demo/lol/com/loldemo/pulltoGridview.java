package demo.lol.com.loldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import butterknife.ButterKnife;

/**
 * 测试类
 */
public class pulltoGridview extends AppCompatActivity {

    PullToRefreshGridView pulltogridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullto_gridview);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        //初始化控件
        pulltogridview= (PullToRefreshGridView) findViewById(R.id.pulltogridview);
        //设置刷新样式
        pulltogridview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置上下拉刷新
        pulltogridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                /**下拉刷新的操作**/
                pulltogridview.onRefreshComplete();//结束刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                /**上拉加载的操作**/
                pulltogridview.onRefreshComplete();//结束加载
            }
        });
    }
}
