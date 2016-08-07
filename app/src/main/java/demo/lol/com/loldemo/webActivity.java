package demo.lol.com.loldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class webActivity extends AppCompatActivity {
    @Bind(R.id.webview)
    WebView webview;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        //获得id
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        //地址拼接
        url = "http://www.demaxiya.com/app/index.php?m=newsdetail&id=" + id
                + "&__ii=867660022750031&__aa=80f79555ac504f4&_width=720&height=1280&version=10";
        initView();
    }

    private void initView() {
        //允许JavaScrip
        webview.getSettings().setJavaScriptEnabled(true);
        //获得焦点
        webview.requestFocus();
        //加载地址
        webview.loadUrl(url);
    }
}
