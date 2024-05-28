package org.booking.bookingapp.service.room;

import org.booking.bookingapp.model.Rooms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IRoomService {
    List<Rooms> findAllRoom();
    Rooms getRoom(Long id);
    Rooms updateRoom(Long id, String roomName, String description);
    void addNewRoom(Rooms room);
    void deleteRoom(Long id);
    List<Rooms> findRoomByAscPrice();
    List<Rooms> findRoomByDescPrice();
    List<Rooms> findRoomWithBoundedPrice(Float price1, Float price2);
    List<Rooms> searchRoomByRoomName(String roomName);
    Page<Rooms> page(int pageNo, Float minPrice, Float maxPrice, String roomName, String orderBy);


}
