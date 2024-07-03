package org.booking.bookingapp.services.manager;

import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.security.core.Authentication;

public interface IManagerService {
    MessageResponse createManager(AddManagerDTO addManagerDTO);

    MessageResponse banUser(Long userId);

    MessageResponse unbanUser(Long userId);

    MessageResponse editManager(Authentication authentication, EditUserInfoDTO editCustomerDTO);

    Manager viewManagerInfo(Authentication authentication);
}
