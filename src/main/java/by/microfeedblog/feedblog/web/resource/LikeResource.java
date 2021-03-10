package by.microfeedblog.feedblog.web.resource;

import by.microfeedblog.feedblog.domain.Like;
import by.microfeedblog.feedblog.domain.Post;
import by.microfeedblog.feedblog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/like")
public class LikeResource {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<Like> save(@RequestBody Like like){
        return ResponseEntity.ok(likeService.save(like));
    }

    @DeleteMapping(path = "/deleteLike")
    public ResponseEntity<?> deleteLike(Post post){
        likeService.deleteLikeFromPost(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/findAllByPost")
    public ResponseEntity<List<Like>> findAllLikesFromPost(Post post){
        return ResponseEntity.ok(likeService.findAllByPost(post));
    }
}
