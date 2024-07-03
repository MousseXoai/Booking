package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.services.booking.IBookingService;
import org.booking.bookingapp.services.manager.IManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/manager")
@RestController
@AllArgsConstructor
public class ManagerController {
    private IManagerService iManagerService;
    private IBookingService iBookingService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createManager(@RequestBody AddManagerDTO addManagerDTO){
        MessageResponse messageResponse = iManagerService.createManager(addManagerDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/view-profile")
    public ResponseEntity<Manager> view(Authentication authentication) {
        Manager manager = iManagerService.viewManagerInfo(authentication);
        return ResponseEntity.ok().body(manager);
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<MessageResponse> editCustomer(Authentication authentication, @RequestBody EditUserInfoDTO editCustomerDTO){
        MessageResponse messageResponse = iManagerService.editManager(authentication, editCustomerDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @GetMapping("/customer-booking")
    public ResponseEntity<List<Booked>> getAllBookingByUserId(Authentication authentication){
        return ResponseEntity.ok().body(iBookingService.getAllBookingByUserId(authentication));
    }

}
