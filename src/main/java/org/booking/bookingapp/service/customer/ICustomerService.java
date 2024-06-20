package org.booking.bookingapp.service.customer;

import org.booking.bookingapp.request.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.response.MessageResponse;

public interface ICustomerService {
    MessageResponse createCustomer(AddCustomerDTO addCustomerDTO);
}
