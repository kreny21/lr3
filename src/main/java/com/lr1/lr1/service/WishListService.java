package com.lr1.lr1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lr1.lr1.interfaces.WishListServiceInterface;
import com.lr1.lr1.model.Car;
import com.lr1.lr1.model.WishList;
import com.lr1.lr1.repository.WishListRepository;

@Service
public class WishListService implements WishListServiceInterface {
    private final WishListRepository wishListRepository;
    private final CarService carService;

    public WishListService(WishListRepository wishListRepository,UserService userService,CarService carService){
        this.wishListRepository = wishListRepository;
        this.carService = carService;
        }
    @Override
    public List<WishList> getAllWishList() {
        return wishListRepository.findAll();
    }

    @Override
    public Optional<WishList> findByUserId(Long id) {
        return wishListRepository.findById(id);
    }

    @Override
    public void addWishedCar(WishList wishList, Long carId) {
        wishList.getCars().add(carService.findById(carId).get());
        wishListRepository.save(wishList);
    }
    @Override
    public void deleteWishedCar(WishList wishList, Long carId) {
        wishList.getCars().remove(carService.findById(carId).get());
        wishListRepository.save(wishList);
    }
    
}
