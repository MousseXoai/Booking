package org.booking.bookingapp.request;

import lombok.*;

@Data
public class AddRoomDTO {
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
    private Long managerId;
}
