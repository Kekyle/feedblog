package by.microfeedblog.feedblog.web.resource;

import by.microfeedblog.feedblog.domain.Tag;
import by.microfeedblog.feedblog.service.TagService;
import by.microfeedblog.feedblog.service.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tag")
public class TagResource {

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Tag> save(@Valid @RequestBody TagDto tag){
        return ResponseEntity.ok(tagService.save(tag));
    }

    @PutMapping
    public ResponseEntity<Tag> update(@Valid @RequestBody TagDto tag){
        return ResponseEntity.ok(tagService.update(tag));
    }

    @Transactional
    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(long id){
        tagService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/deleteByName")
    public ResponseEntity<?> deleteByName(String name){
        tagService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Tag>> findAll(){
        return ResponseEntity.ok(tagService.findAll());
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<Tag> findById(long id){
        return ResponseEntity.ok(tagService.findById(id));
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<Tag> findByName(String name){
        return ResponseEntity.ok(tagService.findByName(name));
    }
}
