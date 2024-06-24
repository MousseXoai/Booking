package org.booking.bookingapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserInfoDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String avatar;
}
