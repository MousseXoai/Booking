package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByEmail(String email);
}
