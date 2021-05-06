/*package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneBookController {
    //получения списка всех пользователей (владельцев телефонных книжек)
    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    //public List<User> users(){
        return Storage.getAllUsers();
    }

    //создание пользователя
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int addUser(User user){
        Storage.addUser(user);
        return user.getId();
    }
 /*
    //получения пользователя по id
    @RequestMapping(value = "/users/id", method = RequestMethod.GET)
    public User user(int userId){
        return Storage.getUser(userId);
    }

    // удаления пользователя по id
    @RequestMapping(value = "/users/id", method = RequestMethod.DELETE)
    public void deleteUser(int userId){
        Storage.deleteUser(userId);
    }

    //редактирование имени пользователя
    @RequestMapping(value = "/users/id", method = RequestMethod.PATCH)
    public void setNameUser(int userId, String name){
        Storage.getUser(userId).setName(name);
    }

    //создание новой записи в телефонной книжке пользователя
    @RequestMapping(value = "/users/id/phone_book/", method = RequestMethod.POST)
    public int addContact(int userId, String phone, String name){
        return Storage.getUser(userId).addContact(phone, name);
    }

    //получение записи телефонной книжки пользователя по id
    @RequestMapping(value = "/users/id/phone_book/", method = RequestMethod.POST)
    public Map<String, String> contact(int userId, int phoneId){
        return Storage.getUser(userId).getContact(phoneId);
    }

    //удаление записи в телефонной книжке пользователя по id
    @RequestMapping(value = "/users/id", method = RequestMethod.DELETE)
    public void delbyId(int userId, int phoneId){
        Storage.getUser(userId).delContact(phoneId);
    }

    //редактирования имени в записи телефонной книжки пользователя по номеру телефона
    @RequestMapping(value = "/users/id", method = RequestMethod.DELETE)
    public void patchNameContact(int userId, String phone){
        Storage.getUser(userId).delContact(phoneId);
    }
    //редактирование номера телефона в телефонной книжке пользователя по имени
    @RequestMapping(value = "/users/id", method = RequestMethod.DELETE)
    public void patchNameContact(int userId, String name){
        Storage.getUser(userId).delContact(phoneId);
    }
    //удаление записи в телефонной книжке пользователя по id
    //получения списка всех записей в телефонной книжке пользователя
    // поиск пользователей по имени (или его части)*
    // поиск телефонной записи по номеру телефона


}
*/
