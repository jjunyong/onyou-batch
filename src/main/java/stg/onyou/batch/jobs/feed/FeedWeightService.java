package stg.onyou.batch.jobs.feed;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stg.onyou.batch.entity.Feed;
import stg.onyou.batch.repository.CommentRepository;
import stg.onyou.batch.repository.FeedRepository;
import stg.onyou.batch.repository.LikesRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class FeedWeightService {

    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FeedRepository feedRepository;

    private static final int LIKE_WEIGHT = 1;
    private static final int COMMENT_WEIGHT = 2;
    private static final int EXPIRATION_DAYS = 10; // 10일동안은 최근 게시글
    private static final int NEW_FEED_WEIGHT_BASE = 10; // 최근 게시글은 기본 10의 가중치.
    private static final int NEW_FEED_WEIGHT_FACTOR = 1; // ex) 9일 경과된 게시글은 10, 7일 경과된 게시글은 12, 2일 경과된 게시글은 15의 가중치.

    public void updateFeedWeight() {

        List<Feed> feeds = feedRepository.findAll();

        for (Feed feed : feeds) {
            Long likeCount = likesRepository.findByFeed(feed)
                    .stream()
                    .filter(feedLikes -> feedLikes.isOnOff() == true)
                    .count();

            Long commentCount = commentRepository.findCommentByFeed(feed)
                    .stream()
                    .count();


            LocalDate now = LocalDate.now();
            LocalDate feedDate = feed.getCreated().toLocalDate();
            long days = ChronoUnit.DAYS.between(feedDate, now);

            double ageFactor = Math.pow(0.95, days);

            double newFeedFactor = 1.0;

            if (days < EXPIRATION_DAYS) {
                long diffDays = EXPIRATION_DAYS - days;
                newFeedFactor = NEW_FEED_WEIGHT_BASE + NEW_FEED_WEIGHT_FACTOR * (diffDays - 1);
                if (newFeedFactor < NEW_FEED_WEIGHT_BASE) {
                    newFeedFactor = NEW_FEED_WEIGHT_BASE;
                }
            }

            double totalWeight = (likeCount * LIKE_WEIGHT + commentCount * COMMENT_WEIGHT)
                    * ageFactor * newFeedFactor;

            feed.setWeight(totalWeight);
        }
    }

}
