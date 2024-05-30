package org.booking.bookingapp.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.booking.bookingapp.model.Role;

import java.time.LocalDateTime;

@Data
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
    private Integer roleId;
    private Boolean active;
}
