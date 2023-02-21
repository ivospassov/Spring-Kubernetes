package com.resellerapp.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Basic
    private Double price;

    @ManyToOne
    private Condition condition;

    @ManyToOne(fetch = FetchType.EAGER)
    private User offeror;

    @ManyToOne(fetch = FetchType.EAGER)
    private User offeree;

    public Offer() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public User getOfferor() {
        return offeror;
    }

    public void setOfferor(User offeror) {
        this.offeror = offeror;
    }

    public User getOfferee() {
        return offeree;
    }

    public void setOfferee(User offeree) {
        this.offeree = offeree;
    }

}
