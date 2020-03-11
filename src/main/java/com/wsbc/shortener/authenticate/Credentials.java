package com.wsbc.shortener.authenticate;

import java.io.Serializable;
import java.util.Date;


public class Credentials implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username; // user email
    private String password;
    private Date loggedin;


    //need default constructor for JSON Parsing
    public Credentials() {

    }

    public Credentials(String username, Date loggedin) {
        this.username = username;
        this.loggedin = loggedin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(Date loggedin) {
        this.loggedin = loggedin;
    }
}
