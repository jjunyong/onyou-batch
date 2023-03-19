package stg.onyou.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stg.onyou.batch.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
