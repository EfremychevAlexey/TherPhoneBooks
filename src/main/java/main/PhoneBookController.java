package main;

import main.model.Contact;
import main.model.PhoneBook;
import main.model.PhoneBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PhoneBookController {
    @Autowired
    private PhoneBookRepository bookRepository;

    // добавление новой книги
    @PostMapping("/books")
    public ResponseEntity addBook(PhoneBook book) {
        bookRepository.save(book);
        return new ResponseEntity(book.getUser().getId(), HttpStatus.CREATED);
    }

    //добавление записи в телефонную книгу пользователя
    @PostMapping("/books/{id}")
    public ResponseEntity addContact(@PathVariable int id, Contact contact) {
        Optional<PhoneBook> bookOptional = bookRepository.findById(id);
        boolean res = false;
        if (bookOptional.isPresent()) {
            res = bookOptional.get().addContact(contact);
        }
        return res? new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    //получение записи телефонной книжки пользователя по id
    @GetMapping("/phone_book/{id}/{phoneid}/")
    public ResponseEntity getContactById(@PathVariable int id, int phoneId) {
        Optional<PhoneBook> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Contact contact = bookOptional.get().getContactById(phoneId);
            return new ResponseEntity(contact,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //получения списка всех записей в телефонной книжке пользователя
    @GetMapping("/phone_book/{id}")
    public ResponseEntity getlist(@PathVariable int id) {
        Optional<PhoneBook> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            List<Contact> contacts = bookOptional.get().allContacts();
            if (!contacts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(contacts);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //удаление записи в телефонной книжке пользователя по id
    @DeleteMapping("/phone_book/{id}")
    public ResponseEntity delbyId(@PathVariable int id, int phoneId) {
        Optional<PhoneBook> bookOptional = bookRepository.findById(id);
        boolean res = false;
        if (bookOptional.isPresent()) {
            res = bookOptional.get().delContactbyId(phoneId);
        }
        return res?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //поиск записи по номеру телефон

    @GetMapping("/phone_book/{id}/")
    public ResponseEntity getContactByStr(@PathVariable  int id, String str) {
        Optional<PhoneBook> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            List<Contact> contacts = bookOptional.get().
                    allContacts().stream().
                    filter(c -> c.getPhone().equals(str)).
                    collect(Collectors.toList());
            if(!contacts.isEmpty()) {
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

