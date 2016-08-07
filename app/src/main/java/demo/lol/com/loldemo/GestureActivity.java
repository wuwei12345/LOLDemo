package demo.lol.com.loldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import io.vov.vitamio.widget.VideoView;

/**
 * 测试类
 */
public class GestureActivity extends AppCompatActivity {
    //手势识别的类
    GestureDetector gestureDetector;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        //实例化手势识别类
        gestureDetector = new GestureDetector(this,new MyGestureDetector());
    }
    //处理手势
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断我们的手势识别类是否进行了识别操作
        if (gestureDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    //识别手势操作的内部类
    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        //双击屏幕的方法
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            //判断媒体是否在播放状体，是：true,否：false
            if (videoView.isPlaying()){
                //暂停播放
                videoView.pause();
            }else{
                //继续播放
                videoView.start();
            }
            return true;
        }
    }
}
