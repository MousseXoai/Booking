package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@AllArgsConstructor
@RestController
public class UsersControllerV1 {

    private IUserService iUserService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping
    public ResponseEntity<List<Users>> findAllUser(){
        return ResponseEntity.ok().body(iUserService.findAllUsers());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/{userId}")
    public ResponseEntity<Users> findAllUserByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(iUserService.findUserByUserId(userId));
    }

    @GetMapping("/user")
    public Users getUserDetailsAfterLogin(Authentication authentication) {
        return iUserService.getUserDetailsAfterLogin(authentication);
    }

    @PutMapping("/password/{userId}")
    public void changePassword(@PathVariable("userId") Long userId, @RequestBody String password){
        iUserService.changePassword(userId, password);
    }





}
