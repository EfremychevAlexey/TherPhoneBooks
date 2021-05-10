package main;

import main.model.PhoneBookRepository;
import main.model.User;
import main.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneBookRepository bookRepository;

    @RequestMapping("/")
    public  String index(Model model){
        Iterable<User> userIterable = userRepository.findAll();
        ArrayList<User> users = new ArrayList<>();
        for(User user : userIterable){
            users.add(user);
        }
        model.addAttribute("user", users);
        model.addAttribute("usersCount",users.size());
        return "index";
    }
}
