package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Rooms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
    @Query("select r from Rooms as r ")
    List<Rooms> getAllRooms();
    //khong nen viet insert nhu nay thay vao do dung .save() cua jpa
    @Transactional
    @Modifying
    @Query("insert into Rooms as r (r.roomName, r.picture, r.description, r.price, r.status, r.type, r.size, r.capacity, r.bed, r.service) values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10) ")
    void addNewRoom(String roomName, String picture, String description, Float price, boolean status, String type, Integer size, Integer capacity, String bed, String service);
    @Modifying
    @Transactional
    @Query("delete from Rooms r where r.roomId = ?1")
    void deleteRoomById(Integer roomid);
    @Query("select r from Rooms as r where r.price>=:price order by r.price asc")
    Page<Rooms> getRoomsPagingByPriceAsc(PageRequest page, @Param("price") Float price);
}
