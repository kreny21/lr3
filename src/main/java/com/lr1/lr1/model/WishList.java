package com.lr1.lr1.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    private Long wishListId;
    private int totalPrice;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "whishList_car",
        joinColumns = @JoinColumn(name = "whishList_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id")
)
    private Set<Car> cars;

    public WishList(){

    }
    public WishList(User user){
        this.user = user;
    }


    public Long getWishListId(){
        return wishListId;
    }
    public void setWishListId(Long wishListId){
        this.wishListId = wishListId;
    }
    public int getTotalPrice(){
        return totalPrice;
    }
    public void setTotalPrice(int totalPrice){
        this.totalPrice = totalPrice;
    }
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Set<Car> getCars(){
        return cars;
    }
    public void setCars(Set<Car> cars){
        this.cars = cars;
    }

}
