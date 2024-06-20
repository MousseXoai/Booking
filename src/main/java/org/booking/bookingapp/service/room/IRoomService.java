package org.booking.bookingapp.service.room;

import org.booking.bookingapp.request.AddRoomDTO;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.response.PageResponse;
import org.booking.bookingapp.response.RoomsDTOResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoomService {
    List<Rooms> findAllRoom();
    Rooms getRoom(Long id);
    MessageResponse updateRoom(Long id, String roomName, String description);
    MessageResponse addNewRoom(AddRoomDTO room);
    MessageResponse deleteRoom(Long id);
    List<Rooms> findRoomByAscPrice();
    List<Rooms> findRoomByDescPrice();
    List<Rooms> findRoomWithBoundedPrice(Float price1, Float price2);
    List<Rooms> searchRoomByRoomName(String roomName);
    PageResponse<RoomsDTOResponse> page(int pageNo, Float minPrice, Float maxPrice, String roomName, String orderBy, String sort);


}
