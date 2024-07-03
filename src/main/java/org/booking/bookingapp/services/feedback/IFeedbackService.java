package org.booking.bookingapp.services.feedback;

import org.booking.bookingapp.request.FeedbackDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IFeedbackService {
    List<FeedbackDTO> getAllFeedback();
    List<FeedbackDTO> getAllFeedbackByUser(Authentication authentication);
}
