package org.booking.bookingapp.service.booking;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.BookingDTO;
import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Booked;
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
                .filter(booked -> booked.getUser().getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    @Override
    public Booked bookingRoom(BookingDTO booked) {
        if(booked.getCheckIn().isBefore(LocalDateTime.now())){
            throw new ApiRequestException("You must check in after the present time!");
        }
        if(booked.getCheckIn().isAfter(booked.getCheckOut())){
            throw new ApiRequestException("You must set time check in is before time check out");
        }

        boolean roomAvailable = bookingRepository.findAll().stream()
                .filter(book -> book.getRooms().getRoomId().equals(booked.getRoomId()))
                .noneMatch(book ->
                        (book.getTimeCheckIn().isBefore(booked.getCheckIn()) && book.getTimeCheckOut().isAfter(booked.getCheckIn())) ||
                        (book.getTimeCheckIn().isBefore(booked.getCheckOut()) && book.getTimeCheckOut().isAfter(booked.getCheckOut())) ||
                        (book.getTimeCheckIn().isAfter(booked.getCheckIn()) && book.getTimeCheckOut().isBefore(booked.getCheckOut()))
                );

        if (!roomAvailable) {
            throw new ApiRequestException("Room has been booked");
        }

        Booked booking = new Booked();
        booking.setUser(usersRepository.findById(booked.getUserId()).orElseThrow(()-> new NotFoundException("The user with id " + booked.getUserId() + " doesn't exist")));
        booking.setRooms(roomsRepository.findById(booked.getRoomId()).orElseThrow(()-> new NotFoundException("Cannot find the room with id " + booked.getRoomId())));
        booking.setCreatedAt(LocalDateTime.now());
        booking.setTimeCheckIn(booked.getCheckIn());
        booking.setTimeCheckOut(booked.getCheckOut());
        booking.setResponseStatus(1);

        return bookingRepository.save(booking);
    }
}
