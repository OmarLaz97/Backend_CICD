package com.orientation.backend.ServiceTests;

import com.orientation.backend.Exceptions.projectIDException.userNameException;
import com.orientation.backend.Models.User;
import com.orientation.backend.Repositories.userRepo;
import com.orientation.backend.Service.userService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {userService.class})
@TestPropertySource(locations = {"classpath:test.properties"})
public class ServiceTests {

    @MockBean
    private userRepo userRepository;

    @Autowired
    private userService UserService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    User newUser;

    @Before
    public void initUser(){
        newUser = new User();
        newUser.setName("Omar Laz");
        newUser.setUserName("Omar.Laz");
        newUser.setAge(23);
        newUser.setGender(false);
        newUser.setDescription("Hi My Name is Omar Laz");
    }

    @Test
    public void TestCreateUser(){

        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(userRepository.findByUserName(any(String.class))).thenReturn(null);

        User createdUser = UserService.createUser(newUser);

        assertEquals("Name saved is not the same as inserted", newUser.getName() ,createdUser.getName());
        assertEquals("UserName saved is not the same as inserted", newUser.getUserName() ,createdUser.getUserName());
        assertEquals("Age saved is not the same as inserted", newUser.getAge() ,createdUser.getAge());
        assertEquals("Gender saved is not the same as inserted", newUser.getGender() ,createdUser.getGender());
        assertEquals("Description saved is not the same as inserted", newUser.getDescription() ,createdUser.getDescription());

    }

    @Test
    public void TestNewUserIfUserNameExists(){
        when(userRepository.findByUserName(any(String.class))).thenReturn(new User());

        expectedException.expect(userNameException.class);
        expectedException.expectMessage(newUser.getUserName() + " Already Exists");

        UserService.createUser(newUser);
    }

    @Test
    public void TestDeleteUser(){
        when(userRepository.findByUserName(any(String.class))).thenReturn(null);

        expectedException.expect(userNameException.class);
        expectedException.expectMessage(newUser.getUserName() + " Does Not Exist");

        UserService.deleteUser(newUser.getUserName());
    }
}
