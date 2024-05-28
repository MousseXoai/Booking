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

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    @Query("select r from Rooms as r ")
    List<Rooms> getAllRooms();
    @Modifying
    @Transactional
    @Query("delete from Rooms r where r.roomId = ?1")
    void deleteRoomById(Long roomid);
    @Query("select r from Rooms as r where (r.price>=:minPrice and r.price<=:maxPrice) and r.roomName like concat('%', :roomName, '%')")
    Page<Rooms> paging(PageRequest page, @Param("minPrice") Float minPrice, @Param("maxPrice") Float maxPrice, @Param("roomName") String roomName);
}
