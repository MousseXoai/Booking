package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.RegisterUserDTO;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@AllArgsConstructor
@RestController
public class UsersControllerV1 {

    private IUserService iUserService;

    @GetMapping
    public ResponseEntity<List<Users>> findAllUser(){
        return ResponseEntity.ok().body(iUserService.findAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> findAllUserByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(iUserService.findUserByUserId(userId));
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterUserDTO user){
        iUserService.register(user);
    }

    @PutMapping("/password/{userId}")
    public void changePassword(@PathVariable("userId") Long userId, @RequestBody String password){
        iUserService.changePassword(userId, password);
    }





}
