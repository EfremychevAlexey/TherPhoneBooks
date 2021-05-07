package main.model;
import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    //@OneToOne(optional = false, cascade = CascadeType.ALL)
    private PhoneBook phoneBook;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }
}
