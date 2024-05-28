package org.booking.bookingapp.service.user;

import org.booking.bookingapp.model.Users;

import java.util.List;

public interface IUserService {
    List<Users> findAllUsers();
    Users findUserByUserId(Long userId);
    void register(Users user);
    void changePassword(Long userId, String password);
}
