package com.orientation.backend.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank(message = "Name Cannot Be Blank!!")
    @Size(min = 5, max = 50, message = "Name Must Be Between 5 To 50 Characters Only")
    private String name;

    @NotBlank(message = "User Name Cannot Be Blank")
    @Size(min = 5, max = 50, message = "UserName Must Be Between 5 To 20 Characters Only")
    @Column(updatable = false, unique = true)
    private String userName;

    @NotNull(message = "You Must Insert Your Age")
    @Max(value = 99, message = "Age Must Be Less Than 99")
    @Min(value = 8, message = "Age Must Be Bigger Than 8")
    private int age;

    @NotNull(message = "Gender Must Be Specified")
    private boolean gender;

    @NotBlank(message = "You Must Add A Description")
    @Size(min = 10, max = 500, message = "Description Must Be Between 10 To 500 Characters Only")
    private String Description;

    public User() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public boolean getGender() { return gender; }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }
}
