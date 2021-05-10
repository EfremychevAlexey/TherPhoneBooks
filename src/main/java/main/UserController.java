package main;

import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //получения списка всех пользователей (владельцев телефонных книжек)
    @GetMapping("/users/")
    public List<User> users() {
        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (User u : userIterable) {
            users.add(u);
        }
        return users;
    }

    //создание пользователя
    @PostMapping("/users/")
    public int add(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    //получения пользователя по id
    @GetMapping("/users/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalUser.get(), HttpStatus.OK);
    }

    // удаления пользователя по id
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return userRepository.existsById(id) ? new ResponseEntity(HttpStatus.NOT_MODIFIED) :
                    new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //редактирование имени пользователя
    @PutMapping("/users/{id}")
    public ResponseEntity setNameUser(@PathVariable int id, @PathVariable String name) {
        ResponseEntity responseEntity = getById(id);
        User user = (User) responseEntity.getBody();
        if (user != null) {
            userRepository.deleteById(id);
            user.setName(name);
            return userRepository.save(user) != null ? new ResponseEntity(HttpStatus.OK) :
                    new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // поиск пользователей по имени (или его части)
    @GetMapping("/users/{id}/")
    public ResponseEntity list(@PathVariable String str) {
        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> list = new ArrayList<>();
        for (User u : userIterable) {
            if (u.getName().contains(str)) {
                list.add(u);
            }
        }
        return !list.isEmpty() ?
                new ResponseEntity(list, HttpStatus.OK) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}

