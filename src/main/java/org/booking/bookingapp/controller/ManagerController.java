package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.request.AddRoomDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.service.manager.IManagerService;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/manager")
@RestController
@AllArgsConstructor
public class ManagerController {
    private IRoomService iRoomService;
    private IManagerService iManagerService;

    @PostMapping("/create")
    public ResponseEntity<Manager> createCustomer(@RequestBody AddManagerDTO addManagerDTO){
        return ResponseEntity.ok().body(iManagerService.createManager(addManagerDTO));
    }
    @PostMapping("/create-room")
    public void addRoom(@RequestBody AddRoomDTO room) {
        iRoomService.addNewRoom(room);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<Rooms> updateRoomName(@PathVariable("roomId") Long roomId, @RequestParam("roomName") String roomName, @RequestParam("description") String description){
        return ResponseEntity.ok().body(iRoomService.updateRoom(roomId, roomName, description));
    }

    @DeleteMapping("/delete/{roomId}")
    public void deleteRoomByid(@PathVariable("roomId") Long roomId){
        iRoomService.deleteRoom(roomId);
    }

    @PutMapping("/ban-user/{userId}")
    public void banUser(@RequestParam Long userId){
        iManagerService.banUser(userId);
    }

    @PutMapping("/unban-user/{userId}")
    public void unbanUser(@RequestParam Long userId){
        iManagerService.unbanUser(userId);
    }

}
