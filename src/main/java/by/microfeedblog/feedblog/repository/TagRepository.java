package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);
}
