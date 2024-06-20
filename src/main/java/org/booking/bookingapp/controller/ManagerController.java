package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.request.AddRoomDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.service.manager.IManagerService;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/manager")
@RestController
@AllArgsConstructor
public class ManagerController {
    private IRoomService iRoomService;
    private IManagerService iManagerService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createManager(@RequestBody AddManagerDTO addManagerDTO){
        MessageResponse messageResponse = iManagerService.createManager(addManagerDTO);
        return ResponseEntity.ok(messageResponse);
    }
    @PostMapping("/create-room")
    public ResponseEntity<MessageResponse> addRoom(@RequestBody AddRoomDTO room) {
        MessageResponse messageResponse = iRoomService.addNewRoom(room);
        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<MessageResponse> updateRoomName(@PathVariable("roomId") Long roomId, @RequestParam("roomName") String roomName, @RequestParam("description") String description){
        MessageResponse messageResponse = iRoomService.updateRoom(roomId, roomName, description);
        return ResponseEntity.ok(messageResponse);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<MessageResponse> deleteRoomByid(@PathVariable("roomId") Long roomId){
        MessageResponse messageResponse = iRoomService.deleteRoom(roomId);
        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/ban-user/{userId}")
    public ResponseEntity<MessageResponse> banUser(@RequestParam Long userId){
        MessageResponse messageResponse = iManagerService.banUser(userId);
        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/unban-user/{userId}")
    public ResponseEntity<MessageResponse> unbanUser(@RequestParam Long userId){
        MessageResponse messageResponse = iManagerService.unbanUser(userId);
        return ResponseEntity.ok(messageResponse);
    }

}
