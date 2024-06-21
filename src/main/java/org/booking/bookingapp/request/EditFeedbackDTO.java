package org.booking.bookingapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditFeedbackDTO {
    private Long feedbackId;
    private Long userId;
    private String content;
    private Float rating;
}
