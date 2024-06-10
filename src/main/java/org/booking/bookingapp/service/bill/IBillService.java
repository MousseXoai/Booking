package org.booking.bookingapp.service.bill;

import org.booking.bookingapp.model.Bill;
import org.booking.bookingapp.model.Booked;
import org.booking.bookingapp.request.BillDTO;

import java.util.List;

public interface IBillService {
    void createBill(BillDTO billBooked);

}
