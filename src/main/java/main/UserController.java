package main;

import main.model.Contact;
import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //получения списка всех пользователей (владельцев телефонных книжек)
    @GetMapping("/users")
    public ResponseEntity users() {
        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for (User u : userIterable) {
            users.add(u);
        }
        return (users != null || users.isEmpty()) ?
                new ResponseEntity(users, HttpStatus.OK) :
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //создание пользователя
    @PostMapping("/users")
    public ResponseEntity add(User user) {
        User newUser = userRepository.save(user);
        return new ResponseEntity(newUser.getId(), HttpStatus.CREATED);
    }

    //получения пользователя по id
    @GetMapping("/users/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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
        ResponseEntity responseEntity = get(id);
        User user = (User) responseEntity.getBody();
        if (user != null) {
            user.setName(name);
            return (user.getName() == name) ? new ResponseEntity(HttpStatus.CREATED) :
                    new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // поиск пользователей по имени (или его части)
    @GetMapping("/users")
    public ResponseEntity list(@PathVariable String str) {
        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> list = new ArrayList<>();
        for (User u : userIterable) {
            if (u.getName().contains(str)) {
                list.add(u);
            }
        }
        return !list.isEmpty()?
                new ResponseEntity(list, HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //добавление записи в телефонную книку пользователя
    @PostMapping("/users/{id}/phone_book")
    public ResponseEntity addContact(int userId, Contact contact) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Map<Integer, Contact> map = optionalUser.get().getPhoneBook();
            if (map != null) map.put(contact.getId(), contact);
            return (map.get(userId).equals(contact)) ?
                    new ResponseEntity(HttpStatus.CREATED) :
                    new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    //получение записи телефонной книжки пользователя по id
    @GetMapping("/users/{id}/phone_book/{id}")
    public ResponseEntity getContact(int userId, int phoneId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Map<Integer, Contact> map = user.get().getPhoneBook();
            if (map.containsKey(phoneId)) {
                return new ResponseEntity(map.get(phoneId), HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //получения списка всех записей в телефонной книжке пользователя
    @GetMapping("/users/{id}/phone_book")
    public ResponseEntity getlist(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Contact> list = user.get().getPhoneBook().values().stream().collect(Collectors.toList());
            if (list != null) {
                return ResponseEntity.status(HttpStatus.OK).body(list);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //удаление записи в телефонной книжке пользователя по id
    @DeleteMapping("/users/{id}/phone_book/{id}")
    public ResponseEntity delbyId(int userId, int phoneId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Map<Integer, Contact> map = user.get().getPhoneBook();
            Contact contact = user.get().getPhoneBook().get(phoneId);
            if (map.containsKey(phoneId)) {
                return map.remove(phoneId, contact) ?
                        new ResponseEntity(HttpStatus.OK) :
                        new ResponseEntity(HttpStatus.NOT_MODIFIED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //поиск телефонной записи по номеру телефон
    @GetMapping("/users/{id}/phone_book")
    public ResponseEntity getContact(int userId, String str) {
        Optional<User> user = userRepository.findById(userId);
        List<Contact> list;
        if (user.isPresent()) {
            list = user.get().getPhoneBook().values().stream().
                    filter(c -> c.getPhone().contains(str)).collect(Collectors.toList());
            if (!list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(list);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
