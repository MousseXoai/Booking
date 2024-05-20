package org.booking.bookingapp.service.room;

import org.booking.bookingapp.model.Rooms;

import java.util.List;

public interface IRoomService {
    List<Rooms> findAllRoom();
    Rooms getRoom(Integer id);
    Rooms updateRoom(Integer id, String roomName, String description);
    void addNewRoom(String roomName, String picture, String description, Float price, boolean status, String type, Integer size, Integer capacity, String bed, String service);
    void deleteRoom(Integer id);
}
