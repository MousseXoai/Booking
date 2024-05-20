package org.booking.bookingapp.service.room;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.repository.RoomsRepository;
import org.booking.bookingapp.service.room.IRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService implements IRoomService {

    private RoomsRepository roomsRepository;

    @Override
    public List<Rooms> findAllRoom() {
        return roomsRepository.getAllRooms();
    }
    @Override
    public Rooms getRoom(Integer id){
        return findAllRoom().stream()
                .filter(rooms -> rooms.getRoomId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot found"));
    }

    @Override
    public Rooms updateRoom(Integer id, String roomName, String description){
        Rooms room = getRoom(id);
        room.setRoomName(roomName);
        room.setDescription(description);
        return roomsRepository.save(room);
    }

    @Override
    public void addNewRoom(String roomName, String picture, String description, Float price, boolean status, String type, Integer size, Integer capacity, String bed, String service){
        roomsRepository.addNewRoom(roomName,picture,description,price,status,type,size,capacity,bed,service);
    }

    @Override
    public void deleteRoom(Integer id){
        roomsRepository.deleteRoomById(id);
    }

}