package bpsound.carousellred;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elegantuniv on 2017. 5. 19..
 * The adapter for listview
 * Adapt to viewholder pattern for reusing ui elements
 */

public class MainFeedAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private List<FeedItem> mFeedItems;
    private OnListener mListener;

    public MainFeedAdapter(Context context, List<FeedItem> feedItems) {
        this.mInflater = LayoutInflater.from(context);
        this.mFeedItems = feedItems;
    }

    @Override
    public int getCount() {
        return mFeedItems.size();
    }

    @Override
    public FeedItem getItem(int location) {
        return mFeedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view != null) {
            holder = (ViewHolder)view.getTag();
        } else {
            view = mInflater.inflate(R.layout.layout_row_feeditem, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.tvTopic.setText(getItem(position).topic);
        holder.btnUpVote.setText("UpVote ("+getItem(position).upvote+")");
        holder.btnDownVote.setText("DownVote ("+getItem(position).downvote+")");

        holder.btnUpVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.upVote(getItem(position));
                }
            }
        });

        holder.btnDownVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.downVote(getItem(position));
                }
            }
        });

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

    /*
        View-observer pattern
     */
    public void setOnListener(OnListener listener) {
        this.mListener = listener;
    }

    public interface OnListener {
        void upVote(FeedItem item);
        void downVote(FeedItem item);
    }
}
