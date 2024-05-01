package com.lr1.lr1.model;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String dealer;
    private String model;
    private int year;
    private int price;
    @JsonIgnore
    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private Set<WishList> wishLists;

    public Car(){

    }
    public Car(String dealer, String model, int year, int price){
        this.dealer = dealer;
        this.model = model;
        this.year = year;
        this.price = price;
    }

public Long getCarId() {
    return carId;
}
public void setCarId(Long carId) {
    this.carId = carId;
}

public String getDealer() {
    return dealer;
}
public void setDealer(String dealer) {
    this.dealer = dealer;
}

public String getModel() {
    return model;
}
public void setModel(String model) {
    this.model = model;
}

public int getYear() {
    return year;
}
public void setYear(int year) {
    this.year = year;
}

public int getPrice() {
    return price;
}
public void setPrice(int price) {
    this.price = price;
}
}
