package org.booking.bookingapp.service.customer;

import org.booking.bookingapp.dto.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;

public interface ICustomerService {
    Customer createCustomer(AddCustomerDTO addCustomerDTO);
}