package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.Token;
import by.microfeedblog.feedblog.repository.TokenRepository;
import by.microfeedblog.feedblog.service.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token save(Token token){
        if (tokenRepository.existsByToken(token.getToken())){
            throw new TokenException();
        } else {
            return tokenRepository.save(token);
        }
    }

    public void deleteTokenByLogin(String login){
        if (tokenRepository.existsByLogin(login)){
            tokenRepository.deleteByLogin(login);
        } else {
            throw new TokenException();
        }
    }

    public boolean containsTokenByLogin(String login){
        return tokenRepository.existsByLogin(login);
    }

    public boolean containsToken(String token){
        if (token == null) {
            throw new TokenException();
        } else {
            return tokenRepository.existsByToken(token);
        }
    }
}
