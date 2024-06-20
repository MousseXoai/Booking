package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.request.ChangePasswordDTO;
import org.booking.bookingapp.request.FeedbackDTO;
import org.booking.bookingapp.response.MessageResponse;
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

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<Users>> findAllUser(){
        return ResponseEntity.ok().body(iUserService.findAllUsers());
    }

    @GetMapping("/user")
    public Users getUserDetailsAfterLogin(Authentication authentication) {
        return iUserService.getUserDetailsAfterLogin(authentication);
    }

    @PutMapping("/feedback")
    public ResponseEntity<MessageResponse> addFeedback(@RequestBody FeedbackDTO feedbackDTO){
        MessageResponse messageResponse = iUserService.addFeedback(feedbackDTO);
        return ResponseEntity.ok(messageResponse);
    }

}
