package org.booking.bookingapp.service.manager;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.AddManagerDTO;
import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.model.Users;
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

        if(managerRepository.findById(addManagerDTO.getUserId()).isPresent()){
            throw new ApiRequestException("This manager already found in system");
        }

        Users user = managerRepository.findUserIsManager().stream()
                .filter(users -> users.getUserId().equals(addManagerDTO.getUserId()))
                .findFirst()
                .orElseThrow(() -> new ApiRequestException("Doesn't create because that user isn't manager role"));

        Manager manager = new Manager();
        manager.setUserId(usersRepository.findById(user.getUserId()).orElseThrow(()->new NotFoundException("Doesn't exist that userid in system")));
        manager.setFirstName(addManagerDTO.getFirstName());
        manager.setLastName(addManagerDTO.getLastName());
        manager.setAddress(addManagerDTO.getAddress());
        manager.setPhoneNumber(addManagerDTO.getPhoneNumber());
        manager.setAvatar(addManagerDTO.getAvatar());
        return managerRepository.save(manager);
    }
}
