package org.booking.bookingapp.service.manager;

import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.response.MessageResponse;

public interface IManagerService {
    MessageResponse createManager(AddManagerDTO addManagerDTO);

    MessageResponse banUser(Long userId);

    MessageResponse unbanUser(Long userId);
}
