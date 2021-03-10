package by.microfeedblog.feedblog.configuration;

import by.microfeedblog.feedblog.web.interceptor.AccessTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private AccessTokenInterceptor accessTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessTokenInterceptor)
        .addPathPatterns("/post", "/post/**").excludePathPatterns("/post/findAll")
        .addPathPatterns("/user/deleteById", "/user/deleteByLogin", "/user/findById")
        .addPathPatterns("/category", "/category/**")
        .addPathPatterns("/tag", "/tga/**")
        .addPathPatterns("/like", "/like/**");
    }
}
