package org.booking.bookingapp.services.user;

import org.booking.bookingapp.request.*;
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
    MessageResponse editFeedback(EditFeedbackDTO editFeedbackDTO);
    MessageResponse deleteFeedback(Long feedbackId);
}
