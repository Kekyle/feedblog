package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.*;
import by.microfeedblog.feedblog.repository.*;
import by.microfeedblog.feedblog.service.dto.LikeDto;
import by.microfeedblog.feedblog.service.dto.PostCommentDto;
import by.microfeedblog.feedblog.service.dto.PostDto;
import by.microfeedblog.feedblog.service.dto.TagDto;
import by.microfeedblog.feedblog.service.exception.PostNotFoundException;
import by.microfeedblog.feedblog.service.exception.PostTitleException;
import by.microfeedblog.feedblog.service.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ViewHistoryRepository viewHistoryRepository;

    public Post save(PostDto post) {
        if (postRepository.existsByTitle(post.getTitle())) {
            throw new PostTitleException("Post with this title is already exist");
        } else {
            Post currentPost = postMapper.dtoToDomain(post);
            List<TagDto> tagDto = post.getTagDto();
            List<Tag> tags = new ArrayList<>();
            for (TagDto dto : tagDto) {
                Tag tag = tagRepository.findById(dto.getId()).get();
                tags.add(tag);
            }
            currentPost.setTags(tags);
            currentPost.setCreateDate(new Date());
            currentPost.setUpdateDate(new Date());
            return postRepository.save(currentPost);
        }
    }

    public void saveComment(PostCommentDto postCommentDto) {
        if (postRepository.existsById(postCommentDto.getId())) {
            Post one = postRepository.getOne(postCommentDto.getId());
            one.getComments().add(new Comment(postCommentDto.getComment().getComment()));
            postRepository.save(one);
        } else {
            throw new PostNotFoundException();
        }
    }

    public List<Like> likePost(LikeDto like) {
        Post post = postRepository.findById(like.getPostId()).get();
        User user = userRepository.getOne(like.getUserId());
        List<Like> likes = post.getLikes();
        for (Like like1 : likes) {
            if (like1.getUser().getId() != like.getUserId()) {
                likeRepository.save(new Like(post, user));
            }
        }
        return likeRepository.findAllByPost(post);
    }

    public void approve(long id, User user) {
        if (postRepository.existsById(id) && user.getRole().equals(Role.ADMIN)) {
            Post post = postRepository.getOne(id);
            post.setApproved(true);
            postRepository.save(post);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public Post update(PostDto post) {
        if (postRepository.existsById(post.getId())) {
            Post currentPost = postMapper.dtoToDomain(post);
            currentPost.setUpdateDate(new Date());
            currentPost.setApproved(false);
            return postRepository.save(currentPost);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByApproved() {
        return postRepository.findAllByApproved(true);
    }

    public List<Post> findAllNotApproved() {
        return postRepository.findAllByApproved(false);
    }

    public List<Post> findAllByCategory(Category category) {
        return postRepository.findAllByCategory(category);
    }

    public List<Post> findAllByTags(Tag tag) {
        return postRepository.findAllByTags(tag);
    }

    public Post findById(long id) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            updateViewCount(post);
            ViewHistory viewHistory = null;
            List<ViewHistory> allByUserId = viewHistoryRepository.findAllByUserId(post.getUser().getId());
            if (allByUserId.size() != 0) {
                viewHistory = allByUserId.get(0);
            }
            if (allByUserId.size() == 10){
                viewHistoryRepository.delete(viewHistory);
            }
            viewHistoryRepository.save(new ViewHistory(post.getId(), post.getUser().getId(), new Date()));
            return postRepository.findById(id).get();
        } else throw new PostNotFoundException("Post not found");
    }

    private void updateViewCount(Post post) {
        long viewCount = post.getViewCount();
        post.setViewCount(++viewCount);
        postRepository.save(post);
    }

    public Post findByTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            Post post = postRepository.findByTitle(title);
            updateViewCount(post);
            return postRepository.findByTitle(title);
        } else throw new PostTitleException();
    }

    public Post findByDescription(String description) {
        if (postRepository.existsByDescription(description)) {
            Post post = postRepository.findByDescription(description);
            updateViewCount(post);
            return postRepository.findByDescription(description);
        } else throw new PostNotFoundException("Post not found");
    }

    public void deleteById(long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void deleteByTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            postRepository.deleteByTitle(title);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }

    public void deleteByDescription(String description) {
        if (postRepository.existsByDescription(description)) {
            postRepository.deleteByDescription(description);
        } else {
            throw new PostNotFoundException("Post not found");
        }
    }
}
