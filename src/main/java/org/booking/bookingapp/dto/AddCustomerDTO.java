package org.booking.bookingapp.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.booking.bookingapp.model.Users;

@Data
public class AddCustomerDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String avatar;
    private Long userId;
}
