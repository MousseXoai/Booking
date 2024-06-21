package org.booking.bookingapp.response;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingRoomsResponse {
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
    private Float avgRating;
}
