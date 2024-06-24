package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select u from Users as u join u.roleId as r where r.roleId = 1")
    List<Users> findUserIsCustomer();

    @Query("select c from Customer as c join c.userId as u where u.userId = ?1")
    Customer findByUserId(Long userId);
}
