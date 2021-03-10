package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
    List<ViewHistory> findAllByUserId(long userId);
    ViewHistory findByPostId(long postId);
}
