package org.booking.bookingapp.service.room;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.repository.RoomsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addNewRoom(
            String roomName,
            String picture,
            String description,
            Float price,
            boolean status,
            String type,
            Integer size,
            Integer capacity,
            String bed,
            String service){

        roomsRepository.addNewRoom(roomName,picture,description,price,status,type,size,capacity,bed,service);
    }

    @Override
    public void deleteRoom(Integer id){
        roomsRepository.deleteRoomById(id);
    }

    @Override
    public List<Rooms> findRoomByAscPrice(){
        return findAllRoom().stream()
                .sorted(Comparator.comparing(Rooms::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rooms> findRoomByDescPrice(){
        return findAllRoom().stream()
                .sorted(Comparator.comparing(Rooms::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Rooms> findRoomWithBoundedPrice(Float price1, Float price2){
        return findAllRoom().stream()
                .filter(rooms -> rooms.getPrice()>=price1 && rooms.getPrice()<= price2)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rooms> searchRoomByRoomName(String roomName){
        return findAllRoom().stream()
                .filter(rooms -> rooms.getRoomName().equals(roomName))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Rooms> page(int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo,3);
        return roomsRepository.findAll(pageRequest);
    }

}