package com.example.baghr;

public class User {
    public String first_name;
    public String last_name;
    public String email;
    public String password;
    public String type;

    public User(String fn, String ln, String em, String pw, String t) {
        first_name = fn;
        last_name = ln;
        email = em;
        password = pw;
        type = t;
    }

    public User (String fn, String ln, String em, String pw) {
        this(fn, ln, em, pw, "Standard");
    }

    public User() {
        this(null, null, null, null, null);
    }

}
