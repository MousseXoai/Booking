package org.booking.bookingapp.service.booking;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService{

    private BookingRepository bookingRepository;
    @Override
    public List<Booked> getAllBookingByUserId(Long userId) {
        return bookingRepository.findAll().stream()
                .filter(booked -> booked.getBookedId().getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Booked bookingRoom(Booked booked) {
        return bookingRepository.save(booked);
    }
}
