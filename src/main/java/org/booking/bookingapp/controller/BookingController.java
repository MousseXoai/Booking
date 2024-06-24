package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.BookingDTO;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.booking.IBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/booking")
@AllArgsConstructor
@RestController
public class BookingController {

    private IBookingService iBookingService;

    @GetMapping("/all")
    public ResponseEntity<List<Booked>> getAllBookingByUserId(Authentication authentication){
        return ResponseEntity.ok().body(iBookingService.getAllBookingByUserId(authentication));
    }

    @PostMapping("/book")
    public ResponseEntity<MessageResponse> booking(@RequestBody BookingDTO booked){
        MessageResponse messageResponse = iBookingService.bookingRoom(booked);
        return ResponseEntity.ok(messageResponse);
    }
}
