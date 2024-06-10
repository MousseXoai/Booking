package org.booking.bookingapp.service.user;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.dto.RegisterUserDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.RoleRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private RoleRepository roleRepository;

    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users findUserByUserId(Long userId) {
        return findAllUsers().stream()
                .filter(users -> users.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Cannout found userId " + userId));
    }

    @Override
    public void register(RegisterUserDTO userDTO) {
        String hashPwd = passwordEncoder.encode(userDTO.getPassword());
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashPwd);
        user.setRoleId(roleRepository.findById(userDTO.getRoleId()).orElseThrow(()->new NotFoundException("Doesn't exist role in system")));
        user.setActive(userDTO.getActive());
        user.setCreateAt(LocalDateTime.now());
        usersRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, String password) {
        Users user = findUserByUserId(userId);
        user.setPassword(password);
        usersRepository.save(user);
    }

    public Users getUserDetailsAfterLogin(Authentication authentication){
        List<Users> customers = usersRepository.findByEmail(authentication.getName());
        System.out.println("1");
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }
}
