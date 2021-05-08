package main.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private int id;
    private String name;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "phone_book_id")
    private PhoneBook phoneBook;
    private boolean status;

    public Contact(){}
    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public int getId(){
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }
    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }
}
