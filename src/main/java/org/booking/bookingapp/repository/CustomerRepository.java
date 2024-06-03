package org.booking.bookingapp.repository;

import org.booking.bookingapp.dto.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select u from Users as u join u.roleId as r where r.roleId = 1")
    List<Users> findUserIsCustomer();
}
