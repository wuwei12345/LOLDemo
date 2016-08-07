package demo.lol.com.loldemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import demo.lol.com.loldemo.Bean.OneBean;
import demo.lol.com.loldemo.R;

/**
 * Created by Administrator on 2016/7/29.
 */
public class OneAdapter extends BaseAdapter {
    List<OneBean.ListBean> list;
    public void setDate(List<OneBean.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.one_item, viewGroup, false);
            viewHolder.oneImageView= (ImageView) view.findViewById(R.id.one_imageView);
            viewHolder.oneTime= (TextView) view.findViewById(R.id.one_time);
            viewHolder.oneDate= (TextView) view.findViewById(R.id.one_date);
            viewHolder.oneTitle= (TextView) view.findViewById(R.id.one_title);
            viewHolder.oneTitle2= (TextView) view.findViewById(R.id.one_title2);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Picasso.with(view.getContext()).load(list.get(i).getImg()).into(viewHolder.oneImageView);
        viewHolder.oneTitle.setText(list.get(i).getName());
        viewHolder.oneTitle2.setText(list.get(i).getName());
        viewHolder.oneDate.setText(list.get(i).getUpdateTime());
        viewHolder.oneTime.setText(list.get(i).getLenght());
        return view;
    }

    static class ViewHolder {
        ImageView oneImageView;
        TextView oneTitle;
        TextView oneTitle2;
        TextView oneDate;
        TextView oneTime;


    }
}
