package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.model.BookedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booked, BookedId> {

}