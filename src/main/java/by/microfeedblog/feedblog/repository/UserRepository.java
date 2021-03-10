package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    List<User> findAllByName(String name);
    List<User> findAllByBornDate(Date bornDate);
    void deleteByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByName(String name);
    boolean existsByBornDate(Date bornDate);
}
