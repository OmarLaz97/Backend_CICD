package com.orientation.backend.Controller;

import com.orientation.backend.Models.User;
import com.orientation.backend.Service.ValidationErrors;
import com.orientation.backend.Service.userService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:test.properties"})
public class ControllerTests {

    @MockBean
    private userService UserService;

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void TestCreateNewUSerAPIReturnsAUserObject() throws Exception {
        User newUser = new User();
        newUser.setName("Omar Laz");
        newUser.setUserName("Omar-Laz");
        newUser.setAge(23);
        newUser.setGender(false);
        newUser.setDescription("Hi This is my new description");

        String request = "{\"name\": \"Omar Laz\",\n" +
                "\"userName\": \"Omar-Laz\",\n" +
                "\"age\": 23,\n" +
                "\"gender\": 0,\n" +
                "\"description\": \"Hi This is my new description\"\n" + "}";

        when(UserService.createUser(any(User.class))).thenReturn(newUser);

        mockMvc.perform(post("/api/orientation/newuser").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void TestCreateNewUSerAPI() throws Exception {
        String request = "{\"name\": \"Omar Laz\",\n" +
                "\"userName\": \"Omar.Laz\",\n" +
                "\"age\": 23,\n" +
                "\"gender\": 0,\n" +
                "\"description\": \"Hi This is my new description\"\n" + "}";


        mockMvc.perform(post("/api/orientation/newuser").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void TestCreateNewUSerAPIWithEmptyName() throws Exception {
        String request = "{\"userName\": \"Omar-Laz\",\n" +
                "\"age\": 23,\n" +
                "\"gender\": 0,\n" +
                "\"description\": \"Hi This is my new description\"\n" + "}";


        mockMvc.perform(post("/api/orientation/newuser")
                .header("content-type", "application/json")
                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'name':'Name Cannot Be Blank'}"));

    }

    @Test
    public void TestCreateNewUSerAPIWithSizeSmallerThanMinName() throws Exception {
        String request = "{\"name\": \"Oma\",\n" +
                "\"userName\": \"Omar-Laz\",\n" +
                "\"age\": 23,\n" +
                "\"gender\": 0,\n" +
                "\"description\": \"Hi This is my new description\"\n" + "}";


        mockMvc.perform(post("/api/orientation/newuser")
                .header("content-type", "application/json")
                .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'name':'Name Must Be Between 5 To 50 Characters Only'}"));

    }

}
