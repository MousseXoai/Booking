package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
