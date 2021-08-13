package com.orientation.backend.Controller;

import com.orientation.backend.Models.User;
import com.orientation.backend.Service.ValidationErrors;
import com.orientation.backend.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orientation")
public class Controller {

    @Autowired
    private userService service;

    @Autowired
    private ValidationErrors validationErrors;

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result){
        System.out.println("New user was hit");
        // Handling Validation Errors
        ResponseEntity<?> errorMap = validationErrors.MapValidationErrors(result);
        if(errorMap != null) return errorMap;

        User newUser = service.createUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/allusers")
    public Iterable<User> getAll(){
        System.out.println("All users was hit");
        return service.getAllUsers();
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(@RequestParam String username){
        System.out.println("Delete user was hit");
        service.deleteUser(username);
        return new ResponseEntity<String>("User Was Deleted Successfully", HttpStatus.CREATED);

    }


}
