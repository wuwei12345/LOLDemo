package demo.lol.com.loldemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.lol.com.loldemo.Fragment.ShouyeFragment;
import demo.lol.com.loldemo.Fragment.ZhiBoFragment;
import demo.lol.com.loldemo.Interface.interfaces;

public class MainActivity extends AppCompatActivity implements interfaces, View.OnClickListener {

    @Bind(R.id.fragmentLayout1)
    FrameLayout fragmentLayout1;
    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    ShouyeFragment mshouye;
    ZhiBoFragment mZixun;
    FragmentManager manger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
        initdata();
        initviewoper();
    }

    @Override
    public void initview() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        manger = getSupportFragmentManager();
        Setselection(0);
    }

    private void Setselection(int i) {
        FragmentTransaction transaction = manger.beginTransaction();
        hindfragment(transaction);
        switch (i) {
            case 0:
                if (mshouye == null) {
                    mshouye = new ShouyeFragment();
                    transaction.add(R.id.fragmentLayout1, mshouye);
                } else {
                    transaction.show(mshouye);
                }
                break;
            case 1:
                if (mZixun == null) {
                    mZixun = new ZhiBoFragment();
                    transaction.add(R.id.fragmentLayout1, mZixun);
                } else {
                    transaction.show(mZixun);
                }
                break;
            default:

                break;
        }
        transaction.commit();
    }

    private void hindfragment(FragmentTransaction transaction) {
        if (mshouye != null) {
            transaction.hide(mshouye);
        }
        if (mZixun != null) {
            transaction.hide(mZixun);
        }
    }

    @Override
    public void initdata() {

    }

    @Override
    public void initviewoper() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Setselection(0);
                break;
            case R.id.btn2:
                Setselection(1);
                break;
            default:

                break;
        }
    }
}
