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

    /*
        To minimize the boilerplate codes, used butterknife library(https://github.com/JakeWharton/butterknife) to bind ui elements.
     */

    @BindView(R.id.lvMain) ListView mLvMain;
    @BindView(R.id.etPost) EditText mEtPost;

    private MainFeedAdapter mListAdapter;
    private ArrayList<FeedItem> mListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAdapter();
    }

    /*
        initialize and set listener to the adapter by using observer pattern.
        if upvote or downvote has proceed in each feed, update to the data in item then it proceed to sort list.
     */
    private void initAdapter(){
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

    /*
        sort list by upvote descending then notify as datasetchanged to adapter
     */
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

    /*
        Once button called 'Submit' clicked, if field in edittext is not blank,
         create new item and added it to the list.
         Then, it proceed to sort list.
     */
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
