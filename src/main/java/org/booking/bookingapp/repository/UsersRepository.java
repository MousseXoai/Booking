package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
