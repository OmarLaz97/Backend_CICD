package com.orientation.backend.Repositories;

import com.orientation.backend.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends CrudRepository<User, Long> {
    // A Custom Function
    User findByUserName(String userName);
}
