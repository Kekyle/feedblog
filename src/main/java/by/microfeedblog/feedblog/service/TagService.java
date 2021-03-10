package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.Tag;
import by.microfeedblog.feedblog.repository.TagRepository;
import by.microfeedblog.feedblog.service.dto.TagDto;
import by.microfeedblog.feedblog.service.exception.TagIsAlreadyExistException;
import by.microfeedblog.feedblog.service.exception.TagNotFoundException;
import by.microfeedblog.feedblog.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    public Tag save (TagDto tag){
        if (tagRepository.existsByName(tag.getName())){
            throw new TagIsAlreadyExistException("Tag is already exist");
        } else {
            Tag currentTag = tagMapper.dtoToDomain(tag);
            return tagRepository.save(currentTag);
        }
    }

    public Tag update(TagDto tag){
        if (tagRepository.existsById(tag.getId())){
            Tag currentTag = tagMapper.dtoToDomain(tag);
            return tagRepository.save(currentTag);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public void deleteById(long id){
        if (tagRepository.existsById(id)){
            tagRepository.deleteById(id);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public void deleteByName(String name){
        if (tagRepository.existsByName(name)){
            tagRepository.deleteByName(name);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public List<Tag> findAll(){
        return tagRepository.findAll();
    }

    public Tag findById(long id){
        if (tagRepository.existsById(id)){
            return tagRepository.findById(id).get();
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }

    public Tag findByName(String name){
        if (tagRepository.existsByName(name)){
            return tagRepository.findByName(name);
        } else {
            throw new TagNotFoundException("Tag not found");
        }
    }
}
