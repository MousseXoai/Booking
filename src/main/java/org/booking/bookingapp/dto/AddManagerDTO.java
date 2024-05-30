package org.booking.bookingapp.dto;

import lombok.Data;

@Data
public class AddManagerDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String avatar;
    private Long userId;
}
