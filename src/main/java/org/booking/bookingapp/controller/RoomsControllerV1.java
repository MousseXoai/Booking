package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Rooms> getRoom(@PathVariable("roomId") Long roomId){
        return ResponseEntity.ok().body(iRoomService.getRoom(roomId));
    }

    @PostMapping("/add")
    public void addRoom(@RequestBody Rooms room) {
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

    @GetMapping("/sorted/asc")
    public ResponseEntity<List<Rooms>> findRoomByPriceAsc(){
        return ResponseEntity.ok().body(iRoomService.findRoomByAscPrice());
    }

    @GetMapping("/sorted/desc")
    public ResponseEntity<List<Rooms>> findRoomByPriceDesc(){
        return ResponseEntity.ok().body(iRoomService.findRoomByDescPrice());
    }

    @GetMapping("/find/by/price")
    public ResponseEntity<List<Rooms>> findRoomByBoundedPrice(@RequestParam("price1") Float price1, @RequestParam("price2") Float price2){
        return ResponseEntity.ok().body(iRoomService.findRoomWithBoundedPrice(price1,price2));
    }

    @GetMapping("/search/{roomName}")
    public ResponseEntity<List<Rooms>> searchRoomByRoomName(@PathVariable("roomName") String roomName){
        return ResponseEntity.ok().body(iRoomService.searchRoomByRoomName(roomName));
    }

    @GetMapping("/pageNo/{pageNo}")
    public ResponseEntity<Page<Rooms>> pageNo(@PathVariable("pageNo") int pageNo, @RequestParam("price") Float price){
        return ResponseEntity.ok().body(iRoomService.page(pageNo-1, price));
    }

}
