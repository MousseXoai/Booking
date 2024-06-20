package org.booking.bookingapp.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.model.Feedback;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomsDTOResponse {
    private Long roomId;
    private String roomName;
    private String picture;
    private String description;
    private Float price;
    private Boolean status;
    private String type;
    private String size;
    private Integer capacity;
    private String bed;
    private String service;
    private List<BookedDTOResponse> booked;
    private List<FeedbackDTOResponse> feedback;
    private Float avgRating;
    private Long managerId;
}
