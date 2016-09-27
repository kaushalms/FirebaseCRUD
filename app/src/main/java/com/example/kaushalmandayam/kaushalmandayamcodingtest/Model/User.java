package com.example.kaushalmandayam.kaushalmandayamcodingtest.Model;

/**
 * Created by Kaushal.Mandayam on 9/24/2016.
 */
public class User {

    public User() {
    }

    String firstName;
    String lastName;
    String dob;
    String zipCode;
    String key;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }


}
