package org.booking.bookingapp.service.user;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.request.ChangePasswordDTO;
import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.RoleRepository;
import org.booking.bookingapp.repository.UsersRepository;
import org.booking.bookingapp.util.EmailUtil;
import org.booking.bookingapp.util.OtpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;
    private RoleRepository roleRepository;
    private EmailUtil emailUtil;

    private Map<String, String> otpStore;

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
    public String changePassword(ChangePasswordDTO changePasswordDTO) {
        List<Users> userByEmail = usersRepository.findByEmail(changePasswordDTO.getEmail());
        if (userByEmail.size() > 0) {
            Users user = userByEmail.get(0);
            String otp = otpStore.get(changePasswordDTO.getEmail());
            if (otp.equals(changePasswordDTO.getOtp())) {
                if(changePasswordDTO.getPassword().equals(changePasswordDTO.getConfirmPassword())){
                    user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));
                    usersRepository.save(user);
                    otpStore.remove(changePasswordDTO.getEmail());
                }
                else {
                    throw new ApiRequestException("Password and confirm password are not matched");
                }
            } else {
                throw new NotFoundException("Invalid OTP");
            }
        } else {
            throw new NotFoundException("Cannot find user with email: " + changePasswordDTO.getEmail());
        }
        return "Password changed successfully";
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
    public String forgotPassword(String email) {
        String notice = "";
        List<Users> userByEmail = usersRepository.findByEmail(email);
        if (userByEmail.size() > 0) {
            Users user = userByEmail.get(0);
            try {
                String otp = OtpUtil.getRandomNumber(6);
                otpStore.put(email, otp);
                emailUtil.sendOtpEmail(user.getEmail(), otp);
                notice = "Your OTP is sent to your email, please use otp for change password";
            } catch (MessagingException e) {
                e.printStackTrace();
                notice = "Error sending OTP email: " + e.getMessage();
            }
        } else {
            throw new NotFoundException("Cannot find user with email: " + email);
        }
        return notice;
    }

}
