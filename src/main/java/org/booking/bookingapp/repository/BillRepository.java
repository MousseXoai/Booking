package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
