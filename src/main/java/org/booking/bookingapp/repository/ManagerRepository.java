package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    @Query("select u from Users as u join u.roleId as r where r.roleId = 2")
    List<Users> findUserIsManager();
}
