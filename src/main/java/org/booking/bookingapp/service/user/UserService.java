package org.booking.bookingapp.service.user;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.RoleRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.booking.bookingapp.util.EmailUtil;
import org.booking.bookingapp.util.OtpUtil;
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
    private EmailUtil emailUtil;

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
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void forgotPassword(String email) {
        System.out.println(email);
        List<Users> userByEmail = usersRepository.findByEmail(email);
        if (userByEmail.size() > 0) {
            Users user = userByEmail.get(0);
            try {
                emailUtil.sendOtpEmail(user.getEmail(), OtpUtil.getRandomNumber(6));
                System.out.println("Your OTP is sent to your email, please use otp within 5 minutes");
            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("Error sending OTP email: " + e.getMessage());
            }
        } else {
            throw new NotFoundException("Cannot find user with email: " + email);
        }
//        return "successfully";
    }

}
