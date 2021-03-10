package by.microfeedblog.feedblog.web.resource;

import by.microfeedblog.feedblog.domain.Category;
import by.microfeedblog.feedblog.service.CategoryService;
import by.microfeedblog.feedblog.service.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryDto category){
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> update(@Valid @RequestBody CategoryDto category){
        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<Category> findById(long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<Category> findByName(String name){
        return ResponseEntity.ok(categoryService.findByName(name));
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(long id){
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/deleteByName")
    public ResponseEntity<?> deleteByName(String name){
        categoryService.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
