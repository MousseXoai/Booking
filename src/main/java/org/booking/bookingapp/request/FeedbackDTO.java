package org.booking.bookingapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
    private Long userId;
    private Long roomId;
    private String content;
    private Float rating;
}
