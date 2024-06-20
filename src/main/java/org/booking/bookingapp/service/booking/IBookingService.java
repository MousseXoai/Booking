package org.booking.bookingapp.service.booking;

import org.booking.bookingapp.request.BookingDTO;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.response.MessageResponse;

import java.util.List;

public interface IBookingService {
    List<Booked> getAllBookingByUserId(Long userId);
    MessageResponse bookingRoom(BookingDTO booked);
}
