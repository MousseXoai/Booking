package org.booking.bookingapp.service.room;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddRoomDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Rooms;
import org.booking.bookingapp.repository.ManagerRepository;
import org.booking.bookingapp.repository.RoomsRepository;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService implements IRoomService {

    private RoomsRepository roomsRepository;
    private ManagerRepository managerRepository;

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
    public MessageResponse updateRoom(Long id, String roomName, String description){
        Rooms room = getRoom(id);
        room.setRoomName(roomName);
        room.setDescription(description);
        roomsRepository.save(room);
        return MessageResponse.builder().message("Update room successfully").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public MessageResponse addNewRoom(AddRoomDTO room){
        Rooms addRoom = new Rooms();
        addRoom.setRoomName(room.getRoomName());
        addRoom.setPicture(room.getPicture());
        addRoom.setPicture(room.getPicture());
        addRoom.setDescription(room.getDescription());
        addRoom.setPrice(room.getPrice());
        addRoom.setStatus(room.getStatus());
        addRoom.setType(room.getType());
        addRoom.setSize(room.getSize());
        addRoom.setCapacity(room.getCapacity());
        addRoom.setBed(room.getBed());
        addRoom.setService(room.getService());
        addRoom.setManager(managerRepository.findById(room.getManagerId()).orElseThrow(()->new NotFoundException("Don't have that manager in system")));
        roomsRepository.save(addRoom);
        return MessageResponse.builder().message("Add room successfully").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public MessageResponse deleteRoom(Long id){
        if(findAllRoom().stream().noneMatch(rooms -> rooms.getRoomId().equals(id))){
            return MessageResponse.builder().message("Cannot find room with id is " + id).statusCode(HttpStatus.NOT_FOUND.value()).build();
        }
        roomsRepository.deleteRoomById(id);
        return MessageResponse.builder().message("Delete room successfully").statusCode(HttpStatus.OK.value()).build();
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
    public Page<Rooms> page(int pageNo, Float minPrice, Float maxPrice, String roomName, String orderBy, String sort) {
        if (maxPrice == null) maxPrice=findAllRoom().stream().map(Rooms::getPrice).max(Float::compareTo).orElse(0F);
        int totalPages = roomsRepository.paging(PageRequest.of(0, 3), minPrice, maxPrice, roomName).getTotalPages();
        if(pageNo < 0 || pageNo >= totalPages) pageNo = 0;
        Sort sortBy = sort.equalsIgnoreCase("ascending") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNo,3, sortBy);
        Page<Rooms> paging = roomsRepository.paging(pageRequest, minPrice, maxPrice, roomName);
        return paging;
    }

}