package com.orientation.backend.Exceptions.projectIDException;

public class userNameExceptionResponse {
    private String userName;

    public userNameExceptionResponse(String userNameData){
        this.userName = userNameData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
