package org.booking.bookingapp.service.manager;

import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.model.Manager;

public interface IManagerService {
    Manager createManager(AddManagerDTO addManagerDTO);

    String banUser(Long userId);

    String unbanUser(Long userId);
}
