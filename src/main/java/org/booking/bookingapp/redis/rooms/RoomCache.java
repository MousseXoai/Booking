package org.booking.bookingapp.redis.rooms;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.repository.ManagerRepository;
import org.booking.bookingapp.repository.RoomsRepository;
import org.booking.bookingapp.response.BookedDTOResponse;
import org.booking.bookingapp.response.FeedbackDTOResponse;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.response.RoomsDTOResponse;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoomCache {

    private RoomsRepository roomsRepository;

    public List<Rooms> findAllRoom() {
        return roomsRepository.getAllRooms();
    }
    @CachePut(value = "roomCache", key = "#id")
    public RoomsDTOResponse updateRoom(Long id, String roomName, String description){
        Rooms room = findAllRoom().stream()
                .filter(rooms -> rooms.getRoomId().equals(id))
                .findFirst()
                .orElseThrow(()->new NotFoundException("Cannot find room with id is " + id));
        room.setRoomName(roomName);
        room.setDescription(description);
        roomsRepository.save(room);
        return new RoomsDTOResponse(
                room.getRoomId(),
                room.getRoomName(),
                room.getPicture(),
                room.getDescription(),
                room.getPrice(),
                room.getStatus(),
                room.getType(),
                room.getSize(),
                room.getCapacity(),
                room.getBed(),
                room.getService(),
                room.getBooked().stream().map(booked -> new BookedDTOResponse(
                        booked.getTimeCheckIn(),
                        booked.getTimeCheckOut()
                )).collect(Collectors.toList()),
                room.getFeedback().stream().map(feedback -> new FeedbackDTOResponse(
                        feedback.getUserId(),
                        feedback.getContent(),
                        feedback.getRating()
                )).collect(Collectors.toList()),
                (float) room.getFeedback().stream().mapToDouble(Feedback::getRating).average().orElse(0F),
                room.getManager().getManagerId()
        );
    }
}
