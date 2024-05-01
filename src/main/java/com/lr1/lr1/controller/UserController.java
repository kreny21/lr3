package com.lr1.lr1.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lr1.lr1.model.User;
import com.lr1.lr1.repository.WishListRepository;
import com.lr1.lr1.service.CarService;
import com.lr1.lr1.service.UserService;
import com.lr1.lr1.service.WishListService;



@RequestMapping("/users")
@Controller
public class UserController {
    private final WishListService wishListService;
    private final UserService userService;

    public UserController(UserService userService, WishListService wishListService){
        this.wishListService = wishListService;
        this.userService = userService;
    }
    @GetMapping("/")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.showAllUsers());
        return "users";
    }
    @PostMapping("/action")
    public String userAction(@RequestParam(value = "userId", required = false) Long userId,Model model) {
        if(userId != null){
        model.addAttribute("selectedUser", userService.findById(userId).get());
        model.addAttribute("selectedUserId", userId);
        return "userAction";
        }
        else{
            return "redirect:/users/";
        }
    }
    
    @PostMapping("/add")
    public String addUser(String email, String password, Model model){
        userService.addUser(email, password);
        return "redirect:/users/";
    }
    @GetMapping("/update")
        public String showUpdateForm(@RequestParam(value = "selectedUserId") Long id, Model model) {
        model.addAttribute("user",  userService.findById(id).get());
        return "updateUser";
    }
    @PatchMapping("/update")
    public String updateUser(@ModelAttribute ("user") User user){
        userService.updateUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/findByEmail")
    public String findByEmail(@RequestParam(value = "userEmail") String email, Model model){ 
        if(userService.findByEmail(email).isPresent()){;
        model.addAttribute("users", userService.findByEmail(email).get());
        return "users";
        }
        else{
        return "redirect:/users/";
        }
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam (value = "selectedUserId")Long userId){
        userService.deleteById(userId);
        return "redirect:/users/";
    }

    @GetMapping("/cars")
    public String showUserCars(@RequestParam(value = "selectedUserId") Long userId, Model model) {
        model.addAttribute("selectedUserId", userId);
        model.addAttribute("cars", userService.findById(userId).get().getWishList().getCars());
        return "userCars";
    }
    @GetMapping("/deposit")
    public String getBalanceForm(@RequestParam (value = "selectedUserId") Long userId, Model model){
        model.addAttribute("user", userService.findById(userId).get());
        return "userBalance";
    }


    
}
