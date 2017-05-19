package bpsound.carousellred;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elegantuniv on 2017. 5. 19..
 */

public class MainFeedAdapter extends BaseAdapter{

    private LayoutInflater mInflater;

    public MainFeedAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view != null) {
            holder = (ViewHolder)view.getTag();
        } else {
            view = mInflater.inflate(R.layout.layout_row_feeditem, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }



        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tvTopic) TextView tvTopic;
        @BindView(R.id.btnUpVote) Button btnUpVote;
        @BindView(R.id.btnDownVote) Button btnDownVote;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
