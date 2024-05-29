package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.BookingDTO;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.service.booking.IBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/booking")
@AllArgsConstructor
@RestController
public class BookingControllerV1 {

    private IBookingService iBookingService;

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<Booked>> getAllBookingByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(iBookingService.getAllBookingByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Booked> booking(@RequestBody BookingDTO booked){
        return ResponseEntity.ok().body(iBookingService.bookingRoom(booked));
    }
}
