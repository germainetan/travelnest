package com.property.Property;

import jakarta.persistence.*;

@Entity
@Table
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyID;

    @Column
    private String title;
    @Column
    private Integer ownerID;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private double latitude;
    @Column
    private double longitude;
    @Column
    private String address;
    @Column
    private String beds;
    @Column
    private Integer price;
    @Column
    private Integer guests;
    @Column(length=8000)
    private String review;
    @Column
    private double rating;
    @Column
    private String facilities;

    public Property() {
    }

    public Property(Integer propertyID, String title, Integer ownerID, String country, String city, double latitude, double longitude, String address, String beds, Integer price, Integer guests, String review, double rating, String facilities) {
        this.propertyID = propertyID;
        this.title = title;
        this.ownerID = ownerID;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.beds = beds;
        this.price = price;
        this.guests = guests;
        this.review = review;
        this.rating = rating;
        this.facilities = facilities;
    }

    public Property(String title, Integer ownerID, String country, String city, double latitude, double longitude, String address, String beds, Integer price, Integer guests, String review, double rating, String facilities) {
        this.title = title;
        this.ownerID = ownerID;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.beds = beds;
        this.price = price;
        this.guests = guests;
        this.review = review;
        this.rating = rating;
        this.facilities = facilities;
    }

    public Integer getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(Integer propertyID) {
        this.propertyID = propertyID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getGuests() {
        return guests;
    }

    public void setGuests(Integer guests) {
        this.guests = guests;
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

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyID=" + propertyID +
                ", title='" + title + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", beds='" + beds + '\'' +
                ", price=" + price +
                ", guests=" + guests +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", facilities='" + facilities + '\'' +
                '}';
    }
}
