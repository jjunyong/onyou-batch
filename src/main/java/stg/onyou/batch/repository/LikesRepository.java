package stg.onyou.batch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stg.onyou.batch.entity.Feed;
import stg.onyou.batch.entity.FeedLikes;

public interface LikesRepository extends JpaRepository<FeedLikes, Long> {
    List<FeedLikes> findByFeed(Feed feed);
}
