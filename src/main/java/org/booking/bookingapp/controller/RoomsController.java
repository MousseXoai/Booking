package org.booking.bookingapp.controller;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddRoomDTO;
import org.booking.bookingapp.request.BookingDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.response.PageResponse;
import org.booking.bookingapp.response.PagingRoomsResponse;
import org.booking.bookingapp.response.RoomsDTOResponse;
import org.booking.bookingapp.services.booking.IBookingService;
import org.booking.bookingapp.services.room.service.IRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/rooms")
@RestController
@AllArgsConstructor
public class RoomsController {

    private IRoomService iRoomService;
    private IBookingService iBookingService;

    @GetMapping("/find/{roomId}")
    public ResponseEntity<RoomsDTOResponse> getRoom(@PathVariable("roomId") Long roomId) {
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

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/create-room")
    public ResponseEntity<MessageResponse> addRoom(@RequestBody AddRoomDTO room) {
        MessageResponse messageResponse = iRoomService.addNewRoom(room);
        return ResponseEntity.ok(messageResponse);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/update/{roomId}")
    public ResponseEntity<MessageResponse> updateRoomName(@PathVariable("roomId") Long roomId, @RequestParam("roomName") String roomName, @RequestParam("description") String description){
        MessageResponse messageResponse = iRoomService.updateRoom(roomId, roomName, description);
        return ResponseEntity.ok(messageResponse);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<MessageResponse> deleteRoomByid(@PathVariable("roomId") Long roomId){
        MessageResponse messageResponse = iRoomService.deleteRoom(roomId);
        return ResponseEntity.ok(messageResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER')")
    @PostMapping("/booking")
    public ResponseEntity<MessageResponse> booking(@RequestBody BookingDTO booked){
        MessageResponse messageResponse = iBookingService.bookingRoom(booked);
        return ResponseEntity.ok(messageResponse);
    }
}
