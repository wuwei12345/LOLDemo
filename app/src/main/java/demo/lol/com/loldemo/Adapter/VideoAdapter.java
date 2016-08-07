package demo.lol.com.loldemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import demo.lol.com.loldemo.Bean.VideoBean;
import demo.lol.com.loldemo.R;

/**
 * Created by Administrator on 2016/8/2.
 */
public class VideoAdapter extends BaseAdapter {
    VideoBean bean;

    public VideoAdapter() {

    }
    public void setDate(VideoBean bean){
        this.bean = bean;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return bean.getList().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item, viewGroup, false);
            holder.img= (ImageView) view.findViewById(R.id.video_image);
            holder.title= (TextView) view.findViewById(R.id.video_title);
            holder.time= (TextView) view.findViewById(R.id.video_time);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.title.setText(bean.getList().get(i).getTitle());
        Picasso.with(viewGroup.getContext()).load(bean.getList().get(i).getImg()).into(holder.img);
        String updateTime = bean.getList().get(i).getUpdateTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        Long time = new Long(updateTime);
        String d = format.format(time * 1000);
        holder.time.setText(d);
        return view;
    }
    class ViewHolder{
        ImageView img;
        TextView title,time;
    }
}
