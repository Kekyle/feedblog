package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.Category;
import by.microfeedblog.feedblog.repository.CategoryRepository;
import by.microfeedblog.feedblog.service.dto.CategoryDto;
import by.microfeedblog.feedblog.service.exception.CategoryIsAlreadyExistException;
import by.microfeedblog.feedblog.service.exception.CategoryNotFoundException;
import by.microfeedblog.feedblog.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public Category save(CategoryDto category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryIsAlreadyExistException("Category is already exist");
        } else {
            Category currentCategory = categoryMapper.dtoToDomain(category);
            return categoryRepository.save(currentCategory);
        }
    }

    public Category update(CategoryDto category) {
        if (categoryRepository.existsById(category.getId())) {
            Category currentCategory = categoryMapper.dtoToDomain(category);
            return categoryRepository.save(currentCategory);
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public Category findById(long id) {
        if (categoryRepository.existsById(id)) {
            return categoryRepository.findById(id).get();
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public Category findByName(String name) {
        if (categoryRepository.existsByName(name)) {
            return categoryRepository.findByName(name);
        }
        throw new CategoryNotFoundException("Category not found");
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void deleteById(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    public void deleteByName(String name) {
        if (categoryRepository.existsByName(name)) {
            categoryRepository.deleteByName(name);
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }
}
