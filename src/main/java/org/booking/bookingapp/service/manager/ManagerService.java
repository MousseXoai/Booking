package org.booking.bookingapp.service.manager;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddManagerDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.repository.ManagerRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagerService implements IManagerService{
    private ManagerRepository managerRepository;
    private UsersRepository usersRepository;

    @Override
    public Manager createManager(AddManagerDTO addManagerDTO) {
        Manager manager = new Manager();
        manager.setFirstName(addManagerDTO.getFirstName());
        manager.setLastName(addManagerDTO.getLastName());
        manager.setAddress(addManagerDTO.getAddress());
        manager.setPhoneNumber(addManagerDTO.getPhoneNumber());
        manager.setAvatar(addManagerDTO.getAvatar());
        manager.setUserId(usersRepository.findById(addManagerDTO.getUserId()).orElseThrow(()->new NotFoundException("Doesn't exist that userid in system")));
        return managerRepository.save(manager);
    }
}
