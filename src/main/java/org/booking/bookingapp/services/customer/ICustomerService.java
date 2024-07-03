package org.booking.bookingapp.services.customer;

import org.booking.bookingapp.request.AddCustomerDTO;
import org.booking.bookingapp.model.Customer;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.security.core.Authentication;

public interface ICustomerService {
    MessageResponse createCustomer(AddCustomerDTO addCustomerDTO);

    MessageResponse editCustomer(Authentication authentication, EditUserInfoDTO editCustomerDTO);

    Customer viewCustomerInfo(Authentication authentication);
}
