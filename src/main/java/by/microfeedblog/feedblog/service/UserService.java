package by.microfeedblog.feedblog.service;

import by.microfeedblog.feedblog.domain.Post;
import by.microfeedblog.feedblog.domain.Role;
import by.microfeedblog.feedblog.domain.User;
import by.microfeedblog.feedblog.domain.ViewHistory;
import by.microfeedblog.feedblog.repository.UserRepository;
import by.microfeedblog.feedblog.repository.ViewHistoryRepository;
import by.microfeedblog.feedblog.service.dto.BornDateDto;
import by.microfeedblog.feedblog.service.exception.UserIsAlreadyExistException;
import by.microfeedblog.feedblog.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ViewHistoryRepository viewHistoryRepository;

    public User save(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new UserIsAlreadyExistException();
        } else {
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setRole(Role.USER);
            return userRepository.save(user);
        }
    }

    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            user.setUpdateDate(new Date());
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User updateRoleToAdmin(User user){
        if (userRepository.existsById(user.getId())){
            user.setUpdateDate(new Date());
            user.setRole(Role.ADMIN);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(long id){
        if (userRepository.existsById(id)){
            return userRepository.findById(id).get();
        } else {
            throw new UserNotFoundException();
        }
    }

    public User findByLogin(String login){
        if (userRepository.existsByLogin(login)){
            return userRepository.findByLogin(login);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<ViewHistory> findTenLastViewPost(long userId){
        return viewHistoryRepository.findAllByUserId(userId);
    }

    public List<User> findAllByName(String name){
        if (userRepository.existsByName(name)){
            return userRepository.findAllByName(name);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> findAllByBornDate(BornDateDto bornDate){
        Date bornDate1 = bornDate.getBornDate();
        if (userRepository.existsByBornDate(bornDate1)){
            return userRepository.findAllByBornDate(bornDate1);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteById(long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void deleteByLogin(String login){
        if (userRepository.existsByLogin(login)){
            userRepository.deleteByLogin(login);
        } else {
            throw new UserNotFoundException();
        }
    }
}
