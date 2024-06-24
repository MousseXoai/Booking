package org.booking.bookingapp.service.manager;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.AddManagerDTO;
import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Manager;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.ManagerRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.booking.bookingapp.request.EditUserInfoDTO;
import org.booking.bookingapp.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManagerService implements IManagerService{
    private ManagerRepository managerRepository;
    private UsersRepository usersRepository;

    @Override
    public MessageResponse createManager(AddManagerDTO addManagerDTO) {

        if(managerRepository.findById(addManagerDTO.getUserId()).isPresent()){
            return MessageResponse.builder().message("Manager already exists").statusCode(HttpStatus.BAD_REQUEST.value()).build();
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
        managerRepository.save(manager);
        return MessageResponse.builder().message("Manager register successfully").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public MessageResponse banUser(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("Cannot find user with id " + userId));
        if(user.getActive().equals(true)){
            user.setActive(false);
            usersRepository.save(user);
            return MessageResponse.builder().message("User has been banned").statusCode(HttpStatus.OK.value()).build();
        }
        return MessageResponse.builder().message("User already got banned").statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }

    @Override
    public MessageResponse unbanUser(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NotFoundException("Cannot find user with id " + userId));
        if(user.getActive().equals(false)){
            user.setActive(true);
            usersRepository.save(user);
            return MessageResponse.builder().message("User has been unbanned").statusCode(HttpStatus.OK.value()).build();
        }
        return MessageResponse.builder().message("User already got unbanned").statusCode(HttpStatus.BAD_REQUEST.value()).build();
    }

    @Override
    public MessageResponse editManager(Authentication authentication, EditUserInfoDTO editCustomerDTO) {
        Users user = usersRepository.findByEmail(authentication.getName()).get(0);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        Manager manager = managerRepository.findByUserId(user.getUserId());
        if(manager == null){
            throw new NotFoundException("Manager not found");
        }
        manager.setFirstName(editCustomerDTO.getFirstName());
        manager.setLastName(editCustomerDTO.getLastName());
        manager.setAddress(editCustomerDTO.getAddress());
        manager.setPhoneNumber(editCustomerDTO.getPhoneNumber());
        manager.setAvatar(editCustomerDTO.getAvatar());
        managerRepository.save(manager);
        return MessageResponse.builder()
                .message("Manager updated successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public Manager viewManagerInfo(Authentication authentication) {
        Users user = usersRepository.findByEmail(authentication.getName()).get(0);
        if(user == null){
            throw new NotFoundException("User not found");
        }
        Manager manager = managerRepository.findByUserId(user.getUserId());
        if (manager == null) {
            throw new NotFoundException("Manager not found");
        }
        return manager;
    }
}
