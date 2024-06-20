package org.booking.bookingapp.service.user;

import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.request.ChangePasswordDTO;
import org.booking.bookingapp.request.FeedbackDTO;
import org.booking.bookingapp.request.ForgotPasswordDTO;
import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IUserService {
    List<Users> findAllUsers();
    Users findUserByUserId(Long userId);
    MessageResponse register(RegisterUserDTO user);
    MessageResponse changePassword(ChangePasswordDTO changePasswordDTO);
    Users getUserDetailsAfterLogin(Authentication authentication);
    MessageResponse getOTP(String email);
    MessageResponse forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
    MessageResponse addFeedback(FeedbackDTO feedbackDTO);
}
