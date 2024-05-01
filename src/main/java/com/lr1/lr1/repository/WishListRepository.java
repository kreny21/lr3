package com.lr1.lr1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lr1.lr1.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    
}
