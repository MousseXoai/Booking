package org.booking.bookingapp.service.booking;

import org.booking.bookingapp.dto.BookingDTO;
import org.booking.bookingapp.model.Booked;

import java.util.List;

public interface IBookingService {
    List<Booked> getAllBookingByUserId(Long userId);
    Booked bookingRoom(BookingDTO booked);
}
