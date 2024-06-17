package org.booking.bookingapp.service.user;

import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.model.Users;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {
    List<Users> findAllUsers();
    Users findUserByUserId(Long userId);
    void register(RegisterUserDTO user);
    void changePassword(Long userId, String password);
    Users getUserDetailsAfterLogin(Authentication authentication);
    void forgotPassword(String email);
}
