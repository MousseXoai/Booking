package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.customer.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customer")
@RestController
@AllArgsConstructor
public class CustomerController {

    private ICustomerService iCustomerService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createCustomer(@RequestBody AddCustomerDTO addCustomerDTO) {
        MessageResponse messageResponse = iCustomerService.createCustomer(addCustomerDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/view-info")
    public ResponseEntity<Customer> view(Authentication authentication) {
        Customer customer = iCustomerService.viewCustomerInfo(authentication);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/edit-info")
    public ResponseEntity<MessageResponse> editCustomer(Authentication authentication, @RequestBody EditUserInfoDTO editCustomerDTO) {
        MessageResponse messageResponse = iCustomerService.editCustomer(authentication, editCustomerDTO);
        return ResponseEntity.ok(messageResponse);
    }


}
