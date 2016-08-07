package demo.lol.com.loldemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class ShouYeAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> list_Title;

    public ShouYeAdapter(FragmentManager fm, List<Fragment> list, List<String> list_Title) {
        super(fm);
        this.list = list;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return list!=null?list.get(position):null;
    }

    @Override
    public int getCount() {
        return list_Title!=null?list_Title.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position%list_Title.size());
    }
}
