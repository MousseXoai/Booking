package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.request.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.request.BookingDTO;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.booking.IBookingService;
import org.booking.bookingapp.service.customer.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customer")
@RestController
@AllArgsConstructor
public class CustomerController {

    private ICustomerService iCustomerService;
    private IBookingService iBookingService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createCustomer(@RequestBody AddCustomerDTO addCustomerDTO) {
        MessageResponse messageResponse = iCustomerService.createCustomer(addCustomerDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/view-profile")
    public ResponseEntity<Customer> view(Authentication authentication) {
        Customer customer = iCustomerService.viewCustomerInfo(authentication);
        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<MessageResponse> editCustomer(Authentication authentication, @RequestBody EditUserInfoDTO editCustomerDTO) {
        MessageResponse messageResponse = iCustomerService.editCustomer(authentication, editCustomerDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @GetMapping("/history-booking")
    public ResponseEntity<List<Booked>> getAllBookingByUserId(Authentication authentication){
        return ResponseEntity.ok().body(iBookingService.getAllBookingByUserId(authentication));
    }


}
