package org.booking.bookingapp.request;

import lombok.Data;

@Data
public class AddCustomerDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String avatar;
    private Long userId;
}
