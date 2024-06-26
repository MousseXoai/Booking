package org.booking.bookingapp.service.feedback;

import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.request.FeedbackDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IFeedbackService {
    List<FeedbackDTO> getAllFeedback();
    List<FeedbackDTO> getAllFeedbackByUser(Authentication authentication);
}
