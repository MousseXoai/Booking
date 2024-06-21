package org.booking.bookingapp.service.user;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.booking.bookingapp.exception.ApiRequestException;
import org.booking.bookingapp.model.Bill;
import org.booking.bookingapp.model.Feedback;
import org.booking.bookingapp.repository.*;
import org.booking.bookingapp.request.ChangePasswordDTO;
import org.booking.bookingapp.request.FeedbackDTO;
import org.booking.bookingapp.request.ForgotPasswordDTO;
import org.booking.bookingapp.request.RegisterUserDTO;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.response.MessageResponse;
import org.booking.bookingapp.util.EmailUtil;
import org.booking.bookingapp.util.OtpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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
    private RoomsRepository roomsRepository;
    private BillRepository billRepository;
    private FeedbackRepository feedbackRepository;
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
    public MessageResponse register(RegisterUserDTO userDTO) {
        if(!usersRepository.findByEmail(userDTO.getEmail()).isEmpty()){
            return MessageResponse.builder().message("Already have user in system").statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
        String hashPwd = passwordEncoder.encode(userDTO.getPassword());
        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashPwd);
        user.setRoleId(roleRepository.findById(userDTO.getRoleId()).orElseThrow(()->new NotFoundException("Doesn't exist role in system")));
        user.setActive(userDTO.getActive());
        user.setCreateAt(LocalDateTime.now());
        usersRepository.save(user);
        return MessageResponse.builder().message("Given user details are successfully registered").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public MessageResponse changePassword(ChangePasswordDTO changePasswordDTO) {
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
                    return MessageResponse.builder().message("Password and confirm password are not matched").statusCode(HttpStatus.BAD_REQUEST.value()).build();
                }
            } else {
                return MessageResponse.builder().message("Invalid OTP").statusCode(HttpStatus.BAD_REQUEST.value()).build();
            }
        } else {
            return MessageResponse.builder().message("Cannot find user with email: " + changePasswordDTO.getEmail()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
        return MessageResponse.builder().message("Password changed successfully").statusCode(HttpStatus.OK.value()).build();
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
    public MessageResponse getOTP(String email) {
        List<Users> userByEmail = usersRepository.findByEmail(email);
        if (userByEmail.size() > 0) {
            Users user = userByEmail.get(0);
            try {
                String otp = OtpUtil.getRandomNumber(6);
                otpStore.put(email, otp);
                emailUtil.sendOtpEmail(user.getEmail(), otp);
            } catch (MessagingException e) {
                e.printStackTrace();
                return MessageResponse.builder().message("Error sending OTP email: " + e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
            }
        } else {
            return MessageResponse.builder().message("Cannot find user with email: " + email).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
        return MessageResponse.builder().message("Your OTP is sent to your email successfully").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public MessageResponse forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        List<Users> userByEmail = usersRepository.findByEmail(forgotPasswordDTO.getEmail());
        if (userByEmail.size() > 0) {
            Users user = userByEmail.get(0);
            if (forgotPasswordDTO.getOtp().equals(otpStore.get(forgotPasswordDTO.getEmail()))) {
                try {
                    String randomPassword = OtpUtil.getRandomPassword(16);
                    user.setPassword(passwordEncoder.encode(randomPassword));
                    usersRepository.save(user);
                    otpStore.remove(forgotPasswordDTO.getEmail());
                    emailUtil.sendGeneratePassword(user.getEmail(),randomPassword);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            } else {
                return MessageResponse.builder().message("Invalid OTP").statusCode(HttpStatus.BAD_REQUEST.value()).build();
            }
        } else {
            return MessageResponse.builder().message("Cannot find user with email: " + forgotPasswordDTO.getEmail()).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
        return MessageResponse.builder().message("Password changed successfully, check your email for new password").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public MessageResponse addFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        if(billRepository.getBillIdByUserId(feedbackDTO.getUserId(), feedbackDTO.getRoomId()).isEmpty()){
            return MessageResponse.builder().message("Cannot find bill with userId: " + feedbackDTO.getUserId() + " and roomId: " + feedbackDTO.getRoomId()).statusCode(HttpStatus.NOT_FOUND.value()).build();
        }
        feedback.setContent(feedbackDTO.getContent());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setUserId(feedbackDTO.getUserId());
        feedback.setRoom(roomsRepository.findById(feedbackDTO.getRoomId()).orElseThrow(()->new NotFoundException("Cannot find room with roomId: " + feedbackDTO.getRoomId())));
        feedbackRepository.save(feedback);
        return MessageResponse.builder().message("Feedback added successfully").statusCode(HttpStatus.OK.value()).build();
    }

}
