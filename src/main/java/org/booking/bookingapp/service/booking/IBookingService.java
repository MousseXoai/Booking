package org.booking.bookingapp.service.booking;

import org.booking.bookingapp.request.BookingDTO;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBookingService {
    List<Booked> getAllBookingByUserId(Authentication authentication);
    MessageResponse bookingRoom(BookingDTO booked);
}
