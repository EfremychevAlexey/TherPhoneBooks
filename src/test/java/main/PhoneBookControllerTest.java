package main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PhoneBookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PhoneBookController controller;

    @Test
    public void addBook() {
    }

    @Test
    public void addContact() {
    }

    @Test
    public void getContactById() {
    }

    @Test
    public void getlist() {
    }

    @Test
    public void delbyId() {
    }

    @Test
    public void getContactByStr() {
    }
}