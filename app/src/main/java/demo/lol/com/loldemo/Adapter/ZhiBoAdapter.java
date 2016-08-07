package demo.lol.com.loldemo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import demo.lol.com.loldemo.Bean.ZhiBoBean;
import demo.lol.com.loldemo.R;

/**
 * Created by Administrator on 2016/8/2.
 */
public class ZhiBoAdapter extends BaseAdapter{
    ZhiBoBean bean;
    public void SetDate(ZhiBoBean bean){
        this.bean=bean;
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
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zhibo_item,viewGroup,false);
            holder.img= (ImageView) view.findViewById(R.id.zhibo_img);
            holder.tx1= (TextView) view.findViewById(R.id.zhibo_title);
            holder.tx2= (TextView) view.findViewById(R.id.zhibo_time);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.tx2.setText(bean.getList().get(i).getUpdateTime());
        holder.tx1.setText(bean.getList().get(i).getName());
        Picasso.with(viewGroup.getContext()).load(bean.getList().get(i).getImg()).into(holder.img);
        return view;
    }
    class ViewHolder{
    ImageView img;
        TextView tx1,tx2;
    }
}
