package com.owner.Owner;

import jakarta.persistence.*;

@Entity
@Table
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ownerID;

    @Column
    private String fullName;
    @Column
    private Integer age;
    @Column
    private Integer phone;
    @Column
    private String email;


    // Constructor

    public Owner() {
    }

    public Owner(Integer ownerID, String fullName,
                 Integer age, Integer phone, String email) {
        this.ownerID = ownerID;
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public Owner(String fullName, Integer age,
                 Integer phone, String email) {
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    // Getter and Setter

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
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

    @Override
    public String toString() {
        return "Owner{" +
                "ownerID=" + ownerID +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
