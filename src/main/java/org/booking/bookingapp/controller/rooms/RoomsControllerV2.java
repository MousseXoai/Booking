package org.booking.bookingapp.controller.rooms;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddRoomDTO;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v2/rooms")
@RestController
@AllArgsConstructor
public class RoomsControllerV2 {

    private IRoomService iRoomService;

    @GetMapping
    public ResponseEntity<List<Rooms>> getALlRooms(){
        return ResponseEntity.ok().body(iRoomService.findAllRoom());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Rooms> getRoom(@PathVariable("roomId") Long roomId){
        return ResponseEntity.ok().body(iRoomService.getRoom(roomId));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Rooms>> page(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "minPrice", required = false, defaultValue = "0F") Float minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") Float maxPrice,
            @RequestParam(value = "orderBy", required = false, defaultValue = "roomId") String orderBy,
            @RequestParam(value = "roomName", required = false) String roomName,
            @RequestParam(value = "sort", required = false, defaultValue = "ascending") String sort){
        return ResponseEntity.ok().body(iRoomService.page(pageNo, minPrice, maxPrice, roomName.trim(), orderBy, sort));
    }

}
