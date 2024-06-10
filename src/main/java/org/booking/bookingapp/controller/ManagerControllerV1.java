package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddManagerDTO;
import org.booking.bookingapp.dto.AddRoomDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.service.manager.IManagerService;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/manager/create")
@RestController
@AllArgsConstructor
public class ManagerControllerV1 {
    private IRoomService iRoomService;
    private IManagerService iManagerService;

    @PostMapping
    public ResponseEntity<Manager> createCustomer(@RequestBody AddManagerDTO addManagerDTO){
        return ResponseEntity.ok().body(iManagerService.createManager(addManagerDTO));
    }
    @PostMapping("/add")
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

}
