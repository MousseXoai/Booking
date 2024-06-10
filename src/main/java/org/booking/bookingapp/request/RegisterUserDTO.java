package org.booking.bookingapp.request;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
    private Integer roleId;
    private Boolean active;
}
