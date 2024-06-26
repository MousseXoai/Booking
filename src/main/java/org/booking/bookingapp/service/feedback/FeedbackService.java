package org.booking.bookingapp.service.feedback;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.FeedbackRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.booking.bookingapp.request.FeedbackDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService implements IFeedbackService {

    private FeedbackRepository feedbackRepository;
    private UsersRepository usersRepository;
    @Override
    public List<FeedbackDTO> getAllFeedback() {
        return feedbackRepository.findAll().stream()
                .map(feedback -> new FeedbackDTO(
                        feedback.getUserId(),
                        feedback.getRoom().getRoomId(),
                        feedback.getContent(),
                        feedback.getRating()
                ))
                .toList();
    }

    @Override
    public List<FeedbackDTO> getAllFeedbackByUser(Authentication authentication) {
        List<Users> users = usersRepository.findByEmail(authentication.getName());
        if(users.size() > 0){
            return feedbackRepository.findByUserId(users.get(0).getUserId()).stream()
                    .map(feedback -> new FeedbackDTO(
                            feedback.getUserId(),
                            feedback.getRoom().getRoomId(),
                            feedback.getContent(),
                            feedback.getRating()
                    ))
                    .toList();
        }
        return null;
    }
}
