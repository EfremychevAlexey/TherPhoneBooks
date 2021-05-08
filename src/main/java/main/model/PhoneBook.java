package main.model;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "phone_book")
public class PhoneBook {

    @Id
    @Column(name = "phone_book_id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_entry")
    private Date lastEntry;

    @OneToMany(mappedBy = "phoneBook", cascade = CascadeType.ALL)
    private Map<Integer, Contact> contacts;
    @OneToMany(mappedBy = "phoneBook", cascade = CascadeType.ALL)
    private Map<Integer, Contact> outdates;

    public PhoneBook() {}
    public PhoneBook(User user){
        this.id = user.getId();
        name = "Owner:" + user.getName();
        this.user = user;
        dateCreation = new Date();
        contacts = new HashMap<>();
        outdates = new HashMap<>();
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getLastEntry(){
        return lastEntry;
    }
    public void setLastEntry(Date lastEntry) {
        this.lastEntry = lastEntry;
    }

    public Map<Integer, Contact> getContacts() {
        return contacts;
    }
    public void setContacts(Map<Integer, Contact> contacts) {
        this.contacts = contacts;
    }

    public Map<Integer, Contact> getBasket() {
        return outdates;
    }
    public void setBasket(Map<Integer, Contact> outdates) {
        this.outdates = outdates;
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
                outdates.put(contact.getId(), contact);
                return true;
            }
        }
        return false;
    }
}
