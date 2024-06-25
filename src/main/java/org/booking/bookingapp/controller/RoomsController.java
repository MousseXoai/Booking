package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.response.PageResponse;
import org.booking.bookingapp.response.PagingRoomsResponse;
import org.booking.bookingapp.response.RoomsDTOResponse;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/api/v1/rooms")
@RestController
@AllArgsConstructor
public class RoomsController {

    private IRoomService iRoomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomsDTOResponse> getRoom(@PathVariable("roomId") Long roomId){
        return ResponseEntity.ok().body(iRoomService.getRoom(roomId));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PagingRoomsResponse>> page(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "minPrice", required = false, defaultValue = "0F") Float minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "") Float maxPrice,
            @RequestParam(value = "orderBy", required = false, defaultValue = "roomId") String orderBy,
            @RequestParam(value = "roomName", required = false, defaultValue = "") String roomName,
            @RequestParam(value = "sort", required = false, defaultValue = "ascending") String sort){
        PageResponse<PagingRoomsResponse> pageResponse = iRoomService.page(pageNo, minPrice, maxPrice, roomName.trim(), orderBy, sort);
        return ResponseEntity.ok(pageResponse);
    }

}
