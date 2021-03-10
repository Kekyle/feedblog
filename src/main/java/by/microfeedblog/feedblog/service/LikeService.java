package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.Like;
import by.microfeedblog.feedblog.domain.Post;
import by.microfeedblog.feedblog.repository.LikeRepository;
import by.microfeedblog.feedblog.service.exception.LikeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public Like save(Like like){
        if (likeRepository.existsByPost(like.getPost())){
            throw new LikeException();
        } else {
            return likeRepository.save(like);
        }
    }

    public void deleteLikeFromPost(Post post){
        if (likeRepository.existsByPost(post)){
            likeRepository.deleteByPost(post);
        } else {
            throw new LikeException();
        }
    }

    public List<Like> findAllByPost(Post post){
        return likeRepository.findAllByPost(post);
    }
}
