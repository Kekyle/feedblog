package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.Category;
import by.microfeedblog.feedblog.domain.Post;
import by.microfeedblog.feedblog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    Post findByDescription(String description);

    List<Post> findAllByApproved(boolean isApproved);

    List<Post> findAllByCategory(Category category);

    List<Post> findAllByTags(Tag tag);

    void deleteByTitle(String title);

    void deleteByDescription(String description);

    boolean existsByTitle(String title);

    boolean existsByDescription(String description);
}
