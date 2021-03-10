package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.Comment;
import by.microfeedblog.feedblog.repository.CommentRepository;
import by.microfeedblog.feedblog.repository.PostRepository;
import by.microfeedblog.feedblog.service.exception.CommentNotFoundException;
import by.microfeedblog.feedblog.service.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public Comment update(Comment comment){
        if (commentRepository.existsById(comment.getId())){
            return commentRepository.save(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment findById(long id){
        if (commentRepository.existsById(id)){
            return commentRepository.findById(id).get();
        } else {
            throw new CommentNotFoundException();
        }
    }

    public Comment findByComment(String comment){
        if (commentRepository.existsByComment(comment)){
            return commentRepository.findByComment(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

//    public List<Comment> findAllByPostId(long postId){
//        if (postRepository.existsById(postId)){
//            return commentRepository.findAllByPostId(postId);
//        } else {
//            throw new PostNotFoundException();
//        }
//    }

    public void deleteById(long id){
        if (commentRepository.existsById(id)){
            commentRepository.deleteById(id);
        } else {
            throw new CommentNotFoundException();
        }
    }

    public void deleteByComment(String comment){
        if (commentRepository.existsByComment(comment)){
            commentRepository.deleteByComment(comment);
        } else {
            throw new CommentNotFoundException();
        }
    }
}
