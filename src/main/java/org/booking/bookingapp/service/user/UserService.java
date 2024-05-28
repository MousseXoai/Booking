package org.booking.bookingapp.service.user;

import lombok.AllArgsConstructor;
import org.booking.bookingapp.exception.NotFoundException;
import org.booking.bookingapp.model.Users;
import org.booking.bookingapp.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UsersRepository usersRepository;

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
    public void register(Users user) {
        usersRepository.save(user);
    }

    @Override
    public void changePassword(Long userId, String password) {
        Users user = findUserByUserId(userId);
        user.setPassword(password);
        usersRepository.save(user);
    }


}
