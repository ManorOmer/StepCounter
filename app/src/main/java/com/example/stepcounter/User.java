package com.example.stepcounter;

public class User {
    public String fullname, weight, height, age, email;

    public User(){

    }

    public User(String fullname, String age, String weight, String height, String email){
        this.fullname = fullname;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.email = email;

    }
}
