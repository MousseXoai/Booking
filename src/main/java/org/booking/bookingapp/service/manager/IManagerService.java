package org.booking.bookingapp.service.manager;

import org.booking.bookingapp.dto.AddManagerDTO;
import org.booking.bookingapp.model.Manager;

public interface IManagerService {
    Manager createManager(AddManagerDTO addManagerDTO);
}
