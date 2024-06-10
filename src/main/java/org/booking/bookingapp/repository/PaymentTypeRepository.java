package org.booking.bookingapp.repository;

import org.booking.bookingapp.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer>{
}
