package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.Map;
import java.util.TreeMap;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column( )
    private Map<Integer, Contact> phoneBook;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Contact> getPhoneBook() {
        return phoneBook;
    }
}
