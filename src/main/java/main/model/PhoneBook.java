package main.model;
import javax.persistence.*;
import java.util.*;

//@Entity
//@Table(name = "Users")
public class PhoneBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private TreeSet<Contact> phoneBook;

    public PhoneBook(String name){
        this.name = name;
        phoneBook = new TreeSet<>(Comparator.comparing(Contact::getName));
    }

    public int getId() {
        return userId;
    }

    public String getName(){
        return name;
    }

    /*public Set<Contact> list() {
        return phoneBook;
    }*/

    /*public List<Contact> list(String str) {
        ArrayList<Contact> list = new ArrayList<>();
        for (Contact e : phoneBook) {
            if (e.getName().contains(str) || e.getPhone().contains(str)) {
                list.add(e);
            }
        }
        return list;
    }*/

    /*public void add(Contact e){
        phoneBook.add(e);
        e.setPhoneBookId(getId());
        indexingList();
    }*/

    /*public void delete(int id){
        for (Contact e: phoneBook){
            if (e.getId() == id) {
                phoneBook.remove(e);
            }
        }
        indexingList();
    }*/

    /*private void indexingList(){
        int i = 1;
        for (Contact e: phoneBook){
            e.setId(i++);
        }
    }*/

}
