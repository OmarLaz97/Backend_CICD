package com.orientation.backend.Models;

import com.orientation.backend.Service.userService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {User.class})
@TestPropertySource(locations = {"classpath:test.properties"})
public class ModelsTest {

    private User user;

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Before
    public void initUser(){
        user = new User();
    }

    @Test
    public void TestSetName(){
        String value = "Omar Laz";
        user.setName(value);

        assertEquals("Name In User Was Not Set Correctly", value, user.getName());
    }

    @Test
    public void TestSetUserName(){
        String value = "Omar.Laz";
        user.setUserName(value);

        assertEquals("UserName In User Was Not Set Correctly", value, user.getUserName());
    }

    @Test
    public void TestSetAge(){
        int value = 23;
        user.setAge(value);

        assertEquals("Age In User Was Not Set Correctly", value, user.getAge());
    }

    @Test
    public void TestSetGender(){
        boolean value = false;
        user.setGender(value);

        assertEquals("Gender In User Was Not Set Correctly", 0, user.getAge());
    }

    @Test
    public void TestSetDescription(){
        String value = "Hi This Is My Description!";
        user.setDescription(value);

        assertEquals("Description In User Was Not Set Correctly", value, user.getDescription());
    }

    @Test
    public void TestSavingEmptyName() {

        User newUser = new User();
        newUser.setName("");
        newUser.setUserName("Omar.Laz");
        newUser.setAge(23);
        newUser.setGender(false);
        newUser.setDescription("Hi My Name Is Omar Laz");

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        List<String> errors = new ArrayList<String>();

        for (ConstraintViolation<User> element : violations) {
            errors.add(element.getConstraintDescriptor().getMessageTemplate());
        }

        assertNotEquals("Name Validation was not triggered", 0, errors.size());
        assertEquals("Name Validation not triggered", "Name Cannot Be Blank", errors.get(1));
        assertEquals("Name Validation not triggered", "Name Must Be Between 5 To 50 Characters Only", errors.get(0));
    }

    @Test
    public void TestSavingShortName() {

        User newUser = new User();
        newUser.setName("Omar");
        newUser.setUserName("Omar.Laz");
        newUser.setAge(23);
        newUser.setGender(false);
        newUser.setDescription("Hi My Name Is Omar Laz");

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        List<String> errors = new ArrayList<String>();

        for (ConstraintViolation<User> element : violations) {
            errors.add(element.getConstraintDescriptor().getMessageTemplate());
        }

        assertNotEquals("Name Validation was not triggered", 0, errors.size());
        assertEquals("Name Validation not triggered", "Name Must Be Between 5 To 50 Characters Only", errors.get(0));
    }

    @Test
    public void TestSavingLongName() {

        User newUser = new User();
        newUser.setName(String.format("%0" + 51 + "d", 0).replace('0', 'a'));
        newUser.setUserName(String.format("%0" + 6 + "d", 0).replace('0', 'a'));
        newUser.setAge(23);
        newUser.setGender(false);
        newUser.setDescription("Hi My Name Is Omar Laz");

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        List<String> errors = new ArrayList<String>();

        for (ConstraintViolation<User> element : violations) {
            errors.add(element.getConstraintDescriptor().getMessageTemplate());
        }

        assertNotEquals("Name Validation was not triggered", 0, errors.size());
        assertEquals("Name Validation not triggered", "Name Must Be Between 5 To 50 Characters Only", errors.get(0));
    }

    @Test
    public void TestSavingEmptyUserName() {

        User newUser = new User();
        newUser.setName("Omar Laz");
        newUser.setUserName("");
        newUser.setAge(23);
        newUser.setGender(false);
        newUser.setDescription("Hi My Name Is Omar Laz");

        Set<ConstraintViolation<User>> violations = validator.validate(newUser);
        List<String> errors = new ArrayList<String>();

        for (ConstraintViolation<User> element : violations) {
            errors.add(element.getConstraintDescriptor().getMessageTemplate());
        }

        assertNotEquals("User Name Validation was not triggered", 0, errors.size());
        assertEquals("User Name Validation not triggered", "User Name Cannot Be Blank", errors.get(0));
        assertEquals("User Name Validation not triggered", "UserName Must Be Between 5 To 20 Characters Only", errors.get(1));
    }

}
