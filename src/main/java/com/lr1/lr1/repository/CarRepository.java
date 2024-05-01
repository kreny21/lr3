package com.lr1.lr1.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lr1.lr1.model.Car;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long>{
    Optional<Car> findByModel(String model);
}
