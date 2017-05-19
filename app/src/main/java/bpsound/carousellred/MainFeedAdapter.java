package bpsound.carousellred;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by elegantuniv on 2017. 5. 19..
 */

public class MainFeedAdapter extends BaseAdapter{

    public MainFeedAdapter() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int location) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    static class ViewHolder {

    }
}
