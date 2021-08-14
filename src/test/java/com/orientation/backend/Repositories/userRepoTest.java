package com.orientation.backend.Repositories;

import com.orientation.backend.Models.User;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:test.properties"})
public class userRepoTest {

    @Autowired
    private userRepo userRepository;


    @Test
    @Sql(scripts = {"/sql/insertUser.sql"})
    public void TestGetALLUsersInUserRepo(){
        Iterable<User> getUsers = userRepository.findAll();


        int size = 0;
        if (getUsers instanceof Collection<?>) {
            size = ((Collection<?>)getUsers).size();
        }

        assertEquals("Findall returns a wrong size", 1, size);
    }


    @Test
    @Sql(scripts = {"/sql/insertUser.sql"})
    public void TestFindByUserName(){
        User findUser = userRepository.findByUserName("Omar.Laz");
        assertNotNull("User In TestFindByUserName Is Null", findUser);
    }

    @Test
    @Sql(scripts = {"/sql/insertUser.sql"})
    public void TestDeleteUser(){
        User findUser = userRepository.findByUserName("Omar.Laz");
        userRepository.delete(findUser);
        User findUserAgain = userRepository.findByUserName("Omar.Laz");
        assertNull("User In TestDeleteUser Was Not Deleted", findUserAgain);
    }







}
