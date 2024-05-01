package com.lr1.lr1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lr1.lr1.interfaces.CarServiceInterface;
import com.lr1.lr1.model.Car;
import com.lr1.lr1.repository.CarRepository;


@Service
public class CarService implements CarServiceInterface {
    
    private final CarRepository carRepository;
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }
    @Override
    public List<Car> showAllCars(){
        return carRepository.findAll();
    }

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Optional<Car> findByModel(String model) {
        return carRepository.findByModel(model);
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }



}
