package org.booking.bookingapp.service.room;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.exception.InternalServerException;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.repository.RoomsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public Rooms getRoom(Long id){
        return findAllRoom().stream()
                .filter(rooms -> rooms.getRoomId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cannot found the room with id " + id));
    }

    @Override
    public Rooms updateRoom(Long id, String roomName, String description){
        Rooms room = getRoom(id);
        room.setRoomName(roomName);
        room.setDescription(description);
        return roomsRepository.save(room);
    }

    @Override
    public void addNewRoom(Rooms room){
        roomsRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id){
        if(findAllRoom().stream().noneMatch(rooms -> rooms.getRoomId().equals(id))){
            throw new NotFoundException("Cannot found any room with id " + id);
        }
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
        List<Rooms> listRoomByRoomName = findAllRoom().stream()
                .filter(rooms -> rooms.getRoomName().matches(roomName))
                .collect(Collectors.toList());
        if (listRoomByRoomName.isEmpty()){
            throw new NotFoundException("Cannot find room name with name is " + roomName);
        }
        return listRoomByRoomName;
    }

    @Override
    public Page<Rooms> page(int pageNo, Float minPrice, Float maxPrice, String roomName, String orderBy) {
        if (pageNo<0) throw new InternalServerException("Cannot reaching the page "+ pageNo + " because it doesn't have that page number");
        if (maxPrice == null) maxPrice=findAllRoom().stream().map(Rooms::getPrice).max(Float::compareTo).orElse(0F);
        PageRequest pageRequest = PageRequest.of(pageNo,3,Sort.by(orderBy).ascending());
        Page<Rooms> paging = roomsRepository.paging(pageRequest, minPrice, maxPrice, roomName);
        if(pageNo>=paging.getTotalPages()){
            throw new InternalServerException("Cannot reaching the page "+ pageNo + " because it doesn't have that page number");
        }
        return paging;
    }

}