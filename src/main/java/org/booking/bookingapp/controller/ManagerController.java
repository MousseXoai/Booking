package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.request.AddRoomDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.manager.IManagerService;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/manager")
@RestController
@AllArgsConstructor
public class ManagerController {
    private IManagerService iManagerService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createManager(@RequestBody AddManagerDTO addManagerDTO){
        MessageResponse messageResponse = iManagerService.createManager(addManagerDTO);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/view-info")
    public ResponseEntity<Manager> view(Authentication authentication) {
        Manager manager = iManagerService.viewManagerInfo(authentication);
        return ResponseEntity.ok().body(manager);
    }

    @PostMapping("/edit-info")
    public ResponseEntity<MessageResponse> editCustomer(Authentication authentication, @RequestBody EditUserInfoDTO editCustomerDTO){
        MessageResponse messageResponse = iManagerService.editManager(authentication, editCustomerDTO);
        return ResponseEntity.ok(messageResponse);
    }


}
