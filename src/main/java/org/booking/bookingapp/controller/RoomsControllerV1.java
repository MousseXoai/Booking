package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1/rooms")
@RestController
@AllArgsConstructor
public class RoomsControllerV1 {

    private IRoomService iRoomService;

    @GetMapping
    public ResponseEntity<List<Rooms>> getALlRooms(){
        return ResponseEntity.ok().body(iRoomService.findAllRoom());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Rooms> getRoom(@PathVariable("roomId") Integer roomId){
        return ResponseEntity.ok().body(iRoomService.getRoom(roomId));
    }

    @PostMapping("/add")
    public void addRoom(
            @RequestParam("roomName") String roomName,
            @RequestParam("picture") String picture,
            @RequestParam("description") String description,
            @RequestParam("price") Float price,
            @RequestParam("status") boolean status,
            @RequestParam("type") String type,
            @RequestParam("size") Integer size,
            @RequestParam("capacity") Integer capacity,
            @RequestParam("bed") String bed,
            @RequestParam("service") String service) {

        iRoomService.addNewRoom(roomName,picture,description,price,status,type,size,capacity,bed,service);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<Rooms> updateRoomName(@PathVariable("roomId") Integer roomId, @RequestParam("roomName") String roomName, @RequestParam("description") String description){
        return ResponseEntity.ok().body(iRoomService.updateRoom(roomId, roomName, description));
    }

    @DeleteMapping("/delete/{roomId}")
    public void deleteRoomByid(@PathVariable("roomId") Integer roomId){
        iRoomService.deleteRoom(roomId);
    }


}
