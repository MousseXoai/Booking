package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddManagerDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.service.manager.IManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/manager/create")
@RestController
@AllArgsConstructor
public class ManagerControllerV1 {

    private IManagerService iManagerService;

    @PostMapping
    public ResponseEntity<Manager> createCustomer(@RequestBody AddManagerDTO addManagerDTO){
        return ResponseEntity.ok().body(iManagerService.createManager(addManagerDTO));
    }
}
