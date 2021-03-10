package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    void deleteByName(String name);

    boolean existsByName(String name);
}
