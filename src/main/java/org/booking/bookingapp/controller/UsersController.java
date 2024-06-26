package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.request.*;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.manager.IManagerService;
import org.booking.bookingapp.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@AllArgsConstructor
@RestController
public class UsersController {

    private IUserService iUserService;
    private IManagerService iManagerService;

    @GetMapping("/profile")
    public Users getUserDetailsAfterLogin(Authentication authentication) {
        return iUserService.getUserDetailsAfterLogin(authentication);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/ban-user/{userId}")
    public ResponseEntity<MessageResponse> banUser(@RequestParam Long userId){
        MessageResponse messageResponse = iManagerService.banUser(userId);
        return ResponseEntity.ok(messageResponse);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/unban-user/{userId}")
    public ResponseEntity<MessageResponse> unbanUser(@RequestParam Long userId){
        MessageResponse messageResponse = iManagerService.unbanUser(userId);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegisterUserDTO customerRegisterDTO) {
        MessageResponse messageResponse = iUserService.register(customerRegisterDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponse> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO){
        MessageResponse messageResponse = iUserService.forgotPassword(forgotPasswordDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/get-otp")
    public ResponseEntity<MessageResponse> getOTP(@RequestParam String email){
        MessageResponse messageResponse = iUserService.getOTP(email);
        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/change-password")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        MessageResponse messageResponse = iUserService.changePassword(changePasswordDTO);
        return ResponseEntity.ok(messageResponse);
    }

}
