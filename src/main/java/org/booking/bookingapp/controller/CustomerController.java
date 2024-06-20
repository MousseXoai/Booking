package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/customer/create")
@RestController
@AllArgsConstructor
public class CustomerController {

     private ICustomerService iCustomerService;

     @PostMapping
     public ResponseEntity<MessageResponse> createCustomer(@RequestBody AddCustomerDTO addCustomerDTO){
         MessageResponse messageResponse = iCustomerService.createCustomer(addCustomerDTO);
         return ResponseEntity.ok(messageResponse);
     }

}
