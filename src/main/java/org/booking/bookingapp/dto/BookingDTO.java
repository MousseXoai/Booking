package org.booking.bookingapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long userId;
    private Long roomId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
