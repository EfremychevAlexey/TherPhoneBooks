package main.model;

import javax.persistence.Entity;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class PhoneBook {

    private int id;
    private String name;
    private User user;
    private Date dateCreation;
    private Date lastEntry;
    private Map<Integer, Contact> contacts;
    private Map<Integer, Contact> basket;

    public PhoneBook(User user){
        this.id = user.getId();
        name = "Owner:" + user.getName();
        this.user = user;
        dateCreation = new Date();
        contacts = new HashMap<>();
        basket = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
    public Date getLastEntry(){
        return lastEntry;
    }

    // добавить новый контакт,
    public boolean addContact(Contact contact){
        if(!contacts.containsKey(contact.getId())) {
            contacts.put(contact.getId(), contact);
            lastEntry = new Date();
            return true;
        }
        return false;
    }
    // получить контакт по id
    public Contact getContactById(int id){
        return contacts.get(id);
    }
    // получить все записи
    public List<Contact> allContacts(){
        List<Contact> list = contacts.values().stream().
                sorted(Comparator.comparing(Contact::getName)).
                collect(Collectors.toList());
        return list;
    }
    // удалить запись
    public boolean delContactbyId(int id) {
        if(contacts.containsKey(id)){
            Contact contact = contacts.remove(id);
            if(contact != null){
                basket.put(contact.getId(), contact);
                return true;
            }
        }
        return false;
    }
}
