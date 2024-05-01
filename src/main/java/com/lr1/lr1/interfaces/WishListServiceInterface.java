package com.lr1.lr1.interfaces;

import java.util.List;
import java.util.Optional;

import com.lr1.lr1.model.Car;
import com.lr1.lr1.model.WishList;

public interface WishListServiceInterface {

    List<WishList> getAllWishList();
    Optional<WishList> findByUserId(Long id);
    void addWishedCar(WishList wishList, Long carId);
    void deleteWishedCar(WishList wishList, Long carId);
}