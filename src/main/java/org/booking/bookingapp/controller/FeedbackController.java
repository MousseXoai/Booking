package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.EditFeedbackDTO;
import org.booking.bookingapp.request.FeedbackDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.feedback.IFeedbackService;
import org.booking.bookingapp.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/feedback")
@RestController
@AllArgsConstructor
public class FeedbackController {

    private IUserService iUserService;
    private IFeedbackService iFeedbackService;

    @GetMapping("/user-feedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbackByUser(Authentication authentication){
        return ResponseEntity.ok().body(iFeedbackService.getAllFeedbackByUser(authentication));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all-feedback")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedback(){
        return ResponseEntity.ok().body(iFeedbackService.getAllFeedback());
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> addFeedback(@RequestBody FeedbackDTO feedbackDTO){
        MessageResponse messageResponse = iUserService.addFeedback(feedbackDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> editFeedback(@RequestBody EditFeedbackDTO editFeedbackDTO){
        MessageResponse messageResponse = iUserService.editFeedback(editFeedbackDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public ResponseEntity<MessageResponse> deleteFeedback(@PathVariable("feedbackId") Long feedbackId){
        MessageResponse messageResponse = iUserService.deleteFeedback(feedbackId);
        return ResponseEntity.ok(messageResponse);
    }
}
