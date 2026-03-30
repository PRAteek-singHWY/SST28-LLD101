package com.example.moviebooking.models;

import java.util.Date;

public class User {
    private int userId;
    private String name;
    private Date dateOfBirth;
    private String mobNo;
    private String emailId;
    private String sex;

    public User(int userId, String name, Date dateOfBirth, String mobNo, String emailId, String sex) {
        this.userId = userId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.mobNo = mobNo;
        this.emailId = emailId;
        this.sex = sex;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public String getMobNo() { return mobNo; }
    public String getEmailId() { return emailId; }
    public String getSex() { return sex; }
}
