package org.booking.bookingapp.service.bill;

import org.booking.bookingapp.model.Bill;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.request.BillDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IBillService {
    MessageResponse createBill(BillDTO billBooked);

    List<Bill> getAllBill(Authentication authentication);
}
