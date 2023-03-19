package stg.onyou.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stg.onyou.batch.entity.Comment;
import stg.onyou.batch.entity.Feed;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByFeed(Feed feed);
}
