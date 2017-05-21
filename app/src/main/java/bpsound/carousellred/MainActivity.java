package bpsound.carousellred;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lvMain) ListView mLvMain;
    @BindView(R.id.etPost) EditText mEtPost;

    private MainFeedAdapter mListAdapter;
    private ArrayList<FeedItem> mListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mListItems = new ArrayList<>();
        mListAdapter = new MainFeedAdapter(this, mListItems);
        mListAdapter.setOnListener(new MainFeedAdapter.OnListener() {
            @Override
            public void upVote(FeedItem item) {
                item.upvote += 1;
                sortList();
            }

            @Override
            public void downVote(FeedItem item) {
                item.downvote += 1;
                sortList();
            }
        });
        mLvMain.setAdapter(mListAdapter);
    }

    private void sortList() {
        final Comparator<FeedItem> comparator = new Comparator<FeedItem>() {
            @Override
            public int compare(FeedItem o1, FeedItem o2) {
                return (o2.upvote-o1.upvote);
            }
        };
        Collections.sort(mListItems, comparator);
        mListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnSubmit)
    public void onSubmitPost() {
        String post = mEtPost.getText().toString();
        if(post.length()>0){
            mEtPost.setText("");

            FeedItem item = new FeedItem(post, 0, 0);
            mListItems.add(item);
            sortList();
        }
    }
}
