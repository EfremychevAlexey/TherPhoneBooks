package main;

import main.model.User;
import main.model.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void users() throws Exception {
        when(userRepository.findAll())
                .thenReturn(Arrays.asList(new User("First User"),
                        new User("Second User")));
         mockMvc.perform(get("/users"))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$",hasSize(2)))
                 .andExpect(jsonPath("$[*].id", containsInAnyOrder(1)));

    }

    @Test
    public void add() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void setNameUser() {
    }

    @Test
    public void list() {
    }
}