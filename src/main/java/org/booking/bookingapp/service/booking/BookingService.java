package org.booking.bookingapp.service.booking;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.BookingDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.model.BookedId;
import org.booking.bookingapp.repository.BookingRepository;
import org.booking.bookingapp.repository.RoomsRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService implements IBookingService{

    private BookingRepository bookingRepository;
    private UsersRepository usersRepository;
    private RoomsRepository roomsRepository;

    @Override
    public List<Booked> getAllBookingByUserId(Long userId) {
        return bookingRepository.findAll().stream()
                .filter(booked -> booked.getBookedId().getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    @Override
    public Booked bookingRoom(BookingDTO booked) {
        Booked booking = new Booked();
        booking.setBookedId(new BookedId(booked.getUserId(),booked.getRoomId()));
        booking.setUser(usersRepository.findById(booked.getUserId()).orElseThrow(()-> new NotFoundException("The user with id " + booked.getUserId() + " doesn't exist")));
        booking.setRooms(roomsRepository.findById(booked.getRoomId()).orElseThrow(()-> new NotFoundException("Cannot find the room with id " + booked.getRoomId())));
        booking.setCreatedAt(LocalDateTime.now());
        booking.setResponseStatus(1);
        return bookingRepository.save(booking);
    }
}
