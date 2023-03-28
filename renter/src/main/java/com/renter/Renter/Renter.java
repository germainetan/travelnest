package com.renter.Renter;

import jakarta.persistence.*;

@Entity
@Table
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer renterID;

    @Column
    private String fullName;
    @Column
    private Integer age;
    @Column
    private Integer phone;
    @Column
    private String email;
    @Column
    private String review;
    @Column
    private double rating;


    // Constructor

    public Renter() {
    }

    public Renter(Integer renterID, String fullName,
                  Integer age, Integer phone,
                  String email, String review, double rating) {
        this.renterID = renterID;
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.review = review;
        this.rating = rating;
    }

    public Renter(String fullName, Integer age,
                  Integer phone, String email,
                  String review, double rating) {
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.review = review;
        this.rating = rating;
    }

    // Getter and Setter

    public Integer getRenterID() {
        return renterID;
    }

    public void setRenterID(Integer renterID) {
        this.renterID = renterID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Renter{" +
                "renterID=" + renterID +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}

