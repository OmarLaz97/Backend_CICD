package com.orientation.backend.Service;

import com.orientation.backend.Exceptions.projectIDException.userNameException;
import com.orientation.backend.Models.User;
import com.orientation.backend.Repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userService {

    @Autowired
    private userRepo userRepository;

    // Create New User
    public User createUser(User userData){

        String userNameData = userData.getUserName();
        User checkUserName = userRepository.findByUserName(userNameData);
        if(checkUserName == null){
            return userRepository.save(userData);
        }else{
            throw new userNameException(userNameData + " Already Exists");
        }
    }

    // Get All Users
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Delete User
    public void deleteUser(String username){
        User userToDelete = userRepository.findByUserName(username);
        if(userToDelete != null){
            userRepository.delete(userToDelete);
        }else{
            throw new userNameException(username + " Does Not Exist");
        }
    }

}
