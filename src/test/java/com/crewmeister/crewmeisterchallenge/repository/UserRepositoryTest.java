package com.crewmeister.crewmeisterchallenge.repository;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crewmeister.crewmeisterchallenge.controller.UserController;
import com.crewmeister.crewmeisterchallenge.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(UserController.class)
class UserRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldGetUser() throws Exception {
        User user = new User();
        user.setName("John");

        when(userRepository.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/user")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Greetings from Crewmeister, John!"
                ));
    }

    @Test
    void shouldCreateUser() throws Exception {
        User user = new User();
        user.setName("John");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Greetings from Crewmeister, John!"
                ));
    }
}
