package by.microfeedblog.feedblog.web.resource;

import by.microfeedblog.feedblog.domain.Token;
import by.microfeedblog.feedblog.domain.User;
import by.microfeedblog.feedblog.domain.ViewHistory;
import by.microfeedblog.feedblog.service.TokenService;
import by.microfeedblog.feedblog.service.UserService;
import by.microfeedblog.feedblog.service.dto.BornDateDto;
import by.microfeedblog.feedblog.web.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.update(user));
    }

    @PutMapping(path = "/updateRole")
    public ResponseEntity<User> updateRole(@RequestBody User user){
        return ResponseEntity.ok(userService.updateRoleToAdmin(user));
    }

    @GetMapping(path = "/login")
    public String login(String login, String password) {
        User user = userService.findByLogin(login);
        if (user.getPassword().equals(password)) {
            UUID token = UUID.randomUUID();
            tokenService.save(new Token(user.getLogin(), token.toString()));
            return token.toString();
        }
        throw new LoginException();
    }

    @Transactional
    @GetMapping(path = "/logout")
    public void logout(String login) {
        tokenService.deleteTokenByLogin(login);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/deleteByLogin")
    public ResponseEntity<?> deleteByLogin(String login){
        userService.deleteByLogin(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/viewHistory")
    public ResponseEntity<List<ViewHistory>> viewHistory(long userId){
        return ResponseEntity.ok(userService.findTenLastViewPost(userId));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(path = "/findByLogin")
    public ResponseEntity<User> findByLogin(String login){
        return ResponseEntity.ok(userService.findByLogin(login));
    }

    @GetMapping(path = "/findAllByName")
    public ResponseEntity<List<User>> findAllByName(String name){
        return ResponseEntity.ok(userService.findAllByName(name));
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<User> findById(long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(path = "/findAllByBornDate")
    public ResponseEntity<List<User>> findAllByBornDate(@RequestBody BornDateDto bornDate){
        return ResponseEntity.ok(userService.findAllByBornDate(bornDate));
    }
}
