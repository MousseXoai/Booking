package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("select f from Feedback f where f.userId = ?1")
    List<Feedback> findByUserId(Long userId);
}
