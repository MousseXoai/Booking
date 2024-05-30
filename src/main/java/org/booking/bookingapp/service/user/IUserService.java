package org.booking.bookingapp.service.user;

import org.booking.bookingapp.dto.RegisterUserDTO;
import org.booking.bookingapp.model.Users;

import java.util.List;

public interface IUserService {
    List<Users> findAllUsers();
    Users findUserByUserId(Long userId);
    void register(RegisterUserDTO user);
    void changePassword(Long userId, String password);
}
