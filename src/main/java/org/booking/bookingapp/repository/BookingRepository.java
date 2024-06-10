package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Booked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booked, Long> {
    @Query("select b from Booked b where b.bookedId in :bookedId")
    List<Booked> findAllByBookedId(List<Long> bookedId);
}
