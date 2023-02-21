package com.resellerapp.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Basic
    private String username;

    @Basic
    private String password;

    @Basic
    private String email;

    @OneToMany(mappedBy = "offeror", targetEntity = Offer.class)
    private List<Offer> offers;

    @OneToMany(mappedBy = "offeree", targetEntity = Offer.class)
    private List<Offer> boughtOffers;

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(List<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }
}
