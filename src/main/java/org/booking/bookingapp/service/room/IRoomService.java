package org.booking.bookingapp.service.room;

import org.booking.bookingapp.model.Rooms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRoomService {
    List<Rooms> findAllRoom();
    Rooms getRoom(Integer id);
    Rooms updateRoom(Integer id, String roomName, String description);
    void addNewRoom(String roomName, String picture, String description, Float price, boolean status, String type, Integer size, Integer capacity, String bed, String service);
    void deleteRoom(Integer id);
    List<Rooms> findRoomByAscPrice();
    List<Rooms> findRoomByDescPrice();
    List<Rooms> findRoomWithBoundedPrice(Float price1, Float price2);
    List<Rooms> searchRoomByRoomName(String roomName);
    Page<Rooms> page(int pageNo, Float price);

}
