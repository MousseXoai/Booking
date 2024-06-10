package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.service.customer.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/customer/create")
@RestController
@AllArgsConstructor
public class CustomerControllerV1 {

     private ICustomerService iCustomerService;

     @PostMapping
     public ResponseEntity<Customer> createCustomer(@RequestBody AddCustomerDTO addCustomerDTO){
         return ResponseEntity.ok().body(iCustomerService.createCustomer(addCustomerDTO));
     }

}
