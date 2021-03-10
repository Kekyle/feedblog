package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.Like;
import by.microfeedblog.feedblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPost(Post post);
    List<Like> findAllByPost(Post post);
    void deleteByPost(Post post);
}
