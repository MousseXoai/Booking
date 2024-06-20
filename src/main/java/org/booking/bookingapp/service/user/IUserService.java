package org.booking.bookingapp.service.user;

import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.request.ChangePasswordDTO;
import org.booking.bookingapp.request.FeedbackDTO;
import org.booking.bookingapp.request.ForgotPasswordDTO;
import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.model.Users;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {
    List<Users> findAllUsers();
    Users findUserByUserId(Long userId);
    void register(RegisterUserDTO user);
    String changePassword(ChangePasswordDTO changePasswordDTO);
    Users getUserDetailsAfterLogin(Authentication authentication);
    String getOTP(String email);
    String forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
    void addFeedback(FeedbackDTO feedbackDTO);
}
