package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByComment(String comment);
    void deleteByComment(String comment);
    boolean existsByComment(String comment);
}
