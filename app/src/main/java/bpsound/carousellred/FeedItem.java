package bpsound.carousellred;

/**
 * Created by elegantuniv on 2017. 5. 19..
 */

public class FeedItem {
    public String topic;
    public int upvote;
    public int downvote;

    public FeedItem(String topic, int upvote, int downvote){
        this.topic = topic;
        this.upvote = upvote;
        this.downvote = downvote;
    }
}
