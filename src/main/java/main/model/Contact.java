package main.model;

import javax.persistence.*;

//@Entity
//@Table(name = "Contacts")
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    private String name;
    private String phone;
    private PhoneBook phoneBook;

    public Contact(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public int getId(){
        return contactId;
    }

    public void setId(int id) {
        contactId = id;
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

    public void setPhoneBookId(PhoneBook phoneBook){
        this.phoneBook = phoneBook;
    }
}
