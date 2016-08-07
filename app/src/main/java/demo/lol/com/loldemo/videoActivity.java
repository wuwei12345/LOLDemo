package demo.lol.com.loldemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.lol.com.loldemo.Interface.interfaces;
import io.vov.vitamio.widget.VideoView;

public class videoActivity extends AppCompatActivity implements interfaces {

    io.vov.vitamio.widget.MediaController controller;
    String path;
    @Bind(R.id.lol_video)
    VideoView lolVideo;
    //屏幕尺寸大小为原始尺寸
    int mlayout = VideoView.VIDEO_LAYOUT_ZOOM;
    /**
     * 手势监听
     */
    GestureDetector mgestureDetector;
    /**
     * 当前声音
     */
    private int mVolume = -1;
    /**
     * 最大声音
     */
    private int mMaxVolume;
    /**音频管理器**/
    private AudioManager mAudioManager;
    /**
     * 当前亮度
     */
    float mBrightness = -1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initview();
        initdata();
        initviewoper();
    }

    @Override
    public void initview() {
        Intent intent = getIntent();
        path = intent.getStringExtra("paths");
        controller = new io.vov.vitamio.widget.MediaController(this);
        mgestureDetector = new GestureDetector(this, new MyGestureListener());
        //初始化音频管理器
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //获取系统的最大声音
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

    }

    @Override
    public void initdata() {
        lolVideo.setVideoURI(Uri.parse(path));
        lolVideo.setMediaController(controller);
        controller.setMediaPlayer(lolVideo);
        lolVideo.setVideoLayout(mlayout, 0);
        //改变屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        lolVideo.requestFocus();
        lolVideo.start();
    }

    @Override
    public void initviewoper() {

    }

    //处理手势
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断mgestureDetector是否进行过滑动，是则返回true对此次事件进行处理
        if (mgestureDetector.onTouchEvent(event)) {
            return true;
        }
        mVolume = -1;
        mBrightness = -1f;
        return super.onTouchEvent(event);
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        //双击
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //判断播放状态，true暂停，false继续播放
            if (lolVideo.isPlaying()) {
                lolVideo.pause();
            } else {
                lolVideo.start();
            }
            return true;
        }

        //滑动
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //获取坐标点
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            //窗口管理器
            Display disp = getWindowManager().getDefaultDisplay();
            //获得屏幕宽高
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            //经过计算判断是否滑动的是右边
            if (mOldX > windowWidth * 4.0 / 5) {
                //将滑动距离带进调节音量的方法中
                onVolumeSlide((mOldY - y) / windowWidth);
                //经过计算判断是否滑动的是左边
            } else if (mOldX < windowWidth / 5) {
                //将滑动距离带进调节亮度的方法中
                onBrightnessSlide((mOldY - y) / windowHeight);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    //改变音量
    private void onVolumeSlide(float percent) {
        //判断当前音量是否是-1，是则没有获取当前系统音量
        if (mVolume == -1) {
            //获得系统当前音量
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            //判断当前音量是否为负数，是则设置最小为0
            if (mVolume < 0) {
                //如果当前音量小于0则给变量赋最小的值0
                mVolume = 0;
            }
        }
        //计算用户滑动的距离
        int index = (int) ((percent * mMaxVolume) + mVolume);
        //判断该距离是否大于最大音量值，是则把给变量赋值为最大值，否则最小值
        if (index > mMaxVolume) {
            index = mMaxVolume;
        } else if (index < 0) {
            index = 0;
        }
        //设置音量
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
    }

    //滑动改变亮度
    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            //获得屏幕亮度
            mBrightness = getWindow().getAttributes().screenBrightness;
            //判断当前亮度是否小于0.01f，是的话则给变量赋值0.01f放置屏幕变黑
            if (mBrightness <= 0.01f)
                mBrightness = 0.01f;

        }
        /**WindowManager.LayoutParams 是 WindowManager 接口的嵌套类；
         它继承于 ViewGroup.LayoutParams； 它用于向WindowManager描述Window的管理策略。**/
        //获取window窗口属性
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        //将当前屏幕亮度加上我们移动的距离的值赋给lpa（窗口管理）
        lpa.screenBrightness = mBrightness + percent;
        //判断当前亮度是否大于1，是则设置为1,（亮度最大只能是1）
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
            //判断是否小于0.01，防止黑屏最小设置成0.01
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        //让设置好的亮度生效
        getWindow().setAttributes(lpa);
    }
}
