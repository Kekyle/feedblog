package by.microfeedblog.feedblog.repository;

import by.microfeedblog.feedblog.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByToken(String token);
}
