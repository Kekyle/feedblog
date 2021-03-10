package by.microfeedblog.feedblog.web.interceptor;

import by.microfeedblog.feedblog.service.TokenService;
import by.microfeedblog.feedblog.web.exception.AccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Boolean.parseBoolean(environment.getProperty("token.security.mode"))){
            String header = request.getHeader("X-Access-Token");
            if (tokenService.containsToken(header)) {
                return true;
            }
            throw new AccessException();
        } else {
            return true;
        }
    }
}
