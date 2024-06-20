package org.booking.bookingapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookedDTOResponse {
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
