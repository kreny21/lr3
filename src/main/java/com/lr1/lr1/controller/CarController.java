package com.lr1.lr1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lr1.lr1.model.Car;
import com.lr1.lr1.model.User;
import com.lr1.lr1.model.WishList;
import com.lr1.lr1.service.CarService;
import com.lr1.lr1.service.UserService;
import com.lr1.lr1.service.WishListService;

@RequestMapping("/cars")
@Controller
public class CarController {
    private final CarService carService;
    private final WishListService wishListService;

    public CarController(CarService carService,WishListService wishListService){
        this.carService = carService;
        this.wishListService = wishListService;
    }
    @GetMapping("/")
    public String showAllCars(@RequestParam(value = "selectedUserId", required = false) Long userId, Model model, Long carId){
        model.addAttribute("selectedUserId", userId);
        model.addAttribute("cars", carService.showAllCars());
        return "cars";
    }
    @PostMapping("/add")
    public String addCar(@RequestParam String dealer, @RequestParam String model, @RequestParam int year, @RequestParam int price){
        Car car = new Car(dealer, model, year, price);
        carService.addCar(car);
        return "redirect:/cars/";
    }

    @GetMapping("/update")
        public String showUpdateForm(@RequestParam(value = "selectedCarId") Long id, Model model) {
        model.addAttribute("car",  carService.findById(id).get());
        return "updateCar";
    }
    @PatchMapping("/update")
    public String updateUser(@ModelAttribute ("car") Car car){
        carService.updateCar(car);
        return "redirect:/cars/";
    }

    @GetMapping("/findByModel")
    public String findByModel(@RequestParam(value = "carModel") String carModel, Model model){
        if(carService.findByModel(carModel).isPresent()){
        model.addAttribute("cars", carService.findByModel(carModel).get());
        return "cars";
        }
        else{
        return "redirect:/cars/";
        }
    }

    @PostMapping("/action")
    public String userAction(@RequestParam(value = "carId", required = false) Long carId,Model model, @RequestParam(value = "selectedUserId", required = false) Long userId) {
        if(carId != null){
        model.addAttribute("selectedCar", carService.findById(carId).get());
        model.addAttribute("selectedCarId", carId);
        if(userId != null){
            model.addAttribute("selectedUserId", userId);
        }
        return "carAction";
        }
        else{
            return "redirect:/cars/";
        }
    }

    @DeleteMapping("/delete")
    public String deleteCar(@RequestParam (value = "selectedCarId") Long carId){
        carService.deleteById(carId);
        return "redirect:/cars/";
    }

    @PostMapping("/user/add")
    public String addUserCars(@RequestParam(value = "selectedUserId") Long userId, @RequestParam(value = "selectedCarId") Long carId) {
        wishListService.addWishedCar(wishListService.findByUserId(userId).get(), carId);
        return "redirect:/users/cars?selectedUserId=" + userId;
    }

    @DeleteMapping("/delete/car")
    public String deleteUserCar(@RequestParam (value = "selectedUserId")Long userId,@RequestParam(value = "selectedCarId")Long carId){
        wishListService.deleteWishedCar(wishListService.findByUserId(userId).get(), carId);
        return "redirect:/users/";
    }
    
}
