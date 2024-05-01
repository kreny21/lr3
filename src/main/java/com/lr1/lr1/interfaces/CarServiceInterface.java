package com.lr1.lr1.interfaces;
import java.util.List;
import java.util.Optional;

import com.lr1.lr1.model.Car;


public interface CarServiceInterface {
    List<Car> showAllCars();
    Car addCar(Car car);
    Optional<Car> findById(Long id);
    Optional<Car> findByModel(String model);
    void deleteById(Long id);
    Car updateCar(Car car);

}
